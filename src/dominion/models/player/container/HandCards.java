package dominion.models.player.container;

import dominion.controllers.components.HandCardsController;
import dominion.models.HasUi;
import dominion.models.cards.Card;
import dominion.models.cards.actions.Action;
import dominion.models.cards.actions.Reaction;
import dominion.models.cards.curses.Curses;
import dominion.models.cards.victories.Victory;
import dominion.models.handlers.CardSelectedHandler;
import dominion.models.player.Player;

import java.util.ArrayList;
import java.util.List;

public class HandCards extends CardContainer implements HasUi {
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
    public void enableUi() {
        this.uiController = new HandCardsController();
        isEnableUi = true;
        for (Card card : cards) {
            card.enableUi();
        }
    }

    public Card getCardByCardId(int cardId) {
        for (Card card : cards) {
            if (card.getId() == cardId) {
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
            } else if (card instanceof Curses) {
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
            card.setOnPressed((e) -> {

            });
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

    public boolean hasReactionCards() {
        for (Card card : cards) {
            if (card instanceof Reaction) {
                return true;
            }
        }
        return false;
    }

    public void removeCardSelectedHandler() {
        this.cardSelectedHandler = (card) -> {
        };
    }

    private CardSelectedHandler cardSelectedHandler = (card) -> {
    };

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
        if (isEnableUi) {
            card.enableUi();
            card.setOnPressed((e) -> {
                cardSelectedHandler.onSelected(card);
            });
            System.out.println("enable ui: " + card.getEnableUi());
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
        card.setOnPressed((e) -> {
        });
        if (isEnableUi) {
            uiController.arrangeCardsPos(cards);
        }
    }

    public void rearrange() {
        if (isEnableUi) {
            for(Card card : cards){
                card.removeHighlight();

            }
            uiController.arrangeCardsPos(cards);
        }
    }

    public void setToBottom(Card card) {
        if(isEnableUi) {
            if(cards.contains(card)) {
                int numSelected = 0;
                for(int i = 0; i < cards.size(); i++) {
                    if(cards.get(i).getName().equals(card.getName())) {
                        cards.remove(card);
                        cards.add(i, card);
                        numSelected = card.getNumSelected() + 1;
                        card.removeHighlight();
                        break;
                    }
                }
                for(int i = cards.size() - 1; i >= 0; i--) {
                    if(cards.get(i).getName().equals(card.getName())) {
                        cards.get(i).setHighlight();
                        cards.get(i).setNumSelected(numSelected);
                        break;
                    }
                }
                uiController.arrangeCardsPos(cards);
            }
        }
    }
}
