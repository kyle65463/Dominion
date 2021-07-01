package dominion.models.cards.actions;

import dominion.core.GameManager;
import dominion.models.cards.Card;
import dominion.models.cards.CardStyles;
import dominion.models.cards.CardTypes;
import dominion.models.events.game.*;
import dominion.models.areas.DisplayedCard;
import dominion.models.expansions.Dominion;
import dominion.models.player.Player;

import java.util.List;

public class Artisan extends Card implements Dominion, Action, HasHandCardsSelection, HasDisplayedCardsSelection {
    // Constructor
    public Artisan() {
        name = "藝術家";
        description = "獲得一張價值至多5的卡片到手中，從手牌中選擇一張卡片放回牌庫頂。";
        style = CardStyles.white;
        type = CardTypes.action;
        numCost = 6;
    }

    // Variables
    private boolean decreaseNumActions = true;

    // Functions
    @Override
    public void perform(Player performer, boolean decreaseNumActions) {
        this.decreaseNumActions = decreaseNumActions;
        GameManager.setCurrentPhase(GameManager.Phase.SelectingDisplayedCards);
        performer.setMaxSelectedCards(1);
        performer.setSelectingDisplayedCardsFilter(displayedCard -> displayedCard.getCard().getNumCost() <= 5);
        performer.startSelectingDisplayedCards("選擇要加到手牌的牌", id);
    }

    @Override
    public void performDisplayedSelection(Player performer, List<DisplayedCard> displayedCards) {
        // Add the card to hand cards
        if(displayedCards.size() > 0) {
            DisplayedCard displayedCard = displayedCards.get(0);
            Card card = displayedCard.instantiateNewCard();
            performer.receiveNewHandCard(card);
            displayedCard.decreaseNumRemain();
        }

        // Select the card that placed back to deck
        if(performer.getHandCards().size() > 0) {
            GameManager.setCurrentPhase(GameManager.Phase.SelectingHandCards);
            performer.setExactSelectedCards(1);
            performer.startSelectingHandCards("選擇放回牌庫頂的牌", id);
        }
        else{
            if (decreaseNumActions) {
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

        if (decreaseNumActions) {
            performer.decreaseNumActions();
        }
        doNextMove();
    }
}
