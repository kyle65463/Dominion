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

    private final static Condition isPlayingActionsPhaseEnd = lock.newCondition();
    private final static Condition isBuyingPhaseEnd = lock.newCondition();

    // Functions
    public static Player getCurrentPlayer() {
        return currentPlayer;
    }

    public static Condition getIsPlayActionsPhaseEnd() {
        return isPlayingActionsPhaseEnd;
    }

    public static Condition getIsBuyingPhaseEnd() {
        return isBuyingPhaseEnd;
    }

    public static void sendEvent(EventAction event) {
        handleEvent(event);
    }

    private static void handleEvent(EventAction event) {
        if(event instanceof GameEvent) {
            ((GameEvent)event).perform();
        }
    }

    public static void endPlayingActionsPhase() {
        lock.lock();
        try {
            isPlayingActionsPhaseEnd.signal();
        }finally {
            lock.unlock();
        }
    }

    public static void endBuyingPhase() {
        lock.lock();
        try {
            isBuyingPhaseEnd.signal();
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
