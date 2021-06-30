package dominion.models.cards.actions;

import dominion.models.player.Player;
import dominion.models.cards.Card;
import dominion.models.cards.CardStyles;
import dominion.models.cards.CardTypes;

public class Smithy extends Card implements Action {
    // Constructor
    public Smithy() {
        name = "鐵匠";
        description = "+3卡片";
        style = CardStyles.white;
        type = CardTypes.action;
        numCost = 4;
    }

    // Functions
    @Override
    public void perform(Player performer, boolean decreaseNumActions) {
        performer.drawCards(3);

        if(decreaseNumActions) {
            performer.decreaseNumActions();
        }
        performer.checkActionCardsAndEndPlayingActionPhase();
    }
}
