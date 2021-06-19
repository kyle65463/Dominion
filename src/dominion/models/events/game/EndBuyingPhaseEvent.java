package dominion.models.events.game;

import dominion.game.GameManager;
import dominion.models.game.Player;

public class EndBuyingPhaseEvent extends GameEvent{
    // Constructor
    public EndBuyingPhaseEvent(int playerId) {
        super(playerId);
    }

    // Functions
    @Override
    public void perform() {
        Player player = GameManager.getPlayerById(playerId);
        if(player.getId() == GameManager.getCurrentPlayer().getId()){
            GameManager.endBuyingPhase();
        }
    }
}
