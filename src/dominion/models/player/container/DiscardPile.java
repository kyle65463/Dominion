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
    // Variables
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

    @Override
    public void removeCard(Card card){
        cards.remove(card);
    }

    @Override
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
}
