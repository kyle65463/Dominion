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
    // Variables
    private boolean isEnableUi = false;
    private HandCardsController uiController;
    private final CardSelectedHandler originalCardSelectedHandler = (card) -> {};
    private CardSelectedHandler cardSelectedHandler = originalCardSelectedHandler;

    // Functions
    public void enableUi() {
        this.uiController = new HandCardsController();
        isEnableUi = true;
        for (Card card : cards) {
            card.enableUi();
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
        setCardSelectedHandler(originalCardSelectedHandler);
    }

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

    @Override
    public void addCard(Card card) {
        cards.add(card);
        if (isEnableUi) {
            card.enableUi();
            card.setOnPressed((e) -> {
                cardSelectedHandler.onSelected(card);
            });
            uiController.arrangeCardsPos(cards);
        }
    }

    @Override
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
