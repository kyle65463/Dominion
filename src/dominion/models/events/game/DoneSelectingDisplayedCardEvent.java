package dominion.models.events.game;

import dominion.game.GameManager;
import dominion.models.game.Player;

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
            player.doneDisplayedSelection(cardId);
        }
    }
}
