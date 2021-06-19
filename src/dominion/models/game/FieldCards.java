package dominion.models.game;

import dominion.controllers.components.FieldCardsController;

import java.util.ArrayList;
import java.util.List;

public class FieldCards {
    // Constructor
    public FieldCards(GameScene gameScene) {
        this.uiController = new FieldCardsController(gameScene);
    }

    // Variables
    private FieldCardsController uiController;
    private List<Card> cards = new ArrayList<>();

    // Functions
    public List<Card> getCards() {
        return cards;
    }

    public void removeCards() {
        cards.clear();
    }

    public void addCard(Card card) {
        cards.add(card);
        uiController.addCard(card, cards.size());
    }
}
