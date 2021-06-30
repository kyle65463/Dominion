package dominion.models.cards.actions;

import dominion.core.GameManager;
import dominion.models.player.Player;
import dominion.models.cards.Card;
import dominion.utils.CardStyles;
import dominion.utils.CardTypes;

import java.util.List;

public class Chapel extends Card implements Action, HasSelection {
    // Constructor
    public Chapel() {
        name = "禮拜堂";
        description = "移除你手上至多4張卡牌。";
        style = CardStyles.white;
        type = CardTypes.action;
        numCost = 2;
    }

    // Variables
    private boolean decreaseNumActions = true;

    // Functions
    @Override
    public void perform(Player performer, boolean decreaseNumActions) {
        this.decreaseNumActions = decreaseNumActions;
        GameManager.setCurrentPhase(GameManager.Phase.SelectingHandCards);
        performer.startSelectingHandCards("選擇要移除的牌", id);
        performer.setMaxSelectingCards(4);
    }

    @Override
    public void performSelection(Player performer, List<Card> cards) {
        performer.trashHandCards(cards);

        if (decreaseNumActions) {
            performer.decreaseNumActions();
        }
        decreaseNumActions = true;
        performer.checkActionCardsAndEndPlayingActionPhase();
    }
}
