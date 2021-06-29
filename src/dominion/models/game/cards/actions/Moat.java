package dominion.models.game.cards.actions;

import dominion.models.game.Player;
import dominion.models.game.cards.Card;
import dominion.utils.CardStyles;
import dominion.utils.CardTypes;

public class Moat extends Card implements Action, Reaction{
    public Moat() {
        name = "護城河";
        description = "";
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
        performer.checkActionCardsAndEndPlayingActionPhase();
    }

    @Override
    public void performReaction(Player performer) {
        performer.setImmuneNextAttack(true);
    }
}
