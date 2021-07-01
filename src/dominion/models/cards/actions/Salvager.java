package dominion.models.cards.actions;

import dominion.core.GameManager;
import dominion.models.cards.Card;
import dominion.models.cards.CardStyles;
import dominion.models.cards.CardTypes;
import dominion.models.expansions.SeaSide;
import dominion.models.player.Player;
import dominion.models.player.PlayerAction.StartSelectingHandCards;
import dominion.models.player.PlayerAction.TrashHandCards;

import java.util.List;

public class Salvager extends Card implements SeaSide, Action, HasHandCardsSelection {
    // Constructor
    public Salvager() {
        name = "打撈員";
        description = "移除手上的一張卡片，那張卡片每價值1，你就加1塊錢。";
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
        performer.increaseNumPurchases(1);
        GameManager.setCurrentPhase(GameManager.Phase.SelectingHandCards);
        performer.setMaxSelectedCards(1);
        performer.performAction(new StartSelectingHandCards("選擇要移除的牌", id));
    }

    @Override
    public void performSelection(Player performer, List<Card> cards) {
        if(cards.size() > 0) {
            Card card = cards.get(0);
            performer.increaseNumCoins(card.getNumCost());
            performer.performAction(new TrashHandCards(cards));
        }

        if (decreaseNumActions) {
            performer.decreaseNumActions();
        }
        decreaseNumActions = true;
        performer.checkActionCardsAndEndPlayingActionPhase();
    }
}
