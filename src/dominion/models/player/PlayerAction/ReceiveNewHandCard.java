package dominion.models.player.PlayerAction;

import dominion.models.areas.LogBox;
import dominion.models.cards.Card;
import dominion.models.player.*;
import dominion.models.player.container.Deck;
import dominion.models.player.container.DiscardPile;
import dominion.models.player.container.FieldCards;
import dominion.models.player.container.HandCards;

public class ReceiveNewHandCard extends PlayerAction {
    public ReceiveNewHandCard(Card card) {
        this.receivedCard = card;
    }

    private Card receivedCard;

    @Override
    public void perform(Player player, HandCards handCards, Deck deck, DiscardPile discardPile, FieldCards fieldCards) {
        LogBox.logReceiveCard(player, receivedCard);
        handCards.addCard(receivedCard);
    }
}
