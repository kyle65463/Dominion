package dominion.models.game;

import dominion.controllers.components.HandCardsController;

import java.util.ArrayList;
import java.util.List;

public class HandCards {
    // Constructor
    public HandCards(GameScene gameScene) {
        this.uiController = new HandCardsController(gameScene);
    }

    // Variables
    private HandCardsController uiController;
    private List<Card> cards = new ArrayList<>();

    // Functions
    public List<Card> getCards() {
        return getCards();
    }

    public void removeCards() {
        cards.clear();
    }

    public void addCard(Card card) {
        cards.add(card);
        uiController.arrangeCardsPos(cards);
    }

    public void removeCard(Card card) {
        cards.remove(card);
        uiController.arrangeCardsPos(cards);
    }
}
