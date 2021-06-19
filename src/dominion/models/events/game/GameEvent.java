package dominion.models.events.game;

import dominion.models.events.EventAction;
import dominion.models.game.Player;

public abstract class GameEvent extends EventAction {
    // Constructor
    public GameEvent(Player player) {
        this.player = player;
    }

    // Variables
    protected Player player;

    // Functions
    public Player getPlayer() {
        return player;
    }

    public abstract void perform();
}
