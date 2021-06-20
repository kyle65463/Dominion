package dominion.models.game.cards.actions;

import dominion.models.game.Player;
import dominion.models.game.cards.Card;
import dominion.utils.CardStyles;
import dominion.utils.CardTypes;

public class Smithy extends Card implements Action {
    // Constructor
    public Smithy() {
        name = "鐵匠";
        description = "";
        style = CardStyles.white;
        type = CardTypes.action;
        numCost = 4;
    }

    // Functions
    @Override
    public void perform(Player performer) {
        performer.drawCards(3);

        performer.decreaseNumActions();
        performer.checkActionCardsAndEndPlayingActionPhase();
    }
}
