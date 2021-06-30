package dominion.models.cards.treasures;

import dominion.models.cards.Card;
import dominion.models.cards.CardStyles;
import dominion.models.cards.CardTypes;

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
