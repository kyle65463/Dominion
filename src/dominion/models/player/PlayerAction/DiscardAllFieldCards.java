package dominion.models.player.PlayerAction;

import dominion.models.cards.Card;
import dominion.models.player.*;
import dominion.models.player.container.Deck;
import dominion.models.player.container.DiscardPile;
import dominion.models.player.container.FieldCards;
import dominion.models.player.container.HandCards;

import java.util.List;

public class DiscardAllFieldCards extends PlayerAction {
    public DiscardAllFieldCards() {
    }

    @Override
    public void perform(Player player, HandCards handCards, Deck deck, DiscardPile discardPile, FieldCards fieldCards) {
        // Discard all field cards to discard `pile
        List<Card> cards = fieldCards.getCards();
        fieldCards.removeAllCards();
        discardPile.addCards(cards);
    }
}
