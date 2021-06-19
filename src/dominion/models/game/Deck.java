package dominion.models.game;

import dominion.controllers.components.DeckController;

import java.util.ArrayList;
import java.util.List;

public class Deck {
    // Constructor
    public Deck(GameScene gameScene) {
        this.uiController = new DeckController(gameScene);
    }

    // Variables
    private DeckController uiController;
    private List<Card> cards = new ArrayList<>();

    // Functions
    public List<Card> getCards() {
        return cards;
    }

    public List<Card> popCards(int numCards) {
        List<Card> resultCards = new ArrayList<>();
        for (int i = 0; i < numCards; i++) {
            if(cards.isEmpty()){
                break;
            }
            Card card = cards.get(cards.size() - 1);
            cards.remove(card);
            resultCards.add(card);
        }
        return resultCards;
    }

    public void removeCards() {
        cards.clear();
    }

    public void addCard(Card card) {
        cards.add(card);
        uiController.addCard(card);
    }
}
