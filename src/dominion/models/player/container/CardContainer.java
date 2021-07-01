package dominion.models.player.container;

import dominion.models.cards.Card;
import dominion.models.cards.curses.Curses;
import dominion.models.cards.victories.Victory;
import dominion.models.player.Player;

import java.util.ArrayList;
import java.util.List;

public abstract class CardContainer {
    // Variables
    protected List<Card> cards = new ArrayList<>();

    // Functions
    public int getNumScores(Player player) {
        int numScores = 0;
        for (Card card : cards) {
            if (card instanceof Victory) {
                numScores += ((Victory) card).getNumVictories(player);
            }
            if(card instanceof Curses) {
                numScores -= ((Curses) card).getNumCurses();
            }
        }
        return numScores;
    }

    public abstract void addCard(Card card);

    public void addCards(List<Card> cards) {
        for(Card card : cards) {
            addCard(card);
        }
    }

    public abstract void removeCard(Card card);

    public void removeCards(List<Card> cs) {
        for(Card card : cs) {
            removeCard(card);
        }
    }

    public void removeAllCards(){
        removeCards(new ArrayList<>(cards));
    }

    public int getNumCards() {
        return cards.size();
    }

    public List<Card> getCards() {
        return new ArrayList<>(cards);
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }

    public Card getCardByCardId(int cardId) {
        for(Card card : cards) {
            if(card.getId() == cardId){
                return card;
            }
        }
        return null;
    }
}
