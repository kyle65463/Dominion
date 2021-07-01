package dominion.models.player.PlayerAction;

import dominion.models.cards.Card;
import dominion.models.player.*;
import dominion.models.player.container.Deck;
import dominion.models.player.container.DiscardPile;
import dominion.models.player.container.FieldCards;
import dominion.models.player.container.HandCards;

public class RetrieveHandCardFromFieldCards extends PlayerAction {
    public RetrieveHandCardFromFieldCards(Player player, Card card) {
        this.player = player;
        this.retrievedCard = card;
    }

    private Card retrievedCard;

    @Override
    public void perform(HandCards handCards, Deck deck, DiscardPile discardPile, FieldCards fieldCards) {
        fieldCards.removeCard(retrievedCard);
        handCards.addCard(retrievedCard);
    }
}
