package dominion.models.player.PlayerAction;

import dominion.models.areas.DisplayedCard;
import dominion.models.areas.PurchaseArea;
import dominion.models.player.Player;
import dominion.models.player.container.Deck;
import dominion.models.player.container.DiscardPile;
import dominion.models.player.container.FieldCards;
import dominion.models.player.container.HandCards;

import java.util.List;

public class ClearSelectedDisplayedCards extends PlayerAction {
    public ClearSelectedDisplayedCards() {

    }


    @Override
    public void perform(Player player, HandCards handCards, Deck deck, DiscardPile discardPile, FieldCards fieldCards) {
        List<DisplayedCard> selectedDisplayedCards = player.getSelectedDisplayedCards();
        selectedDisplayedCards.clear();
        PurchaseArea.rearrange();
        player.enableLeftButton(false);
        if (player.getExactSelectedCards() > 0) {
            player.enableRightButton(selectedDisplayedCards.size() == player.getExactSelectedCards());
        }
    }
}
