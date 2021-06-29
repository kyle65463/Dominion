package dominion.game;

import dominion.connections.Connection;
import dominion.models.events.EventAction;
import dominion.models.events.game.GameEvent;
import dominion.models.game.*;
import dominion.models.game.cards.Card;
import javafx.application.Platform;

import java.util.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class GameManager {
    // Constructor
    public static void initialize(List<Player> players, Connection connection, Player applicationPlayer,
                                  MajorPurchaseArea majorPurchaseArea, MinorPurchaseArea minorPurchaseArea,
                                  int randomSeed) {
        phases.push(Phase.Reset);
        GameManager.players = players;
        Collections.sort(players, (a, b) -> a.getId() - b.getId());
        currentPlayer = players.get(0);
        connection.setActionCallback((e) -> {
            System.out.println("setActionCallback:" + e);
            Platform.runLater(() -> {
                handleEvent(e);
            });
        });
        GameManager.connection = connection;
        GameManager.majorPurchaseArea = majorPurchaseArea;
        GameManager.minorPurchaseArea = minorPurchaseArea;
        GameManager.applicationPlayer = applicationPlayer;
    }

    // Variables
    public static Lock gameLock = new ReentrantLock();
    public static Lock attackLock = new ReentrantLock();
    private static Player currentPlayer;       // The player playing actions
    private static List<Player> players;       // All players
    private static Player applicationPlayer;
    private static Connection connection;
    private static MajorPurchaseArea majorPurchaseArea;
    private static MinorPurchaseArea minorPurchaseArea;
//    private static GameScene gameScene;
    private static Stack<Phase> phases = new Stack<>();

    public static enum Phase {
        Reset,
        PlayingActions,
        BuyingCards,
        SelectingHandCards,
        SelectingDisplayedCards,
        SelectingReactionCard,
        GameOver,
    }

    private final static Condition isPlayingActionsPhaseEnd = gameLock.newCondition();
    private final static Condition isBuyingPhaseEnd = gameLock.newCondition();
    private final static Condition isDoneReacting = attackLock.newCondition();
    private final static Condition isDoneSelectingHandCards = attackLock.newCondition();
    private static int randomSeed;
    private static Random random;

    // Functions
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

//    public static GameScene getGameScene() {
//        return gameScene;
//    }

    public static int getRandomSeed() {
        return randomSeed;
    }

    public static void setRandomSeed(int randomSeed) {
        GameManager.randomSeed = randomSeed;
        GameManager.random = new Random(randomSeed);
    }

    public static int getRandomInt() {
        return random.nextInt(1000000);
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

    public static Condition getIsPlayActionsPhaseEnd() {
        return isPlayingActionsPhaseEnd;
    }

    public static Condition getIsBuyingPhaseEnd() {
        return isBuyingPhaseEnd;
    }

    public static Condition getIsDoneReacting() { return isDoneReacting; }

    public static Condition getIsDoneAttacking() { return isDoneSelectingHandCards; }


    public static void sendEvent(EventAction event) {
        if (event instanceof GameEvent) {
            if (((GameEvent) event).getPlayerId() == applicationPlayer.getId()) {
                connection.send(event);
            }
        }
    }

    public static Player getApplicationPlayer() {
        return applicationPlayer;
    }


    private static void handleEvent(EventAction event) {
        if (event instanceof GameEvent) {
            try {
                Thread.sleep(30);
            } catch (Exception e) {

            }
            System.out.printf("%s%n", event.getClass().getName());
            ((GameEvent) event).perform();
        }
    }

//    public static void attack

    public static void endPlayingActionsPhase() {
        gameLock.lock();
        try {
            isPlayingActionsPhaseEnd.signal();
        } finally {
            gameLock.unlock();
        }
    }

    public static void endBuyingPhase() {
        gameLock.lock();
        try {
            isBuyingPhaseEnd.signal();
        } finally {
            gameLock.unlock();
        }
    }

    public static void endTurn() {
        // Get next player
        int currentPlayerIndex = players.indexOf(currentPlayer);
        currentPlayer = players.get((currentPlayerIndex + 1) % players.size());
    }

    public static void checkGameOver() {
        if (minorPurchaseArea.isGameOver()) {
            gameOver();
        } else if (minorPurchaseArea.getNumNoneRemained() + majorPurchaseArea.getNumNoneRemained() >= 3) {
            gameOver();
        }
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
            WinnerDialog.setWinner(finalWinner.getName());
            GameScene.disable();
            setCurrentPhase(Phase.GameOver);
        });
    }

    public static void waitConditionLock(Condition condition, Lock lock) {
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
}
