package dominion.models.player.PlayerAction;

import dominion.core.GameManager;
import dominion.models.cards.Card;
import dominion.models.player.Player;
import dominion.models.player.container.Deck;
import dominion.models.player.container.DiscardPile;
import dominion.models.player.container.FieldCards;
import dominion.models.player.container.HandCards;

import java.util.List;

public class SelectHandCard extends PlayerAction {
    public SelectHandCard(Player player, int cardId) {
        this.player = player;
        this.cardId = cardId;
    }

    private int cardId;

    @Override
    public void perform(HandCards handCards, Deck deck, DiscardPile discardPile, FieldCards fieldCards) {
        if (GameManager.getCurrentPhase() == GameManager.Phase.SelectingHandCards) {
            Card card = handCards.getCardByCardId(cardId);
            List<Card> selectedCards = player.getSelectedCards();
            if (!selectedCards.contains(card) && selectedCards.size() < player.getMaxSelectedCards()) {
                card.setHighlight();
                selectedCards.add(card);
                handCards.setToBottom(card);
            }
            player.enableLeftButton(selectedCards.size() > 0);
            if (player.getExactSelectedCards() > 0) {
                player.enableRightButton(selectedCards.size() == player.getExactSelectedCards());
            }
        }
    }
}
