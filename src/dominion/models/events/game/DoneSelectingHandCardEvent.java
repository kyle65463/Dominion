package dominion.models.events.game;

import dominion.core.GameManager;
import dominion.models.player.Player;

public class DoneSelectingHandCardEvent extends GameEvent {
    // Constructor
    public DoneSelectingHandCardEvent(int playerId, int cardId) {
        super(playerId);
        this.cardId = cardId;
    }

    // Variables
    private int cardId;

    // Functions
    @Override
    public void perform() {
        if(GameManager.getCurrentPhase() == GameManager.Phase.SelectingHandCards) {
            GameManager.returnLastPhase();
            Player player = GameManager.getPlayerById(playerId);
            player.doneHandCardsSelection(cardId);
        }
    }
}
