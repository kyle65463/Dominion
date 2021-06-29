package dominion.models.events.game;

import dominion.models.events.Event;

public abstract class GameEvent extends Event {
    // Constructor
    public GameEvent(int playerId) {
        this.playerId = playerId;
    }

    // Variables
    protected int playerId;

    // Functions
    public int getPlayerId() {
        return playerId;
    }

    public abstract void perform();
}
