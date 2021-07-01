package dominion.models.player.PlayerAction;

import dominion.models.cards.Card;
import dominion.models.player.*;

import java.util.List;

public class DiscardAllFieldCards extends PlayerAction {
    public DiscardAllFieldCards(Player player) {
         this.player = player;
    }

    @Override
    public void perform(HandCards handCards, Deck deck, DiscardPile discardPile, FieldCards fieldCards) {
        // Discard all field cards to discard `pile
        List<Card> cards = fieldCards.getCards();
        fieldCards.removeCards();
        discardPile.addCards(cards);
    }
}
