package dominion.models.game;

import dominion.controllers.components.DeckController;

import java.util.ArrayList;
import java.util.List;

public class Deck implements HasUi{
    // Constructor
    public Deck() {
    }

    // Variables
    private boolean isEnableUi = false;
    private DeckController uiController;
    private List<Card> cards = new ArrayList<>();

    // Functions
    public void enableUi(GameScene gameScene) {
        this.uiController = new DeckController(gameScene);
        isEnableUi = true;
        for(Card card : cards) {
            card.enableUi();
        }
    }

    public int getNumCards() {
        return cards.size();
    }

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
        if (isEnableUi) {
            for (Card card : cards){
                card.enableUi();
                card.flipToFront();
                card.setNumRemain(0);
                uiController.addCard(card);
            }
        }
        cards.clear();
    }

    public void addCards(List<Card> cards) {
        for(Card card : cards) {
            addCard(card);
        }
    }

    public void addCard(Card card) {
        cards.add(card);
        if (isEnableUi) {
            card.enableUi();
            card.flipToBack();
            card.setNumRemain(cards.size());
            uiController.addCard(card);
        }
    }
}
