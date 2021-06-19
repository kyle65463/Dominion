package dominion.models.events.game;

import dominion.game.GameManager;
import dominion.models.game.Player;
import dominion.models.game.cards.Card;

public class PlayCardEvent extends GameEvent {
    // Constructor
    public PlayCardEvent(int playerId, int cardId) {
        super(playerId);
        this.cardId = cardId;
    }

    // Variables
    private int cardId;

    // Functions
//    public Card getCard() { return card; }

    @Override
    public void perform() {
        Player player = GameManager.getPlayerById(playerId);
        System.out.println(player.getName() + " " + player.getId());
        player.playCard(cardId);
    }
}
