package dominion.models.cards.actions;

import dominion.core.GameManager;
import dominion.models.areas.PurchaseArea;
import dominion.models.events.game.DoneAttackingEvent;
import dominion.models.areas.DisplayedCard;
import dominion.models.expansions.Dominion;
import dominion.models.player.Player;
import dominion.models.cards.Card;
import dominion.models.cards.AttackPlayers;
import dominion.models.cards.curses.Curse;
import dominion.models.cards.CardStyles;
import dominion.models.cards.CardTypes;
import dominion.models.player.PlayerAction.DrawCards;
import dominion.models.player.PlayerAction.ReceiveNewCard;

public class Witch extends Card implements Dominion, Action, Attack {
    public Witch() {
        name = "女巫";
        description = "+2 卡片\n\n其他人獲得一張詛咒。";
        style = CardStyles.white;
        type = CardTypes.action;
        numCost = 5;
    }

    boolean decreaseNumActions;

    @Override
    public void perform(Player performer, boolean decreaseNumActions) {
        performer.performAction(new DrawCards(2));
        this.decreaseNumActions = decreaseNumActions;
        Thread thread = new Thread(new AttackPlayers(performer, this));
        thread.start();
    }

    @Override
    public void performAttack(Player performer, Player attacked) {
        Card curse = new Curse();
        DisplayedCard card = PurchaseArea.getDisplayedCardByCard(curse);
        if (card.getNumRemain() > 0) {
            attacked.performAction(new ReceiveNewCard(curse));
            card.decreaseNumRemain();
        }
        GameManager.sendEvent(new DoneAttackingEvent(attacked.getId()));
    }

    @Override
    public void performAfterAttack(Player performer) {
        if (decreaseNumActions) {
            performer.decreaseNumActions();
        }
        doNextMove();
    }
}