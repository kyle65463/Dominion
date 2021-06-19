package dominion.models.game.cards;

import dominion.controllers.components.CardController;
import dominion.models.game.HasUi;
import dominion.models.game.cards.treasures.Treasure;

public abstract class Card implements HasUi {
    // Constructor
    public Card() {
        disableUi();
    }

    // Variables
    /* Properties */
    protected String name = "";
    protected String type = "";
    protected String description = "";
    protected String style = "";
    protected int numCost = 0;
    protected int numRemain = 0;

    /* UI */
    protected boolean isEnableUi = false;
    protected CardController uiController;

    // Function
    public String getStyle() {
        return style;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public int getNumCost() {
        return numCost;
    }

    public void disableUi() {
        uiController = null;
        isEnableUi = false;
    }

    public void enableUi() {
        if(uiController == null) {
            uiController = new CardController(this);
            uiController.setNumRemainLabel(numRemain);
            if(this instanceof Treasure) {
                uiController.setNumValueLabel(((Treasure) this).getNumValue());
            }
        }
        isEnableUi = true;
    }

    public void flipToBack() {
        if(isEnableUi) {
            uiController.flipToBack();
        }
    }

    public void flipToFront() {
        if(isEnableUi) {
            uiController.flipToBack();
        }
    }

    public void setNumRemain(int numRemain) {
        if(isEnableUi) {
            uiController.setNumRemainLabel(numRemain);
        }
    }

    public CardController getController() {
        return uiController;
    }
}
