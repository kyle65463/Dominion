package dominion.models.cards.actions;

import dominion.core.GameManager;
import dominion.models.cards.Card;
import dominion.models.cards.CardStyles;
import dominion.models.cards.CardTypes;
import dominion.models.expansions.Dominion;
import dominion.models.player.Player;
import dominion.models.player.PlayerAction.AddCardsToDiscardPile;
import dominion.models.player.PlayerAction.PlayCard;
import dominion.models.player.PlayerAction.ReceiveNewHandCard;

import java.util.List;

public class Vassal extends Card implements Dominion, Action{
    public Vassal() {
        name = "家臣";
        description = "+2塊錢\n棄掉牌庫頂部的一張卡，若是行動卡，你可以打出它";
        style = CardStyles.white;
        type = CardTypes.action;
        numCost = 3;
    }

    @Override
    public void perform(Player performer, boolean decreaseNumActions) {
        performer.increaseNumCoins(2);
        List<Card> cards = performer.popDeckTop(1);
        if (cards.size() > 0) {
            Card card = cards.get(0);
            if (card instanceof Action) {
                GameManager.setCurrentPhase(GameManager.Phase.SelectingOptions);
                performer.snapshotStatus();
                performer.setCardSelectedHandler((card1)->{});
                performer.setActionBarStatus("你可以打出 " + card.getName(), "打出", "不打出");
                performer.enableLeftButton(true);
                performer.setActionBarRightButtonHandler((e)->{
                    performer.performPlayerAction(new ReceiveNewHandCard(card));
                    performer.recoverStatus();
                    performer.enableLeftButton(false);
                    GameManager.returnLastPhase();
                    if(decreaseNumActions) {
                        performer.decreaseNumActions();
                    }
                    performer.performPlayerAction(new PlayCard(card.getId(), false, null));
                });
                performer.setActionBarLeftButtonHandler((e)->{
                    performer.performPlayerAction(new AddCardsToDiscardPile(cards));
                    performer.recoverStatus();
                    performer.enableLeftButton(false);
                    GameManager.returnLastPhase();
                    if(decreaseNumActions) {
                        performer.decreaseNumActions();
                    }
                    doNextMove();
                });
            } else {
                performer.performPlayerAction(new AddCardsToDiscardPile(cards));
                if(decreaseNumActions) {
                    performer.decreaseNumActions();
                }
                doNextMove();
            }
        } else {
            if(decreaseNumActions) {
                performer.decreaseNumActions();
            }
            doNextMove();
        }
    }
}
