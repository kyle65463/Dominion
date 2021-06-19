package dominion.models.game;

import dominion.controllers.components.FieldCardsController;

import java.util.ArrayList;
import java.util.List;

public class FieldCards implements HasUi{
    // Constructor
    public FieldCards() {
    }

    // Variables
    private boolean isEnableUi = false;
    private FieldCardsController uiController;
    private List<Card> cards = new ArrayList<>();

    // Functions
    public void enableUi(GameScene gameScene) {
        this.uiController = new FieldCardsController(gameScene);
        isEnableUi = true;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void removeCards() {
        cards.clear();
    }

    public void addCard(Card card) {
        cards.add(card);
        if(isEnableUi) {
            card.enableUi();
            uiController.addCard(card, cards.size());
        }
    }
}
