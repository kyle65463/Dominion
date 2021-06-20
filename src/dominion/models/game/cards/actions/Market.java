package dominion.models.game.cards.actions;

import dominion.models.game.Player;
import dominion.models.game.cards.Card;
import dominion.utils.CardStyles;
import dominion.utils.CardTypes;

public class Market extends Card implements Action {
    // Constructor
    public Market() {
        name = "市集";
        description = "";
        style = CardStyles.white;
        type = CardTypes.action;
        numCost = 5;
    }

    // Functions
    @Override
    public void perform(Player performer) {
        performer.drawCards(1);
        performer.increaseNumActions(2);
        performer.increaseNumPurchases(1);
        performer.increaseNumCoins(1);

        performer.decreaseNumActions();
        performer.checkActionCardsAndEndPlayingActionPhase();
    }
}
