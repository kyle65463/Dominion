package dominion.models.cards.actions;

import dominion.models.cards.Card;
import dominion.models.cards.CardStyles;
import dominion.models.cards.CardTypes;
import dominion.models.expansions.SeaSide;
import dominion.models.player.Player;
import dominion.models.player.PlayerAction.DrawCards;

public class Bazaar extends Card implements SeaSide, Action {
    // Constructor
    public Bazaar() {
        name = "趕集";
        description = "+1 卡片\n+2 行動\n+1 塊錢";
        style = CardStyles.white;
        type = CardTypes.action;
        numCost = 5;
    }

    // Functions
    @Override
    public void perform(Player performer, boolean decreaseNumActions) {
        performer.performPlayerAction(new DrawCards(1));
        performer.increaseNumActions(2);
        performer.increaseNumCoins(1);

        if(decreaseNumActions) {
            performer.decreaseNumActions();
        }
        doNextMove();
    }
}