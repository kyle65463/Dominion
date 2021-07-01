package dominion.models.cards.victories;

import dominion.models.expansions.Basic;
import dominion.models.player.Player;
import dominion.models.cards.Card;
import dominion.models.cards.CardStyles;
import dominion.models.cards.CardTypes;

public class Duchy extends Card implements Basic, Victory{
    // Constructor
    public Duchy() {
        name = "公國";
        description = "+3 分數";
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
