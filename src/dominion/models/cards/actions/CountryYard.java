package dominion.models.cards.actions;

import dominion.core.GameManager;
import dominion.models.cards.Card;
import dominion.models.cards.CardStyles;
import dominion.models.cards.CardTypes;
import dominion.models.cards.treasures.Copper;
import dominion.models.player.Player;

import java.util.List;

public class CountryYard extends Card implements Action, HasHandCardsSelection {
    // Constructor
    public CountryYard() {
        name = "庭院";
        description = "+3 卡片\n\n將你一張手牌放回牌庫頂。";
        style = CardStyles.white;
        type = CardTypes.action;
        numCost = 2;
        expansion = Expansion.Intrigue;
    }

    // Variables
    private boolean decreaseNumActions = true;

    // Functions
    @Override
    public void perform(Player performer, boolean decreaseNumActions) {
        this.decreaseNumActions = decreaseNumActions;
        performer.drawCards(3);

        if(performer.getHandCards().size() > 0) {
            GameManager.setCurrentPhase(GameManager.Phase.SelectingHandCards);
            performer.setExactSelectingCards(1);
            performer.startSelectingHandCards("選擇放回牌庫頂的牌", id);
        }
        else{
            if(decreaseNumActions) {
                performer.decreaseNumActions();
            }
            doNextMove();
        }
    }

    @Override
    public void performSelection(Player performer, List<Card> cards) {
        if(cards.size() > 0) {
            Card card = cards.get(0);
            performer.removeHandCard(card);
            performer.receiveNewCardOnDeck(card);
        }

        if(decreaseNumActions) {
            performer.decreaseNumActions();
        }
        decreaseNumActions = true;
        doNextMove();
    }
}
