package dominion.models.cards.actions;

import dominion.models.cards.Card;
import dominion.models.cards.CardStyles;
import dominion.models.cards.CardTypes;
import dominion.models.cards.treasures.Silver;
import dominion.models.expansions.Dominion;
import dominion.models.player.Player;

import java.util.List;

public class Merchant extends Card implements Dominion, Action {
    // Constructor
    public Merchant() {
            name = "商人";
            description = "+1 卡片\n+1 行動\n\n此回合第一次打出銀幣的時候+1塊。";
            style = CardStyles.white;
            type = CardTypes.action;
            numCost = 3;
    }

    @Override
    public void perform(Player performer, boolean decreaseNumActions) {
        performer.drawCards(1);
        performer.increaseNumActions(1);

        performer.setAfterPlayCardHandler(()->{
            List<Card> cards = performer.getFieldCards().getCards();
            boolean firstSilver = true;
            if (cards.get(cards.size() - 1) instanceof Silver) {
                for (int i = 0; i < cards.size() - 1; i++) {
                    Card card = cards.get(i);
                    if (card instanceof Silver) {
                        firstSilver = false;
                    }
                }
                if (firstSilver) {
                    for (Card card : cards) {
                        if (card instanceof Merchant) {
                            performer.increaseNumCoins(1);
                        }
                    }
                }
            }
        });
        if (decreaseNumActions) {
            performer.decreaseNumActions();
        }
        doNextMove();
    }
}
