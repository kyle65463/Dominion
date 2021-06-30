package dominion.models.cards.actions;

import dominion.models.player.Player;
import dominion.models.cards.Card;
import dominion.utils.CardStyles;
import dominion.utils.CardTypes;

public class Village extends Card implements Action {
    // Constructor
    public Village() {
        name = "村莊";
        description = "+1卡片\n+2行動";
        style = CardStyles.white;
        type = CardTypes.action;
        numCost = 3;
    }

    // Functions
    @Override
    public void perform(Player performer, boolean decreaseNumActions) {
        performer.drawCards(1);
        performer.increaseNumActions(2);

        if(decreaseNumActions) {
            performer.decreaseNumActions();
        }
        performer.checkActionCardsAndEndPlayingActionPhase();
    }
}
