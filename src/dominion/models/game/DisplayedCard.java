package dominion.models.game;

import dominion.controllers.components.DisplayedCardController;
import dominion.models.game.cards.Card;

public class DisplayedCard implements HasUi {
    // Constructor
    public DisplayedCard(Card card, int numRemain) {
        this.card = card;
        this.numRemain = numRemain;
        card.disableUi();

        // Impossible with no ui
        enableUi();
    }

    // Variables
    private Card card;
    private int numRemain;
    private boolean isEnableUi;
    private DisplayedCardController uiController;

    // Functions
    public void enableUi() {
        this.uiController =  new DisplayedCardController(card);
        setNumRemain(numRemain);
        isEnableUi = true;
    }

    public void setNumRemain(int numRemain) {
        uiController.setNumRemain(numRemain);
    }

    public DisplayedCardController getController() {
        return uiController;
    }
}
