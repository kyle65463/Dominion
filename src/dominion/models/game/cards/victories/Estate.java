package dominion.models.game.cards.victories;

import dominion.models.game.cards.Card;
import dominion.utils.CardStyles;
import dominion.utils.CardTypes;

public class Estate extends Card implements Victory{
    // Constructor
    public Estate() {
        name = "莊園";
        description = "";
        style = CardStyles.green;
        type = CardTypes.victory;
        numCost = 2;
        numVictories = 1;
    }

    // Variables
    private int numVictories;

    // Functions
    @Override
    public int getNumVictories() {
        return numVictories;
    }
}
