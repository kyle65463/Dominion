package dominion.models.game;

import dominion.controllers.components.DiscardPileController;

import java.util.ArrayList;
import java.util.List;

public class DiscardPile implements HasUi{
    // Constructor
    public DiscardPile(){
    }

    // Variables
    private List<Card> cards = new ArrayList<>();
    private boolean isEnableUi = false;
    private DiscardPileController uiController;

    // Functions
    public void enableUi(GameScene gameScene) {
        this.uiController = new DiscardPileController(gameScene);
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

    public void removeCards() {
        cards.clear();
    }

    public void addCard(Card card) {
        cards.add(card);
        if(isEnableUi){
            card.enableUi();
            uiController.addCard(card);
        }
    }
}
