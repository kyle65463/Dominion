package dominion.models.cards.actions;

import dominion.models.expansions.Dominion;
import dominion.models.player.Player;
import dominion.models.cards.Card;
import dominion.models.cards.CardStyles;
import dominion.models.cards.CardTypes;
import dominion.models.player.PlayerAction.DrawCards;

public class Smithy extends Card implements Dominion, Action {
    // Constructor
    public Smithy() {
        name = "鐵匠";
        description = "+3 卡片";
        style = CardStyles.white;
        type = CardTypes.action;
        numCost = 4;
    }

    // Functions
    @Override
    public void perform(Player performer, boolean decreaseNumActions) {
        performer.performAction(new DrawCards(3));

        if(decreaseNumActions) {
            performer.decreaseNumActions();
        }
        doNextMove();
    }
}
