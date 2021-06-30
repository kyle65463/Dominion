package dominion.models.cards.actions;

import dominion.core.GameManager;
import dominion.models.cards.Card;
import dominion.models.cards.CardStyles;
import dominion.models.cards.CardTypes;
import dominion.models.player.Player;

import java.util.List;

public class Vassal extends Card implements Action{
    public Vassal() {
        name = "家臣";
        description = "+2塊錢\n棄掉牌庫頂部的一張卡，若是行動卡，你可以打出它";
        style = CardStyles.white;
        type = CardTypes.action;
        numCost = 3;
    }

    @Override
    public void perform(Player performer, boolean decreaseNumActions) {
        if(decreaseNumActions) {
            performer.decreaseNumActions();
        }
        performer.increaseNumCoins(2);
        List<Card> cards = performer.popDeckTop(1);
        if (cards.size() > 0) {
            GameManager.setCurrentPhase(GameManager.Phase.SelectingOptions);
            Card card = cards.get(0);
            System.out.println("top card: " + card);
            if (card instanceof Action) {
                performer.snapshotStatus();
                performer.setActionBarStatus("你可以打出 " + card.getName(), "打出", "不打出");
                performer.enableLeftButton(true);
                performer.setActionBarRightButtonHandler((e)->{
                    card.enableUi();
                    performer.receiveNewHandCard(card);
                    performer.playCard(card.getId(), false, null);
                    performer.recoverStatus();
                    performer.enableLeftButton(false);
                    doNextMove();
                });
                performer.setActionBarLeftButtonHandler((e)->{
                    performer.recoverStatus();
                    performer.enableLeftButton(false);
                    doNextMove();
                });
            } else {
                performer.receiveNewHandCard(card);
                performer.removeHandCard(card);
                doNextMove();
            }
        } else {
            doNextMove();
        }
    }
}
