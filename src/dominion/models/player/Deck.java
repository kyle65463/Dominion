package dominion.models.player;

import dominion.controllers.components.DeckController;
import dominion.core.GameManager;
import dominion.models.HasUi;
import dominion.models.cards.Card;
import dominion.models.cards.curses.Curses;
import dominion.models.cards.victories.Victory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Deck implements HasUi {
    // Constructor
    public Deck() {
    }

    // Variables
    private boolean isEnableUi = false;
    private DeckController uiController;
    private List<Card> cards = new ArrayList<>();

    // Functions
    public void enableUi() {
        this.uiController = new DeckController();
        isEnableUi = true;
        for (Card card : cards) {
            card.enableUi();
        }
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

    public boolean isEmpty() {
        return cards.isEmpty();
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
            if (cards.isEmpty()) {
                break;
            }
            Card card = cards.get(cards.size() - 1);
            cards.remove(card);
            resultCards.add(card);

            if (isEnableUi) {
                card.enableUi();
                card.setNumRemain(0);
                card.flipToFront();
            }
        }
        return resultCards;
    }

    public void addCards(List<Card> cards, boolean shuffle) {
        if(shuffle){
            Collections.shuffle(cards, new Random(GameManager.getRandomInt()));
        }
        for (Card card : cards) {
            addCard(card);
        }
    }

    public void addCards(List<Card> cards) {
        addCards(cards, false);
    }

    private void addCard(Card card) {
        cards.add(card);
        if (isEnableUi) {
            card.enableUi();
            card.setNumRemain(cards.size());
            card.flipToBack();
            uiController.addCard(card);
        }
    }
}
