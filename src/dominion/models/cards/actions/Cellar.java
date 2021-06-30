package dominion.models.cards.actions;

import dominion.core.GameManager;
import dominion.models.player.Player;
import dominion.models.cards.Card;
import dominion.models.cards.CardStyles;
import dominion.models.cards.CardTypes;

import java.util.List;

public class Cellar extends Card implements Action, HasHandCardsSelection {
    // Constructor
    public Cellar() {
        name = "地窖";
        description = "+1 行動\n\n棄掉任意張數的卡牌，抽取相同數量的卡牌。";
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
        performer.startSelectingHandCards("選擇要棄掉的牌", id);
    }

    @Override
    public void performSelection(Player performer, List<Card> cards) {
        performer.discardHandCards(cards);
        performer.drawCards(cards.size());
        performer.increaseNumActions(1);

        if (decreaseNumActions) {
            performer.decreaseNumActions();
        }
        decreaseNumActions = true;
        performer.checkActionCardsAndEndPlayingActionPhase();
    }
}
