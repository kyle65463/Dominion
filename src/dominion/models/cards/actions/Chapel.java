package dominion.models.cards.actions;

import dominion.core.GameManager;
import dominion.models.expansions.Dominion;
import dominion.models.player.Player;
import dominion.models.cards.Card;
import dominion.models.cards.CardStyles;
import dominion.models.cards.CardTypes;
import dominion.models.player.PlayerAction.StartSelectingHandCards;
import dominion.models.player.PlayerAction.TrashHandCards;

import java.util.List;

public class Chapel extends Card implements Dominion, Action, HasHandCardsSelection {
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
        performer.setMaxSelectedCards(4);
        performer.performAction(new StartSelectingHandCards("選擇要移除的牌", id));
    }

    @Override
    public void performSelection(Player performer, List<Card> cards) {
        performer.performAction(new TrashHandCards(cards));

        if (decreaseNumActions) {
            performer.decreaseNumActions();
        }
        decreaseNumActions = true;
        doNextMove();
    }
}
