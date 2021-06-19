package dominion.models.game.cards.curses;

import dominion.models.game.cards.Card;
import dominion.utils.CardStyles;
import dominion.utils.CardTypes;

public class Curse extends Card implements Curses{
    // Constructor
    public Curse() {
        name = "詛咒";
        description = "";
        style = CardStyles.purple;
        type = CardTypes.curse;
        numCost = 0;
        numCurses = 1;
    }

    // Variables
    private int numCurses;

    // Functions
    @Override
    public int getNumCurses() {
        return numCurses;
    }
}
