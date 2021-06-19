package dominion.models.game;

import dominion.controllers.components.DiscardPileController;
import dominion.models.game.cards.Card;
import dominion.models.game.cards.curses.Curses;
import dominion.models.game.cards.victories.Victory;

import java.io.Serializable;
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

    public int getNumScores() {
        int numScores = 0;
        for (Card card : cards) {
            if (card instanceof Victory) {
                numScores += ((Victory) card).getNumVictories();
            }
            else if(card instanceof Curses) {
                numScores -= ((Curses) card).getNumCurses();
            }
        }
        return numScores;
    }

    public List<Card> getCards() {
        return new ArrayList<>(cards);
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
        else{
            card.disableUi();
        }
    }

    public void addCards(List<Card> cards) {
        for(Card card : cards) {
            addCard(card);
        }
    }
}
