package dominion.models.player.container;

import dominion.controllers.cardcontainer.DeckController;
import dominion.core.GameManager;
import dominion.models.HasUi;
import dominion.models.cards.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Deck extends CardContainer implements HasUi {
    // Variables
    private boolean isEnableUi = false;
    private DeckController uiController;

    // Functions
    public void enableUi() {
        this.uiController = new DeckController();
        isEnableUi = true;
        for (Card card : cards) {
            card.enableUi();
        }
    }

    public List<Card> popTopCards(int numCards) {
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

    @Override
    public void removeCard(Card card) {
        cards.remove(card);
        if (isEnableUi) {
            card.enableUi();
            card.setNumRemain(0);
            card.flipToFront();
        }
        for(int i = 0; i < cards.size(); i++){
            if(isEnableUi) {
                cards.get(i).setNumRemain(i);
            }
        }
    }

    @Override
    public void addCard(Card card) {
        cards.add(card);
        if (isEnableUi) {
            card.enableUi();
            card.setNumRemain(cards.size());
            card.flipToBack();
            uiController.addCard(card);
        }
    }

    public void addCards(List<Card> cards, boolean shuffle) {
        if(shuffle){
            Collections.shuffle(cards, new Random(GameManager.getRandomInt()));
        }
        super.addCards(cards);
    }
}
