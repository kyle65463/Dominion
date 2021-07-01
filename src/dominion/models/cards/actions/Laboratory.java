package dominion.models.cards.actions;

import dominion.models.expansions.Dominion;
import dominion.models.player.Player;
import dominion.models.cards.Card;
import dominion.models.cards.CardStyles;
import dominion.models.cards.CardTypes;
import dominion.models.player.PlayerAction.DrawCards;

public class Laboratory extends Card implements Dominion, Action {
    // Constructor
    public Laboratory() {
        name = "實驗室";
        description = "+2 卡片\n+1 行動";
        style = CardStyles.white;
        type = CardTypes.action;
        numCost = 5;
    }

    // Functions
    @Override
    public void perform(Player performer, boolean decreaseNumActions) {
        performer.performPlayerAction(new DrawCards(2));
        performer.increaseNumActions(1);

        if(decreaseNumActions) {
            performer.decreaseNumActions();
        }
        doNextMove();
    }
}
