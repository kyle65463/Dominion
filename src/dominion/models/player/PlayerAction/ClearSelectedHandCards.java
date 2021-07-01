package dominion.models.player.PlayerAction;

import dominion.models.cards.Card;
import dominion.models.player.Player;
import dominion.models.player.container.Deck;
import dominion.models.player.container.DiscardPile;
import dominion.models.player.container.FieldCards;
import dominion.models.player.container.HandCards;

import java.util.List;

public class ClearSelectedHandCards extends PlayerAction {
    public ClearSelectedHandCards() {

    }


    @Override
    public void perform(Player player, HandCards handCards, Deck deck, DiscardPile discardPile, FieldCards fieldCards) {
        List<Card> selectedCards = player.getSelectedCards();
        selectedCards.clear();
        handCards.rearrange();
        player.enableLeftButton(false);
        if (player.getExactSelectedCards() > 0) {
            player.enableRightButton(selectedCards.size() == player.getExactSelectedCards());
        }
    }
}
