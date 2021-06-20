package dominion.models.game.cards.actions;

import dominion.models.game.Player;
import dominion.models.game.cards.Card;
import dominion.utils.CardStyles;
import dominion.utils.CardTypes;

public class Village extends Card implements Action {
    // Constructor
    public Village() {
        name = "村莊";
        description = "";
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
