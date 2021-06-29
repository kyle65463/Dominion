package dominion.models.game.cards.victories;

import dominion.models.game.Player;
import dominion.models.game.cards.Card;
import dominion.utils.CardStyles;
import dominion.utils.CardTypes;

public class Duchy extends Card implements Victory{
    // Constructor
    public Duchy() {
        name = "公國";
        description = "+3分數";
        style = CardStyles.green;
        type = CardTypes.victory;
        numCost = 5;
        numVictories = 3;
    }

    // Variables
    private int numVictories;

    // Functions
    @Override
    public int getNumVictories(Player player) {
        return numVictories;
    }
}
