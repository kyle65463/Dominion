package dominion.models.cards.actions;

import dominion.core.GameManager;
import dominion.models.areas.PurchaseArea;
import dominion.models.events.game.DoneAttackingEvent;
import dominion.models.areas.DisplayedCard;
import dominion.models.expansions.SeaSide;
import dominion.models.player.Player;
import dominion.models.cards.Card;
import dominion.models.cards.AttackPlayers;
import dominion.models.cards.curses.Curse;
import dominion.models.cards.CardStyles;
import dominion.models.cards.CardTypes;
import dominion.models.player.PlayerAction.AddCardsToDiscardPile;
import dominion.models.player.PlayerAction.ReceiveNewCardOnDeck;

import java.util.List;

public class SeaHag extends Card implements SeaSide, Action, Attack {
    public SeaHag() {
        name = "海巫";
        description = "其他所有玩家棄掉牌庫頂的一張卡片，他們獲得一張詛咒並放在牌庫頂。";
        style = CardStyles.white;
        type = CardTypes.action;
        numCost = 4;
    }

    boolean decreaseNumActions;

    @Override
    public void perform(Player performer, boolean decreaseNumActions) {
        this.decreaseNumActions = decreaseNumActions;
        Thread thread = new Thread(new AttackPlayers(performer, this));
        thread.start();
    }

    @Override
    public void performAttack(Player performer, Player attacked) {
        // Pop a card from deck
        List<Card> cards = attacked.popDeckTop(1);
        attacked.performPlayerAction(new AddCardsToDiscardPile(cards));

        // Get a curse
        Card curse = new Curse();
        DisplayedCard card = PurchaseArea.getDisplayedCardByCard(curse);
        if (card.getNumRemain() > 0) {
            attacked.performPlayerAction(new ReceiveNewCardOnDeck(curse));
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