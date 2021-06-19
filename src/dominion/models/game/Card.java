package dominion.models.game;

import dominion.controllers.components.CardController;

public class Card implements HasUi {
    // Constructor
    public Card() {
    }

    // Variables
    private boolean isEnableUi = false;
    private static CardController uiController;

    // Function
    public void disableUi() {
        uiController = null;
        isEnableUi = false;
    }

    public void enableUi() {
        if(uiController == null) {
            uiController = new CardController();
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
            uiController.setRemainLabel(numRemain);
        }
    }

    public CardController getController() {
        return uiController;
    }
}
