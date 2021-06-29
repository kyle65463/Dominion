package dominion.models.game.cards.treasures;

import dominion.models.game.cards.Card;
import dominion.utils.CardStyles;
import dominion.utils.CardTypes;

public class Silver extends Card implements Treasure{
    // Constructor
    public Silver() {
        name = "銀幣";
        description = "+2塊錢";
        style = CardStyles.gold;
        type = CardTypes.treasure;
        numCost = 3;
        numValue = 2;
    }

    // Variables
    private int numValue;

    // Functions
    @Override
    public int getNumValue() {
        return numValue;
    }
}
