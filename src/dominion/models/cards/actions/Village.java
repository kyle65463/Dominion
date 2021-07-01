package dominion.models.cards.actions;

import dominion.models.expansions.Dominion;
import dominion.models.player.Player;
import dominion.models.cards.Card;
import dominion.models.cards.CardStyles;
import dominion.models.cards.CardTypes;
import dominion.models.player.PlayerAction.DrawCards;

public class Village extends Card implements Dominion, Action {
    // Constructor
    public Village() {
        name = "村莊";
        description = "+1 卡片\n+2 行動";
        style = CardStyles.white;
        type = CardTypes.action;
        numCost = 3;
    }

    // Functions
    @Override
    public void perform(Player performer, boolean decreaseNumActions) {
        performer.performPlayerAction(new DrawCards(1));
        performer.increaseNumActions(2);

        if(decreaseNumActions) {
            performer.decreaseNumActions();
        }
        doNextMove();
    }
}
