package dominion.models.events.game;

import dominion.models.events.EventAction;
import dominion.models.game.Player;

public abstract class GameEvent extends EventAction {
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
