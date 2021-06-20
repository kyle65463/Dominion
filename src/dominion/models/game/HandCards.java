package dominion.models.game;

import dominion.controllers.components.HandCardsController;
import dominion.game.GameManager;
import dominion.models.events.game.GameEvent;
import dominion.models.events.game.PlayCardEvent;
import dominion.models.game.cards.Card;
import dominion.models.game.cards.actions.Action;
import dominion.models.game.cards.curses.Curses;
import dominion.models.game.cards.treasures.Treasure;
import dominion.models.game.cards.victories.Victory;
import javafx.event.EventHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HandCards implements HasUi {
    // Constructor
    public HandCards(Player player) {
        this.player = player;
    }

    // Variables
    private Player player;
    private boolean isEnableUi = false;
    private HandCardsController uiController;
    private List<Card> cards = new ArrayList<>();

    // Functions
    public void enableUi(GameScene gameScene) {
        this.uiController = new HandCardsController(gameScene);
        isEnableUi = true;
        for (Card card : cards) {
            card.enableUi();
        }
    }

    public Card getCardByCardId(int cardId) {
        for(Card card : cards) {
            if(card.getId() == cardId){
                return card;
            }
        }
        return null;
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

    public int getNumCards() {
        return cards.size();
    }

    public List<Card> getCards() {
        return new ArrayList<>(cards);
    }

    public void removeAllCards() {
        cards.clear();
        if (isEnableUi) {
            uiController.arrangeCardsPos(cards);
        }
    }

    public void disableAllCards() {
        for (Card card : cards) {
            card.disableOnPressed();
        }
    }

    public boolean hasActionCards() {
        for (Card card : cards) {
            if (card instanceof Action) {
                return true;
            }
        }
        return false;
    }

    public void removeCardSelectedHandler() {
        this.cardSelectedHandler = (card) -> {};
    }

    private CardSelectedHandler cardSelectedHandler = (card) -> {};

    public void setCardSelectedHandler(CardSelectedHandler cardSelectedHandler) {
        this.cardSelectedHandler = cardSelectedHandler;
        for (Card card : cards) {
            card.setOnPressed((e) -> {
                cardSelectedHandler.onSelected(card);
            });
        }
    }

    public CardSelectedHandler getCardSelectedHandler() {
        return cardSelectedHandler;
    }

    public void addCards(List<Card> cards) {
        for (Card card : cards) {
            addCard(card);
        }
    }

    public void addCard(Card card) {
        cards.add(card);
        card.setOnPressed((e) -> {
            cardSelectedHandler.onSelected(card);
        });
        if (isEnableUi) {
            uiController.arrangeCardsPos(cards);
        }
    }

    public void removeCards(List<Card> cards) {
        for (Card card : cards) {
            removeCard(card);
        }
    }

    public void removeCard(Card card) {
        cards.remove(card);
        card.setNumRemain(0);
        card.disableOnPressed();
        if (isEnableUi) {
            uiController.arrangeCardsPos(cards);
        }
    }
}
