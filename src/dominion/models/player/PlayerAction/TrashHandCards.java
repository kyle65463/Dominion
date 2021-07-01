package dominion.models.player.PlayerAction;

import dominion.models.areas.LogBox;
import dominion.models.cards.Card;
import dominion.models.player.Player;
import dominion.models.player.container.Deck;
import dominion.models.player.container.DiscardPile;
import dominion.models.player.container.FieldCards;
import dominion.models.player.container.HandCards;

import java.util.List;

public class TrashHandCards extends PlayerAction{
    public TrashHandCards(List<Card> cards) {
        this.trashedCards = cards;
    }

    private List<Card> trashedCards;

    @Override
    public void perform(Player player, HandCards handCards, Deck deck, DiscardPile discardPile, FieldCards fieldCards) {
        for (Card card : trashedCards) {
            LogBox.logTrashCard(player, card);
        }
        handCards.removeCards(trashedCards);
        for (Card card : trashedCards) {
            card.disableUi();
        }
    }
}
