package dominion.models.player.PlayerAction;

import dominion.models.cards.Card;
import dominion.models.player.*;
import dominion.models.player.container.Deck;
import dominion.models.player.container.DiscardPile;
import dominion.models.player.container.FieldCards;
import dominion.models.player.container.HandCards;

import java.util.List;

public class DiscardAllHandCards extends PlayerAction{
    public DiscardAllHandCards() {
    }

    @Override
    public void perform(Player player, HandCards handCards, Deck deck, DiscardPile discardPile, FieldCards fieldCards) {
        // Discard all hand cards to discard pile
        List<Card> cards = handCards.getCards();
        handCards.removeAllCards();
        discardPile.addCards(cards);
    }
}
