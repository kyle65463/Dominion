package dominion.models.events.game;


import dominion.core.GameManager;
import dominion.models.player.Player;
import dominion.models.player.PlayerAction.DoneDisplayedCardsSelection;

public class DoneSelectingDisplayedCardEvent extends GameEvent{
    // Constructor
    public DoneSelectingDisplayedCardEvent(int playerId, int cardId) {
        super(playerId);
        this.cardId = cardId;
    }

    // Variables
    private int cardId;

    // Functions
    @Override
    public void perform() {
        if(GameManager.getCurrentPhase() == GameManager.Phase.SelectingDisplayedCards) {
            GameManager.returnLastPhase();
            Player player = GameManager.getPlayerById(playerId);
            player.performPlayerAction(new DoneDisplayedCardsSelection(cardId));
        }
    }
}
