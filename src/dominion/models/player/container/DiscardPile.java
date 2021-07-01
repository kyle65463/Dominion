package dominion.models.player.container;

import dominion.controllers.components.DiscardPileController;
import dominion.models.HasUi;
import dominion.models.cards.Card;
import dominion.models.cards.curses.Curses;
import dominion.models.cards.victories.Victory;
import dominion.models.player.Player;

import java.util.ArrayList;
import java.util.List;

public class DiscardPile extends CardContainer  implements HasUi {
    // Constructor
    public DiscardPile(){
    }

    // Variables
    private List<Card> cards = new ArrayList<>();
    private boolean isEnableUi = false;
    private DiscardPileController uiController;

    // Functions
    public void enableUi() {
        this.uiController = new DiscardPileController();
        isEnableUi = true;
        for(Card card : cards) {
            card.enableUi();
        }
    }

    public int getNumCards() {
        return cards.size();
    }

    public int getNumScores(Player player) {
        int numScores = 0;
        for (Card card : cards) {
            if (card instanceof Victory) {
                numScores += ((Victory) card).getNumVictories(player);
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
