package dominion.models.game;

import dominion.controllers.components.FieldCardsController;
import dominion.models.game.cards.Card;

import java.io.Serializable;
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
        return new ArrayList<>(cards);
    }

    public Card getCardByCardId(int cardId) {
        for(Card card : cards) {
            if(card.getId() == cardId){
                return card;
            }
        }
        return null;
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
