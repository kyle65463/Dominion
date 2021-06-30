package dominion.core;

import dominion.connections.Connection;
import dominion.controllers.components.ReturnRoomController;
import dominion.controllers.scenes.RoomController;
import dominion.models.areas.GameScene;
import dominion.models.areas.MajorPurchaseArea;
import dominion.models.areas.MinorPurchaseArea;
import dominion.models.areas.WinnerDialog;
import dominion.models.events.Event;
import dominion.models.events.game.GameEvent;
import dominion.models.cards.Card;
import dominion.models.events.game.InterActiveEvent;
import dominion.models.areas.DisplayedCard;
import dominion.models.player.Player;
import dominion.utils.VoicePlayer;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.util.Duration;

import java.util.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

public class GameManager {
    // Constructor
    public static void initialize(List<Player> players, Player applicationPlayer, Connection connection ) {
        phases.push(Phase.Reset);
        GameManager.players = players;
        Collections.sort(players, (a, b) -> a.getId() - b.getId());
        currentPlayer = players.get(0);
        connection.setEventHandler((e) -> {
            Platform.runLater(() -> {
                handleEvent(e);
            });
        });
        GameManager.connection = connection;
        GameManager.applicationPlayer = applicationPlayer;
        System.out.println(currentPlayer);
        System.out.println(GameManager.applicationPlayer);

    }

    // Variables
    public final static Lock gameLock = new ReentrantLock();
    public final static Lock attackLock = new ReentrantLock();
    public final static Condition isPlayingActionsPhaseEnd = gameLock.newCondition();
    public final static Condition isBuyingPhaseEnd = gameLock.newCondition();
    public final static Condition isDoneReacting = attackLock.newCondition();
    public final static Condition isDoneAttacking = attackLock.newCondition();

    private static Player currentPlayer;       // The player playing actions
    private static List<Player> players;       // All players
    private static Player applicationPlayer;
    private static Connection connection;
    private static MajorPurchaseArea majorPurchaseArea;
    private static MinorPurchaseArea minorPurchaseArea;
    private static Stack<Phase> phases = new Stack<>();
    private static int randomSeed;
    private static Random random;

    public enum Phase {
        Reset,
        PlayingActions,
        BuyingCards,
        SelectingHandCards,
        SelectingDisplayedCards,
        SelectingReactionCard,
        SelectingOptions,
        GameOver,
    }

    // Functions
    public static Player getApplicationPlayer(){return applicationPlayer;}

    public static List<Player> getPlayers() {
        return new ArrayList<>(players);
    }

    public static Phase getCurrentPhase() {
        return phases.peek();
    }

    public static void returnLastPhase() {
        phases.pop();
    }

    public static void setCurrentPhase(Phase phase) {
        phases.push(phase);
    }

    public static int getRandomInt() {
        return getRandomInt(1000000);
    }

    public static int getRandomInt(int upperBound) {
        if (random != null) {
            return random.nextInt(upperBound);
        }
        return 0;
    }

    public static Player getCurrentPlayer() {
        return currentPlayer;
    }

    public static Player getPlayerById(int id) {
        return players.get(id);
    }

    public static DisplayedCard getDisplayedCardById(int displayedCardId) {
        DisplayedCard displayedCard = majorPurchaseArea.getDisplayedCardById(displayedCardId);
        if (displayedCard == null) {
            displayedCard = minorPurchaseArea.getDisplayedCardById(displayedCardId);
        }
        return displayedCard;
    }

    public static DisplayedCard getDisplayCardByCard(Card card) {
        DisplayedCard displayedCard = majorPurchaseArea.getDisplayedCardByCard(card);
        if (displayedCard == null) {
            displayedCard = minorPurchaseArea.getDisplayedCardByCard(card);
        }
        return displayedCard;
    }

    public static void endTurn() {
        // Get next player
        int currentPlayerIndex = players.indexOf(currentPlayer);
        currentPlayer = players.get((currentPlayerIndex + 1) % players.size());
    }

    public static boolean checkGameOver() {
        if (minorPurchaseArea.isGameOver()) {
            VoicePlayer.playEffect(1);
            gameOver();
            return true;
        } else if (minorPurchaseArea.getNumNoneRemained() + majorPurchaseArea.getNumNoneRemained() >= 3) {
            VoicePlayer.playEffect(1);
            gameOver();
            return true;
        }
        return false;
    }

    private static void gameOver() {
        int maxNumScores = 0;
        Player winner = currentPlayer;
        for (Player player : players) {
            int scores = player.getNumScores();
            if (scores > maxNumScores) {
                maxNumScores = scores;
                winner = player;
            }
        }
        Player finalWinner = winner;
        Platform.runLater(() -> {
            GameScene.disable();
            WinnerDialog.setWinner(finalWinner.getName());
            setCurrentPhase(Phase.GameOver);
        });
        PauseTransition returnRoom = new PauseTransition(Duration.seconds(3));
        returnRoom.setOnFinished(e-> ReturnRoomController.navigateToRoomScene());
        returnRoom.play();
    }

    // Random seed
    public static int getRandomSeed() {
        return randomSeed;
    }

    public static void setRandomSeed(int randomSeed) {
        GameManager.randomSeed = randomSeed;
        GameManager.random = new Random(randomSeed);
    }

    // Conditions
    public static void waitCondition(Condition condition, Lock lock) {
        lock.lock();
        try {
            condition.await();
        } catch (Exception e) {

        } finally {
            lock.unlock();
        }
    }

    public static void signalCondition(Condition condition, Lock lock) {
        lock.lock();
        try {
            condition.signal();
        } finally {
            lock.unlock();
        }
    }

    // Events
    public static void sendEvent(Event event) {
        if (event instanceof GameEvent) {
            if (((GameEvent) event).getPlayerId() == applicationPlayer.getId()) {
                connection.send(event);
            }
        }else if(event instanceof InterActiveEvent){
            connection.send(event);
        }
    }


    private static void handleEvent(Event event) {
        if (event instanceof GameEvent) {
            try {
                Thread.sleep(30);
            } catch (Exception e) {

            }
            System.err.println("handle event: " + event);
            System.err.println("phase now: " + phases.stream().collect(Collectors.toList()).toString());
            System.err.println();

            ((GameEvent) event).perform();
        }else if(event instanceof InterActiveEvent){
            try {
                Thread.sleep(30);
            } catch (Exception e) {

            }
            ((InterActiveEvent) event).perform();
        }
    }
}
