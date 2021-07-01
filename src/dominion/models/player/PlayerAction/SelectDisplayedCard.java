package dominion.models.player.PlayerAction;

import dominion.core.GameManager;
import dominion.models.areas.DisplayedCard;
import dominion.models.areas.PurchaseArea;
import dominion.models.player.Player;
import dominion.models.player.container.Deck;
import dominion.models.player.container.DiscardPile;
import dominion.models.player.container.FieldCards;
import dominion.models.player.container.HandCards;

import java.util.List;

public class SelectDisplayedCard extends PlayerAction {
    public SelectDisplayedCard(Player player, int displayedCardId) {
        this.player = player;
        this.displayedCardId = displayedCardId;
    }

    private int displayedCardId;

    @Override
    public void perform(HandCards handCards, Deck deck, DiscardPile discardPile, FieldCards fieldCards) {
        List<DisplayedCard> selectedDisplayedCards = player.getSelectedDisplayedCards();
        if (GameManager.getCurrentPhase() == GameManager.Phase.SelectingDisplayedCards) {
            DisplayedCard displayedCard = PurchaseArea.getDisplayedCardById(displayedCardId);
            if (!selectedDisplayedCards.contains(displayedCard) && selectedDisplayedCards.size() < player.getMaxSelectedCards()) {
                if (player.getId() == GameManager.getApplicationPlayer().getId() && displayedCard != null) {
                    displayedCard.setHighlight();
                }
                selectedDisplayedCards.add(displayedCard);
            }
            player.enableLeftButton(selectedDisplayedCards.size() > 0);
            if (player.getExactSelectedCards() > 0) {
                player.enableRightButton(selectedDisplayedCards.size() == player.getExactSelectedCards());
            }
        }
    }
}
