package dominion.models.events.game;

import dominion.core.GameManager;
import dominion.models.player.Player;

public class SelectReactionCardEvent extends GameEvent{
    public SelectReactionCardEvent(int playerId, int cardId) {
        super(playerId);
        this.cardId = cardId;
    }

    private int cardId;

    @Override
    public void perform() {
        if(GameManager.getCurrentPhase() == GameManager.Phase.SelectingReactionCard) {
            Player player = GameManager.getPlayerById(playerId);
            player.react(cardId);
        }
    }
}
