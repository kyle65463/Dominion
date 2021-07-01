package dominion.models.player.PlayerAction;

import dominion.models.cards.Card;
import dominion.models.cards.actions.HasHandCardsSelection;
import dominion.models.player.Player;
import dominion.models.player.container.Deck;
import dominion.models.player.container.DiscardPile;
import dominion.models.player.container.FieldCards;
import dominion.models.player.container.HandCards;

public class DoneHandCardsSelection extends PlayerAction {
    public DoneHandCardsSelection(Player player, int cardId) {
        this.player = player;
        this.cardId = cardId;
    }

    private int cardId;


    @Override
    public void perform(HandCards handCards, Deck deck, DiscardPile discardPile, FieldCards fieldCards) {
        for (Card card : player.getSelectedCards()) {
            card.removeHighlight();
        }
        player.setMaxSelectedCards(Integer.MAX_VALUE);
        player.setExactSelectedCards(0);
        player.enableRightButton(true);
        player.enableLeftButton(false);
        player.setSelectingHandCardsFilter(null);
        player.recoverStatus();

        HasHandCardsSelection card = (HasHandCardsSelection) fieldCards.getCardByCardId(cardId);
        card.performSelection(player, player.getSelectedCards());
        player.clearSelectedHandCards();
    }
}
