package dominion.models.events.game;

import dominion.game.GameManager;
import dominion.models.game.Player;
import dominion.models.game.cards.Card;

public class DoneSelectingEvent extends GameEvent {
    // Constructor
    public DoneSelectingEvent(int playerId, int cardId) {
        super(playerId);
        this.cardId = cardId;
    }

    // Variables
    private int cardId;

    // Functions
    @Override
    public void perform() {
        Player player = GameManager.getPlayerById(playerId);
        player.doneSelecting(cardId);
    }
}
