package dominion.models.events.game;

import dominion.core.GameManager;
import dominion.models.player.Player;
import dominion.models.player.PlayerAction.ClearSelectedHandCards;

public class ClearSelectedHandCardsEvent extends GameEvent{
    // Constructor
    public ClearSelectedHandCardsEvent(int playerId) {
        super(playerId);
    }

    // Functions
    @Override
    public void perform() {
        if(GameManager.getCurrentPhase() == GameManager.Phase.SelectingHandCards) {
            Player player = GameManager.getPlayerById(playerId);
            player.performAction(new ClearSelectedHandCards());
        }
    }
}
