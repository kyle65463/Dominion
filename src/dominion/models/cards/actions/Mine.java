package dominion.models.cards.actions;

import dominion.core.GameManager;
import dominion.models.areas.DisplayedCard;
import dominion.models.cards.Card;
import dominion.models.cards.CardStyles;
import dominion.models.cards.CardTypes;
import dominion.models.cards.treasures.Treasure;
import dominion.models.events.game.HasDisplayedCardsSelection;
import dominion.models.expansions.Dominion;
import dominion.models.player.Player;

import java.util.List;

public class Mine extends Card implements Dominion, Action, HasHandCardsSelection, HasDisplayedCardsSelection {
    public Mine() {
        name = "礦坑";
        description = "你可以移除手上的一張錢幣卡，獲得一張價值至多加3塊的錢幣卡到手中。" ;
        style = CardStyles.white;
        type = CardTypes.action;
        numCost = 2;
    }

    private boolean decreaseNumActions;

    @Override
    public void perform(Player performer, boolean decreaseNumActions) {
        this.decreaseNumActions = decreaseNumActions;
        GameManager.setCurrentPhase(GameManager.Phase.SelectingHandCards);
        performer.setExactSelectingCards(1);
        performer.startSelectingHandCards("選擇要移除的錢幣卡", id);
    }

    @Override
    public void performSelection(Player performer, List<Card> cards) {
        if (cards.size() > 0) {
            Card card = cards.get(0);
            performer.trashHandCards(cards);

            GameManager.setCurrentPhase(GameManager.Phase.SelectingDisplayedCards);
            performer.setExactSelectingCards(1);
            performer.setSelectingDisplayedCardsFilter( (displayedCard) -> displayedCard.getCard().getNumCost() <= 3 + card.getNumCost() && displayedCard.getCard() instanceof Treasure);
            performer.startSelectingDisplayedCards("選擇要加到手牌的錢幣卡", id);
        } else {
            if (decreaseNumActions) {
                performer.decreaseNumActions();
            }
            doNextMove();
        }
    }

    @Override
    public void performDisplayedSelection(Player performer, List<DisplayedCard> displayedCards) {
        if (displayedCards.size() > 0) {
            DisplayedCard displayedCard = displayedCards.get(0);
            Card card = displayedCard.getCard();
            performer.receiveNewHandCard(card);
            displayedCard.decreaseNumRemain();
        }

        if (decreaseNumActions) {
            performer.decreaseNumActions();
        }
        doNextMove();
    }
}
