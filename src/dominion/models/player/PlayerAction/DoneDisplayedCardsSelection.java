package dominion.models.player.PlayerAction;

import dominion.models.areas.DisplayedCard;
import dominion.models.areas.PurchaseArea;
import dominion.models.events.game.HasDisplayedCardsSelection;
import dominion.models.player.Player;
import dominion.models.player.container.Deck;
import dominion.models.player.container.DiscardPile;
import dominion.models.player.container.FieldCards;
import dominion.models.player.container.HandCards;

import java.util.List;

public class DoneDisplayedCardsSelection extends PlayerAction {
    public DoneDisplayedCardsSelection(int cardId) {
        this.cardId = cardId;
    }

    private int cardId;

    @Override
    public void perform(Player player, HandCards handCards, Deck deck, DiscardPile discardPile, FieldCards fieldCards) {
        List<DisplayedCard> selectedDisplayedCards = player.getSelectedDisplayedCards();
        for (DisplayedCard displayedCard : selectedDisplayedCards) {
            displayedCard.removeHighlight();
        }
        player.setMaxSelectedCards(Integer.MAX_VALUE);
        player.setExactSelectedCards(0);
        player.setSelectingDisplayedCardsFilter(null);
        player.enableRightButton(true);
        player.enableLeftButton(false);
        PurchaseArea.setDisplayedCardSelectedHandler((displayedCard) -> {
            displayedCard.setDisplayCardEventHandler(displayedCard.getOriginalHandler());
        });
        player.recoverStatus();
        HasDisplayedCardsSelection card = (HasDisplayedCardsSelection) fieldCards.getCardByCardId(cardId);
        card.performDisplayedSelection(player, selectedDisplayedCards);
        player.performAction(new ClearSelectedDisplayedCards());
    }
}
