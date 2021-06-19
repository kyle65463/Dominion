package dominion.models.game;

import dominion.controllers.components.HandCardsController;

import java.util.ArrayList;
import java.util.List;

public class HandCards implements HasUi{
    // Constructor
    public HandCards() {
    }

    // Variables
    private boolean isEnableUi = false;
    private HandCardsController uiController;
    private List<Card> cards = new ArrayList<>();

    // Functions
    public void enableUi(GameScene gameScene) {
        this.uiController = new HandCardsController(gameScene);
        isEnableUi = true;
        for(Card card : cards) {
            card.enableUi();
        }
    }

    public int getNumCards() {
        return cards.size();
    }

    public List<Card> getCards() {
        return getCards();
    }

    public void removeCards() {
        cards.clear();
    }

    public void addCard(Card card) {
        cards.add(card);
        if(isEnableUi) {
            uiController.arrangeCardsPos(cards);
        }
    }

    public void removeCard(Card card) {
        cards.remove(card);
        if(isEnableUi) {
            card.enableUi();
            uiController.arrangeCardsPos(cards);
        }
    }
}
