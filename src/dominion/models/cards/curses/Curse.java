package dominion.models.cards.curses;

import dominion.models.cards.Card;
import dominion.models.cards.CardStyles;
import dominion.models.cards.CardTypes;
import dominion.models.expansions.Basic;

public class Curse extends Card implements Basic, Curses{
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
