package dominion.models.player.PlayerAction;

import dominion.models.cards.Card;
import dominion.models.player.Player;
import dominion.models.player.container.Deck;
import dominion.models.player.container.DiscardPile;
import dominion.models.player.container.FieldCards;
import dominion.models.player.container.HandCards;

public class DiscardHandCard extends PlayerAction{
    public DiscardHandCard(Card card) {
        this.discardedCard = card;
    }

    private Card discardedCard;


    @Override
    public void perform(Player player, HandCards handCards, Deck deck, DiscardPile discardPile, FieldCards fieldCards) {
        handCards.removeCard(discardedCard);
        discardPile.addCard(discardedCard);
    }
}
