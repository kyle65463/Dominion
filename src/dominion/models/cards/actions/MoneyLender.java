package dominion.models.cards.actions;

import dominion.core.GameManager;
import dominion.models.player.Player;
import dominion.models.cards.Card;
import dominion.models.cards.treasures.Copper;
import dominion.models.cards.CardStyles;
import dominion.models.cards.CardTypes;

import java.util.List;

public class MoneyLender extends Card implements Action, HasSelection{
    // Constructor
    public MoneyLender() {
        name = "錢莊";
        description = "你可以移除手上一張銅幣換取3塊錢。";
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
        GameManager.setCurrentPhase(GameManager.Phase.SelectingHandCards);
        performer.startSelectingHandCards("選擇要移除的牌", id);
        performer.setMaxSelectingHandCards(1);
        performer.setSelectingHandCardsFilter(card -> card instanceof Copper);
    }

    @Override
    public void performSelection(Player performer, List<Card> cards) {
        if(cards.size() > 0) {
            performer.trashHandCards(cards);
            performer.increaseNumCoins(3);
        }

        if(decreaseNumActions) {
            performer.decreaseNumActions();
        }
        decreaseNumActions = true;
        performer.checkActionCardsAndEndPlayingActionPhase();
    }
}
