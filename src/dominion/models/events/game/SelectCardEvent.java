package dominion.models.events.game;

import dominion.game.GameManager;
import dominion.models.game.Player;

public class SelectCardEvent extends GameEvent {
    // Constructor
    public SelectCardEvent(int playerId, int cardId) {
        super(playerId);
        this.cardId = cardId;
    }

    // Variables
    private int cardId;

    // Functions
    @Override
    public void perform() {
        Player player = GameManager.getPlayerById(playerId);
        player.selectCard(cardId);
    }
}