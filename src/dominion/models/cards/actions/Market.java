package dominion.models.cards.actions;

import dominion.models.player.Player;
import dominion.models.cards.Card;
import dominion.utils.CardStyles;
import dominion.utils.CardTypes;

public class Market extends Card implements Action {
    // Constructor
    public Market() {
        name = "市集";
        description = "+1卡片\n+1行動\n+1購買\n+1塊錢";
        style = CardStyles.white;
        type = CardTypes.action;
        numCost = 5;
    }

    // Functions
    @Override
    public void perform(Player performer, boolean decreaseNumActions) {
        performer.drawCards(1);
        performer.increaseNumActions(2);
        performer.increaseNumPurchases(1);
        performer.increaseNumCoins(1);

        if(decreaseNumActions) {
            performer.decreaseNumActions();
        }
        performer.checkActionCardsAndEndPlayingActionPhase();
    }
}
