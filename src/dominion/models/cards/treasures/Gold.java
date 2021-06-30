package dominion.models.cards.treasures;

import dominion.models.cards.Card;
import dominion.utils.CardStyles;
import dominion.utils.CardTypes;

public class Gold extends Card implements Treasure{
    // Constructor
    public Gold() {
        name = "黃金";
        description = "3塊錢";
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
