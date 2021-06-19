package dominion.models.events.game;

import dominion.models.events.EventAction;
import dominion.models.game.Player;
import dominion.models.game.cards.Card;

public class PlayCardEvent extends GameEvent {
    // Constructor
    public PlayCardEvent(Player player, Card card) {
        super(player);
        this.card = card;
    }

    // Variables
    private Card card;

    // Functions
    public Card getCard() { return card; }

    @Override
    public void perform() {
        player.playCard(card);
    }
}
