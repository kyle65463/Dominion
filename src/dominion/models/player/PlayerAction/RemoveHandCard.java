package dominion.models.player.PlayerAction;

import dominion.models.cards.Card;
import dominion.models.player.Player;
import dominion.models.player.container.Deck;
import dominion.models.player.container.DiscardPile;
import dominion.models.player.container.FieldCards;
import dominion.models.player.container.HandCards;

public class RemoveHandCard extends PlayerAction{
    public RemoveHandCard(Card card) {
        this.removedHandCard = card;
    }

    private Card removedHandCard;

    @Override
    public void perform(Player player, HandCards handCards, Deck deck, DiscardPile discardPile, FieldCards fieldCards) {
        handCards.removeCard(removedHandCard);
    }
}
