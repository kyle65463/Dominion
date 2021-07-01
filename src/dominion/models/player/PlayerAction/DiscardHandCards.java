package dominion.models.player.PlayerAction;

import dominion.models.cards.Card;
import dominion.models.player.Player;
import dominion.models.player.container.Deck;
import dominion.models.player.container.DiscardPile;
import dominion.models.player.container.FieldCards;
import dominion.models.player.container.HandCards;

import java.util.List;

public class DiscardHandCards extends PlayerAction {
    public DiscardHandCards(List<Card> cards) {
        this.discardedHandCards = cards;
    }

    private List<Card> discardedHandCards;

    @Override
    public void perform(Player player, HandCards handCards, Deck deck, DiscardPile discardPile, FieldCards fieldCards) {
        handCards.removeCards(discardedHandCards);
        discardPile.addCards(discardedHandCards);
    }
}
