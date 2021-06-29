package dominion.models.game.cards.actions;

import dominion.game.GameManager;
import dominion.models.events.game.DoneAttackingEvent;
import dominion.models.game.DisplayedCard;
import dominion.models.game.Player;
import dominion.models.game.cards.Card;
import dominion.models.game.cards.AttackPlayers;
import dominion.models.game.cards.curses.Curse;
import dominion.utils.CardStyles;
import dominion.utils.CardTypes;

public class Witch extends Card implements Action, Attack {
    public Witch() {
        name = "女巫";
        description = "";
        style = CardStyles.white;
        type = CardTypes.action;
        numCost = 5;
    }

    @Override
    public void perform(Player performer, boolean decreaseNumActions) {
        Thread thread = new Thread(new AttackPlayers(performer, this, decreaseNumActions));
        thread.start();
    }

    @Override
    public void performAttack(Player performer, Player attacked) {
        Card curse = new Curse();
        DisplayedCard card = GameManager.getDisplayCardByCard(curse);
        if (card.getNumRemain() > 0) {
            attacked.receiveNewCard(curse);
            card.decreaseNumRemain();
        }
        GameManager.sendEvent(new DoneAttackingEvent(attacked.getId()));
    }

    @Override
    public void performAfterAttack(Player performer) {
        performer.drawCards(2);
    }
}