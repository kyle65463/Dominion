package dominion.models.player.PlayerAction;

import dominion.models.cards.Card;
import dominion.models.player.Player;
import dominion.models.player.container.Deck;
import dominion.models.player.container.DiscardPile;
import dominion.models.player.container.FieldCards;
import dominion.models.player.container.HandCards;

import java.util.List;

public class AddCardsToDiscardPile extends PlayerAction {
    public AddCardsToDiscardPile(List<Card> cards) {
        this.addedCards = cards;
    }

    private List<Card> addedCards;


    @Override
    public void perform(Player player, HandCards handCards, Deck deck, DiscardPile discardPile, FieldCards fieldCards) {
        discardPile.addCards(addedCards);
    }
}
