package dominion.models.game.cards.actions;

import dominion.models.game.Player;
import dominion.models.game.cards.Card;
import dominion.utils.CardStyles;
import dominion.utils.CardTypes;

public class Laboratory extends Card implements Action {
    // Constructor
    public Laboratory() {
        name = "實驗室";
        description = "+2卡片\n+1行動";
        style = CardStyles.white;
        type = CardTypes.action;
        numCost = 5;
    }

    // Functions
    @Override
    public void perform(Player performer, boolean decreaseNumActions) {
        performer.drawCards(2);
        performer.increaseNumActions(1);

        if(decreaseNumActions) {
            performer.decreaseNumActions();
        }
        performer.checkActionCardsAndEndPlayingActionPhase();
    }
}
