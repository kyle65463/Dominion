package dominion.models.player.PlayerAction;

import dominion.models.cards.Card;
import dominion.models.player.*;

import java.util.List;

public class DiscardAllHandCards extends PlayerAction{
    public DiscardAllHandCards(Player player) {
        this.player = player;
    }

    @Override
    public void perform(HandCards handCards, Deck deck, DiscardPile discardPile, FieldCards fieldCards) {
        // Discard all hand cards to discard pile
        List<Card> cards = handCards.getCards();
        discardPile.addCards(cards);
        handCards.removeAllCards();
    }
}
