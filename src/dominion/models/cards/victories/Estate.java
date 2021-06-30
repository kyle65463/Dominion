package dominion.models.cards.victories;

import dominion.models.player.Player;
import dominion.models.cards.Card;
import dominion.models.cards.CardStyles;
import dominion.models.cards.CardTypes;

public class Estate extends Card implements Victory{
    // Constructor
    public Estate() {
        name = "莊園";
        description = "+1 分數";
        style = CardStyles.green;
        type = CardTypes.victory;
        numCost = 2;
        numVictories = 1;
    }

    // Variables
    private int numVictories;

    // Functions
    @Override
    public int getNumVictories(Player player) {
        return numVictories;
    }
}
