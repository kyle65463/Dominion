package dominion.models.cards.actions;

import dominion.core.GameManager;
import dominion.models.player.Player;
import dominion.models.cards.Card;
import dominion.models.cards.CardStyles;
import dominion.models.cards.CardTypes;

import java.util.List;

public class WareHouse extends Card implements Action, HasHandCardsSelection {
    // Constructor
    public WareHouse() {
        name = "倉庫";
        description = "+3 卡片\n+1 行動\n\n棄掉三張卡片。";
        style = CardStyles.white;
        type = CardTypes.action;
        numCost = 3;
    }

    // Variables
    private boolean decreaseNumActions = true;

    // Functions
    @Override
    public void perform(Player performer, boolean decreaseNumActions) {
        this.decreaseNumActions = decreaseNumActions;
        performer.increaseNumActions(1);
        performer.drawCards(3);
        if(performer.getHandCards().size() > 3) {
            GameManager.setCurrentPhase(GameManager.Phase.SelectingHandCards);
            performer.setExactSelectingCards(3);
            performer.startSelectingHandCards("選擇要棄掉的牌", id);
        }
        else{
            performer.discardAllHandCards();
            if (decreaseNumActions) {
                performer.decreaseNumActions();
            }
            doNextMove();
        }
    }

    @Override
    public void performSelection(Player performer, List<Card> cards) {
        performer.discardHandCards(cards);

        if (decreaseNumActions) {
            performer.decreaseNumActions();
        }
        decreaseNumActions = true;
        doNextMove();
    }
}
