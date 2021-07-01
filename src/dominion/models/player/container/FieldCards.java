package dominion.models.player.container;

import dominion.controllers.components.FieldCardsController;
import dominion.models.HasUi;
import dominion.models.cards.Card;

import java.util.ArrayList;
import java.util.List;

public class FieldCards extends CardContainer implements HasUi {
    // Variables
    private boolean isEnableUi = false;
    private FieldCardsController uiController;

    // Functions
    public void enableUi() {
        this.uiController = new FieldCardsController();
        isEnableUi = true;
    }

    public void removeCard(Card card) {
        cards.remove(card);
    }

    public void addCard(Card card) {
        cards.add(card);
        if(isEnableUi) {
            card.enableUi();
            uiController.addCard(card, cards.size());
        }
    }
}
