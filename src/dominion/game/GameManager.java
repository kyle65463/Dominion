package dominion.game;

import dominion.models.events.EventAction;
import dominion.models.events.game.GameEvent;
import dominion.models.game.*;

import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class GameManager {
    // Constructor
    public static void initialize(List<Player> players) {
        GameManager.players = players;
        currentPlayer = players.get(0);
    }

    // Variables
    public static Lock lock = new ReentrantLock();
    private static Player currentPlayer;       // The player playing actions
    private static List<Player> players;       // All players

    private final static Condition isPlayActionCardsPhaseEnd = lock.newCondition();
    private final static Condition isBuyingCardsPhaseEnd = lock.newCondition();

    // Functions
    public static Player getCurrentPlayer() {
        return currentPlayer;
    }

    public static Condition getIsPlayActionCardsPhaseEnd() {
        return isPlayActionCardsPhaseEnd;
    }

    public static Condition getIsBuyingCardsPhaseEnd() {
        return isBuyingCardsPhaseEnd;
    }

    public static void sendEvent(EventAction event) {
        handleEvent(event);
    }

    private static void handleEvent(EventAction event) {
        if(event instanceof GameEvent) {
            ((GameEvent)event).perform();
            playActionCardsPhaseEnd();
        }
    }

    public static void playActionCardsPhaseEnd() {
        lock.lock();
        try {
            isPlayActionCardsPhaseEnd.signal();
        }finally {
            lock.unlock();
        }
    }

    public static void buyingCardsPhaseEnd() {
        lock.lock();
        try {
            isBuyingCardsPhaseEnd.signal();
        }finally {
            lock.unlock();
        }
    }

    public static void endTurn() {
        // Get next player
        int currentPlayerIndex = players.indexOf(currentPlayer);
//        currentPlayer = players.get((currentPlayerIndex + 1) % players.size());

    }
}
