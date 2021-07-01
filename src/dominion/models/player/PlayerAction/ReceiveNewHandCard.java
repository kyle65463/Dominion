package dominion.models.player.PlayerAction;

import dominion.models.areas.LogBox;
import dominion.models.cards.Card;
import dominion.models.player.*;

public class ReceiveNewHandCard extends PlayerAction {
    public ReceiveNewHandCard(Player player, Card card) {
        this.player = player;
        this.receivedCard = card;
    }

    private Card receivedCard;

    @Override
    public void perform(HandCards handCards, Deck deck, DiscardPile discardPile, FieldCards fieldCards) {
        LogBox.logReceiveCard(player, receivedCard);
        handCards.addCard(receivedCard);
    }
}
