package dominion.models.cards.actions;

import dominion.core.GameManager;
import dominion.models.areas.DisplayedCard;
import dominion.models.cards.Card;
import dominion.models.cards.CardStyles;
import dominion.models.cards.CardTypes;
import dominion.models.events.game.HasDisplayedCardsSelection;
import dominion.models.player.Player;

import java.util.List;

public class Workshop extends Card implements Action, HasDisplayedCardsSelection {
    // Constructor
    public Workshop() {
        name = "工作室";
        description = "獲得一張價值至多4的卡片到手中";
        style = CardStyles.white;
        type = CardTypes.action;
        numCost = 3;
    }

    private boolean decreaseNumActions;

    @Override
    public void perform(Player performer, boolean decreaseNumActions) {
        this.decreaseNumActions = decreaseNumActions;
        GameManager.setCurrentPhase(GameManager.Phase.SelectingDisplayedCards);
        performer.setMaxSelectingCards(1);
        performer.setSelectingDisplayedCardsFilter(displayedCard -> displayedCard.getCard().getNumCost() <= 4);
        performer.startSelectingDisplayedCards("選擇要加到手牌的牌", id);
    }

    @Override
    public void performDisplayedSelection(Player performer, List<DisplayedCard> displayedCards) {
        if(displayedCards.size() > 0) {
            DisplayedCard displayedCard = displayedCards.get(0);
            Card card = displayedCard.instantiateNewCard();
            performer.receiveNewHandCard(card);
            displayedCard.decreaseNumRemain();
        }

        if (decreaseNumActions) {
            performer.decreaseNumActions();
        }
        doNextMove();
    }
}
