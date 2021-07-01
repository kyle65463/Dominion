package dominion.models.cards.treasures;

import dominion.models.cards.Card;
import dominion.models.cards.CardStyles;
import dominion.models.cards.CardTypes;
import dominion.models.expansions.Basic;

public class Gold extends Card implements Basic, Treasure{
    // Constructor
    public Gold() {
        name = "黃金";
        description = "3 塊錢";
        style = CardStyles.gold;
        type = CardTypes.treasure;
        numCost = 6;
        numValue = 3;
    }

    // Variables
    private int numValue;

    // Functions
    @Override
    public int getNumValue() {
        return numValue;
    }
}
