package dominion.models.game.cards.victories;

import dominion.models.game.Player;
import dominion.models.game.cards.Card;
import dominion.utils.CardStyles;
import dominion.utils.CardTypes;

public class Province extends Card implements Victory{
    // Constructor
    public Province() {
        name = "行省";
        description = "+6分數";
        style = CardStyles.green;
        type = CardTypes.victory;
        numCost = 8;
        numVictories = 6;
    }

    // Variables
    private int numVictories;

    // Functions
    @Override
    public int getNumVictories(Player player) {
        return numVictories;
    }
}
