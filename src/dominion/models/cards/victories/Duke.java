package dominion.models.cards.victories;

import dominion.models.cards.Card;
import dominion.models.cards.CardStyles;
import dominion.models.cards.CardTypes;
import dominion.models.expansions.Intrigue;
import dominion.models.player.Player;

import java.util.List;

public class Duke extends Card implements Intrigue, Victory{
    // Constructor
    public Duke() {
        name = "公爵";
        description = "遊戲結束時，每一張公國都會讓你遊戲結束多1分。";
        style = CardStyles.green;
        type = CardTypes.victory;
        numCost = 5;
    }

    // Functions
    @Override
    public int getNumVictories(Player player) {
        List<Card> cards = player.getAllCards();
        int numDuchy = 0;
        for(Card card : cards) {
            if(card instanceof Duchy) {
                numDuchy++;
            }
        }
        return numDuchy;
    }
}
