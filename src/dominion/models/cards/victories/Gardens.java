package dominion.models.cards.victories;

import dominion.models.player.Player;
import dominion.models.cards.Card;
import dominion.models.cards.CardStyles;
import dominion.models.cards.CardTypes;

public class Gardens extends Card implements Victory{
    // Constructor
    public Gardens() {
        name = "花園";
        description = "遊戲結束時，你手上每有10張卡片，此卡會給予你一分。\n(無條件捨去)";
        style = CardStyles.green;
        type = CardTypes.victory;
        numCost = 4;
        numVictories = 1;
    }

    // Variables
    private int numVictories;

    // Functions
    @Override
    public int getNumVictories(Player player) {
        return  (player.getAllCards().size() / 10) * numVictories;
    }
}
