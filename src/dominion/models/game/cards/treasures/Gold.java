package dominion.models.game.cards.treasures;

import dominion.models.game.cards.Card;
import dominion.utils.CardStyles;
import dominion.utils.CardTypes;

public class Gold extends Card implements Treasure{
    // Constructor
    public Gold() {
        name = "金幣";
        description = "";
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
