package dominion.models.cards.actions;

import dominion.core.GameManager;
import dominion.models.expansions.Dominion;
import dominion.models.player.Player;
import dominion.models.cards.Card;
import dominion.models.cards.treasures.Copper;
import dominion.models.cards.CardStyles;
import dominion.models.cards.CardTypes;
import dominion.models.player.PlayerAction.StartSelectingHandCards;
import dominion.models.player.PlayerAction.TrashHandCards;

import java.util.List;

public class MoneyLender extends Card implements Dominion, Action, HasHandCardsSelection {
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
        performer.setMaxSelectedCards(1);
        performer.performAction(new StartSelectingHandCards("選擇要移除的牌", id));
        performer.setSelectingHandCardsFilter(card -> card instanceof Copper);
    }

    @Override
    public void performSelection(Player performer, List<Card> cards) {
        if(cards.size() > 0) {
            performer.performAction(new TrashHandCards(cards));
            performer.increaseNumCoins(3);
        }

        if(decreaseNumActions) {
            performer.decreaseNumActions();
        }
        decreaseNumActions = true;
        doNextMove();
    }
}
