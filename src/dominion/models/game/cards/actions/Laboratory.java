package dominion.models.game.cards.actions;

import dominion.models.game.Player;
import dominion.models.game.cards.Card;
import dominion.utils.CardStyles;
import dominion.utils.CardTypes;

public class Laboratory extends Card implements Action {
    // Constructor
    public Laboratory() {
        name = "實驗室";
        description = "";
        style = CardStyles.white;
        type = CardTypes.action;
        numCost = 5;
    }

    // Functions
    @Override
    public void perform(Player performer) {
        performer.drawCards(2);
        performer.increaseNumActions(1);
    }
}
