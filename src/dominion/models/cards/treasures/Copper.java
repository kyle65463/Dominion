package dominion.models.cards.treasures;

import dominion.models.cards.Card;
import dominion.models.cards.CardStyles;
import dominion.models.cards.CardTypes;

public class Copper extends Card implements Treasure{
    // Constructor
    public Copper() {
        name = "銅幣";
        description = "1 塊錢";
        style = CardStyles.gold;
        type = CardTypes.treasure;
        numCost = 0;
        numValue = 1;
    }

    // Variables
    private int numValue;

    // Functions
    @Override
    public int getNumValue() {
        return numValue;
    }
}
