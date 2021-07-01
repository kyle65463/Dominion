package dominion.models.events.game;

import dominion.core.GameManager;
import dominion.models.player.Player;
import dominion.models.player.PlayerAction.PlayCard;

public class PlayCardEvent extends GameEvent {
    // Constructor
    public PlayCardEvent(int playerId, int cardId) {
        super(playerId);
        this.cardId = cardId;
    }

    // Variables
    private int cardId;

    // Functions
    @Override
    public void perform() {
        Player player = GameManager.getPlayerById(playerId);
        player.performAction(new PlayCard(cardId, true, null));
    }
}
