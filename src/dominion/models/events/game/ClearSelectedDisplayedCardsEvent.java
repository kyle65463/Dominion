package dominion.models.events.game;


import dominion.core.GameManager;
import dominion.models.player.Player;

public class ClearSelectedDisplayedCardsEvent extends GameEvent {
    public ClearSelectedDisplayedCardsEvent(int playerId) {
        super(playerId);
    }

    // Functions
    @Override
    public void perform() {
        if(GameManager.getCurrentPhase() == GameManager.Phase.SelectingDisplayedCards) {
            Player player = GameManager.getPlayerById(playerId);
            player.clearSelectedDisplayedCards();
        }
    }
}
