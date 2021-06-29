package dominion.models.events.game;

import dominion.game.GameManager;
import dominion.models.game.Player;
import dominion.models.game.cards.Card;

public class PlayReactionEvent extends GameEvent{
    public PlayReactionEvent(int playerId, boolean useCard, int cardId) {
        super(playerId);
        cardId = cardId;
        useCard = useCard;
    }

    private int cardId;
    private boolean useCard;

    @Override
    public void perform() {
        Player player = GameManager.getPlayerById(playerId);
        if (useCard) {
            player.react(cardId);
        }
    }
}
