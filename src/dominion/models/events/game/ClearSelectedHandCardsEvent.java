package dominion.models.events.game;

import dominion.game.GameManager;
import dominion.models.game.Player;

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
            player.clearSelectedHandCards();
        }
    }
}
