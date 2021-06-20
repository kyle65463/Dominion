package dominion.models.game.cards.victories;

import dominion.models.game.Player;
import dominion.models.game.cards.Card;
import dominion.utils.CardStyles;
import dominion.utils.CardTypes;

public class Gardens extends Card implements Victory{
    // Constructor
    public Gardens() {
        name = "花園";
        description = "";
        style = CardStyles.green;
        type = CardTypes.victory;
        numCost = 4;
        numVictories = 1;
    }

    // Variables
    private int numVictories;

    // Functions
    @Override
    public int getNumVictories(Player player) {
        return  (player.getAllCards().size() / 10) * numVictories;
    }
}
