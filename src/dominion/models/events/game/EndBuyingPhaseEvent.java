package dominion.models.events.game;

import dominion.core.GameManager;
import dominion.models.player.Player;

public class EndBuyingPhaseEvent extends GameEvent{
    // Constructor
    public EndBuyingPhaseEvent(int playerId) {
        super(playerId);
    }

    // Functions
    @Override
    public void perform() {
        Player player = GameManager.getPlayerById(playerId);
        if(GameManager.getCurrentPhase() == GameManager.Phase.BuyingCards) {
            if (player.getId() == GameManager.getCurrentPlayer().getId()) {
                GameManager.signalCondition(GameManager.isBuyingPhaseEnd, GameManager.gameLock);
            }
        }
    }
}
