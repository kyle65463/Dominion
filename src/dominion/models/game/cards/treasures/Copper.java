package dominion.models.game.cards.treasures;

import dominion.models.game.cards.Card;
import dominion.utils.CardStyles;
import dominion.utils.CardTypes;

public class Copper extends Card implements Treasure{
    // Constructor
    public Copper() {
        name = "銅幣";
        description = "";
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
