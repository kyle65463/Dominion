package dominion.game;

import dominion.connections.Connection;
import dominion.models.events.EventAction;
import dominion.models.events.game.GameEvent;
import dominion.models.game.*;
import javafx.application.Platform;

import java.util.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class GameManager {
    // Constructor
    public static void initialize(List<Player> players, Connection connection, Player applicationPlayer,
                                  MajorPurchaseArea majorPurchaseArea, MinorPurchaseArea minorPurchaseArea,
                                  int randomSeed,
                                  GameScene gameScene) {
        phases.push(Phase.Reset);
        GameManager.players = players;
        Collections.sort(players, (a, b) -> a.getId() - b.getId());
        currentPlayer = players.get(0);
        connection.setActionCallback((e) -> {
            Platform.runLater(() -> {
                handleEvent(e);
            });
        });
        GameManager.connection = connection;
        GameManager.majorPurchaseArea = majorPurchaseArea;
        GameManager.minorPurchaseArea = minorPurchaseArea;
        GameManager.gameScene = gameScene;
        GameManager.applicationPlayer = applicationPlayer;
    }

    // Variables
    public static Lock lock = new ReentrantLock();
    private static Player currentPlayer;       // The player playing actions
    private static List<Player> players;       // All players
    private static Player applicationPlayer;
    private static Connection connection;
    private static MajorPurchaseArea majorPurchaseArea;
    private static MinorPurchaseArea minorPurchaseArea;
    private static GameScene gameScene;
    private static Stack<Phase> phases = new Stack<>();

    public static enum Phase {
        Reset,
        PlayingActions,
        BuyingCards,
        SelectingHandCards,
        SelectingDisplayedCards,
    }

    private final static Condition isPlayingActionsPhaseEnd = lock.newCondition();
    private final static Condition isBuyingPhaseEnd = lock.newCondition();
    private static int randomSeed;
    private static Random random;

    // Functions
    public static Phase getCurrentPhase() {
        return phases.peek();
    }

    public static void returnLastPhase() {
        phases.pop();
    }

    public static void setCurrentPhase(Phase phase) {
        phases.push(phase);
    }

    public static GameScene getGameScene() {
        return gameScene;
    }

    public static int getRandomSeed(){
        return randomSeed;
    }

    public static void setRandomSeed(int randomSeed) {
        GameManager.randomSeed = randomSeed;
        GameManager.random = new Random(randomSeed);
    }

    public static int getRandomInt(){
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

    public static Condition getIsPlayActionsPhaseEnd() {
        return isPlayingActionsPhaseEnd;
    }

    public static Condition getIsBuyingPhaseEnd() {
        return isBuyingPhaseEnd;
    }

    public static void sendEvent(EventAction event) {
        if (event instanceof GameEvent) {
            if(((GameEvent) event).getPlayerId() == applicationPlayer.getId()) {
                connection.send(event);
            }
        }
    }

    private static void handleEvent(EventAction event) {
        if (event instanceof GameEvent) {
            try {
                Thread.sleep(30);
            }
            catch (Exception e) {

            }
            ((GameEvent) event).perform();
        }
    }

    public static void endPlayingActionsPhase() {
        lock.lock();
        try {
            isPlayingActionsPhaseEnd.signal();
        } finally {
            lock.unlock();
        }
    }

    public static void endBuyingPhase() {
        lock.lock();
        try {
            isBuyingPhaseEnd.signal();
        } finally {
            lock.unlock();
        }
    }

    public static void endTurn() {
        // Get next player
        int currentPlayerIndex = players.indexOf(currentPlayer);
        currentPlayer = players.get((currentPlayerIndex + 1) % players.size());
    }

    public static void checkGameOver() {
        if(minorPurchaseArea.isGameOver()) {
            gameOver();
        }
        else if(minorPurchaseArea.getNumNoneRemained() + majorPurchaseArea.getNumNoneRemained() >= 3){
            gameOver();
        }
    }

    private static void gameOver(){
        int maxNumScores = 0;
        List<Player> winners = new ArrayList<>();
        for (Player player : players) {
            int scores = player.getNumScores();
            if(scores >= maxNumScores){
                scores = maxNumScores;
               winners.add(player);
            }
        }
        for (Player winner : winners) {
            System.out.println(winner + "wins!");
        }
    }

}
