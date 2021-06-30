package dominion.models.cards.actions;

import dominion.core.GameManager;
import dominion.models.cards.treasures.Copper;
import dominion.models.events.game.DoneAttackingEvent;
import dominion.models.player.Player;
import dominion.models.cards.Card;
import dominion.models.cards.AttackPlayers;
import dominion.models.cards.CardStyles;
import dominion.models.cards.CardTypes;

import java.util.List;

public class CutPurse extends Card implements Action, Attack, HasHandCardsSelection {
    public CutPurse() {
        name = "扒手";
        description = "+2 塊錢\n\n其他玩家棄掉一張銅幣，或是展示手牌裡沒有銅幣。";
        style = CardStyles.white;
        type = CardTypes.action;
        numCost = 4;
    }

    boolean decreaseNumActions = true;

    @Override
    public void perform(Player performer, boolean decreaseNumActions) {
        performer.increaseNumCoins(2);
        this.decreaseNumActions = decreaseNumActions;
        Thread thread = new Thread(new AttackPlayers(performer, this));
        thread.start();
    }

    @Override
    public void performAttack(Player performer, Player attacked) {
        if (attacked.getHandCards().size() > 3) {
            if (attacked.getHandCards().stream().anyMatch(card -> card instanceof Copper)) {
                GameManager.setCurrentPhase(GameManager.Phase.SelectingHandCards);
                attacked.setSelectingHandCardsFilter(card -> card instanceof Copper);
                attacked.setExactSelectingCards(1);
                attacked.startSelectingHandCards("棄掉一張銅幣", id);
            } else {
                attacked.displayHandCards();
                GameManager.sendEvent(new DoneAttackingEvent(performer.getId()));
            }
        }
        else{
            GameManager.sendEvent(new DoneAttackingEvent(performer.getId()));
        }
    }

    @Override
    public void performSelection(Player performer, List<Card> cards) {
        performer.discardHandCards(cards);
        GameManager.sendEvent(new DoneAttackingEvent(performer.getId()));
    }

    @Override
    public void performAfterAttack(Player performer) {
        if (decreaseNumActions) {
            performer.decreaseNumActions();
        }
        doNextMove();
    }
}