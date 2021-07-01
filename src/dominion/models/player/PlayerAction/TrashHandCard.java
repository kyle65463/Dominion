package dominion.models.player.PlayerAction;

import dominion.models.areas.LogBox;
import dominion.models.cards.Card;
import dominion.models.player.*;
import dominion.models.player.container.Deck;
import dominion.models.player.container.DiscardPile;
import dominion.models.player.container.FieldCards;
import dominion.models.player.container.HandCards;

public class TrashHandCard extends PlayerAction{
    public TrashHandCard(Player player, Card card) {
        this.player = player;
        trashedCard = card;
    }

    private Card trashedCard;

    @Override
    public void perform(HandCards handCards, Deck deck, DiscardPile discardPile, FieldCards fieldCards) {
        LogBox.logTrashCard(player, trashedCard);
        handCards.removeCard(trashedCard);
        trashedCard.disableUi();
    }
}
