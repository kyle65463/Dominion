package dominion.models.game.cards.actions;

import dominion.models.game.Player;
import dominion.models.game.cards.Card;
import dominion.utils.CardStyles;
import dominion.utils.CardTypes;

public class Festival extends Card implements Action {
    // Constructor
    public Festival() {
        name = "慶典";
        description = "+2行動\n+1購買\n+2塊錢";
        style = CardStyles.white;
        type = CardTypes.action;
        numCost = 5;
    }

    // Functions
    @Override
    public void perform(Player performer, boolean decreaseNumActions) {
        performer.increaseNumActions(2);
        performer.increaseNumPurchases(1);
        performer.increaseNumCoins(2);

        if(decreaseNumActions) {
            performer.decreaseNumActions();
        }
        performer.checkActionCardsAndEndPlayingActionPhase();
    }
}
