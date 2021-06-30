package dominion.models.cards.curses;

import dominion.models.cards.Card;
import dominion.utils.CardStyles;
import dominion.utils.CardTypes;

public class Curse extends Card implements Curses{
    // Constructor
    public Curse() {
        name = "詛咒";
        description = "-1分數";
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
