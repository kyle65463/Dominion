package dominion.models.cards.actions;

import dominion.core.GameManager;
import dominion.models.areas.DisplayedCard;
import dominion.models.cards.Card;
import dominion.models.cards.CardStyles;
import dominion.models.cards.CardTypes;
import dominion.models.cards.treasures.Treasure;
import dominion.models.cards.victories.Victory;
import dominion.models.events.game.HasDisplayedCardsSelection;
import dominion.models.player.Player;

import java.util.List;

public class IronWorks extends Card implements Action, HasDisplayedCardsSelection {
    // Constructor
    public IronWorks() {
        name = "打鐵鋪";
        description = "獲得一張價值不高於4的卡\n如果獲得的卡片類型包含\n行動卡，+1 行動\n錢幣卡，+1 塊錢\n分數卡，+1 卡片";
        style = CardStyles.white;
        type = CardTypes.action;
        numCost = 4;
    }

    // Variables
    private boolean decreaseNumActions = true;

    // Functions
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
        // Add the card to hand cards
        if(displayedCards.size() > 0) {
            DisplayedCard displayedCard = displayedCards.get(0);
            Card card = displayedCard.instantiateNewCard();
            performer.receiveNewHandCard(card);
            displayedCard.decreaseNumRemain();

            if(card instanceof Action){
                performer.increaseNumActions(1);
            }
            if(card instanceof Treasure){
                performer.increaseNumCoins(1);
            }
            if(card instanceof Victory){
                performer.drawCards(1);
            }
        }

        if(decreaseNumActions) {
            performer.decreaseNumActions();
        }
        decreaseNumActions = true;
        performer.checkActionCardsAndEndPlayingActionPhase();
    }
}
