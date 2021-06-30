package dominion.models.events.game;

import dominion.core.GameManager;
import dominion.models.player.Player;

public class SelectHandCardEvent extends GameEvent {
    // Constructor
    public SelectHandCardEvent(int playerId, int cardId) {
        super(playerId);
        this.cardId = cardId;
    }

    // Variables
    private int cardId;

    // Functions
    @Override
    public void perform() {
        if(GameManager.getCurrentPhase() == GameManager.Phase.SelectingHandCards) {
            Player player = GameManager.getPlayerById(playerId);
            player.selectHandCard(cardId);
        }
    }
}