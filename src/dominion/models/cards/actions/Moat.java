package dominion.models.cards.actions;

import dominion.models.expansions.Dominion;
import dominion.models.player.Player;
import dominion.models.cards.Card;
import dominion.models.cards.CardStyles;
import dominion.models.cards.CardTypes;

public class Moat extends Card implements Dominion, Action, Reaction{
    public Moat() {
        name = "護城河";
        description = "+2 卡片\n\n當其他玩家打出攻擊卡時，你可以先展示手上的此卡，來無效對你的影響。";
        style = CardStyles.blue;
        type = CardTypes.action;
        numCost = 2;
    }

    @Override
    public void perform(Player performer, boolean decreaseNumActions) {
        performer.drawCards(2);
        if(decreaseNumActions) {
            performer.decreaseNumActions();
        }
        doNextMove();
    }

    @Override
    public void performReaction(Player performer) {
        performer.setImmuneNextAttack(true);
    }
}
