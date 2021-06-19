package dominion.models.events.game;

import dominion.game.GameManager;
import dominion.models.game.Player;

public class EndBuyingPhaseEvent extends GameEvent{
    // Constructor
    public EndBuyingPhaseEvent(Player player) {
        super(player);
    }

    // Functions
    @Override
    public void perform() {
        if(player.getId() == GameManager.getCurrentPlayer().getId()){
            GameManager.endBuyingPhase();
        }
    }
}
