package dominion.models.cards.actions;

import dominion.core.GameManager;
import dominion.models.areas.PurchaseArea;
import dominion.models.cards.Card;
import dominion.models.cards.CardStyles;
import dominion.models.cards.CardTypes;
import dominion.models.expansions.Dominion;
import dominion.models.player.Player;

import java.util.List;

public class Poacher extends Card implements Dominion, Action, HasHandCardsSelection {
    public Poacher() {
        name = "盜獵者";
        description = "+1 卡片\n+1 行動\n+1 塊錢\n\n供應區每有一張空的牌堆，你就必須棄掉一張手牌。";
        style = CardStyles.white;
        type = CardTypes.action;
        numCost = 4;
    }

    private boolean decreaseNumActions;

    @Override
    public void perform(Player performer, boolean decreaseNumActions) {
        this.decreaseNumActions = decreaseNumActions;
        performer.drawCards(1);
        performer.increaseNumActions(1);
        performer.increaseNumCoins(1);

        if (PurchaseArea.getNumNoneRemained() > 0) {
            GameManager.setCurrentPhase(GameManager.Phase.SelectingHandCards);
            int selectedNum = Math.min(PurchaseArea.getNumNoneRemained(), performer.getHandCards().size());
            performer.setExactSelectedCards(selectedNum);
            performer.startSelectingHandCards("選擇要棄掉的牌", id);
        } else {
            if (decreaseNumActions) {
                performer.decreaseNumActions();
            }
            doNextMove();
        }
    }

    @Override
    public void performSelection(Player performer, List<Card> cards) {
        if (cards.size() > 0) {
            performer.discardHandCards(cards);
        }

        if(decreaseNumActions) {
            performer.decreaseNumActions();
        }
        doNextMove();
    }
}
