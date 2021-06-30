package dominion.models.cards.actions;

import dominion.core.GameManager;
import dominion.models.areas.PurchaseArea;
import dominion.models.cards.Card;
import dominion.models.cards.CardStyles;
import dominion.models.cards.CardTypes;
import dominion.models.events.game.*;
import dominion.models.areas.DisplayedCard;
import dominion.models.player.Player;

import java.util.List;

public class Artisan extends Card implements Action, HasSelection, HasDisplayedSelection {
    // Constructor
    public Artisan() {
        name = "藝術家";
        description = "";
        style = CardStyles.white;
        type = CardTypes.action;
        numCost = 6;
    }

    private int selection_time = 0;
    private boolean decreaseNumActions = true;

    @Override
    public void perform(Player performer, boolean decreaseNumActions) {
        this.decreaseNumActions = decreaseNumActions;
        performer.setCardSelectedHandler((card -> {}));


        GameManager.setCurrentPhase(GameManager.Phase.SelectingDisplayedCards);
        performer.setMaxSelectingCards(1);
        performer.setSelectingDisplayedCardsFilter(((displayedCard) -> {
            return displayedCard.getCard().getNumCost() <= 5;
        }));
        performer.setActionBarRightButtonHandler((e) -> {
            GameManager.sendEvent(new DoneSelectingDisplayedCardEvent(performer.getId(), id));
        });
        performer.startSelectingDisplayedCards("選擇要加到手牌的牌", id);
    }

    @Override
    public void performDisplayedSelection(Player performer, List<DisplayedCard> displayedCards) {
        try {
            DisplayedCard displayedCard = displayedCards.get(0);
            Card card = displayedCard.getCard();
            Card newCard = (Card) card.clone();
            newCard.setNumRemain(1);
            performer.receiveNewHandCard(newCard);
            displayedCard.decreaseNumRemain();
        } catch (Exception e) {

        }
        PurchaseArea.setDisplayedCardSelectedHandler((displayedCard) -> {
            displayedCard.setDisplayCardEventHandler(displayedCard.getOriginalHandler());
        });
        performer.removeCardSelectedHandler();

        GameManager.setCurrentPhase(GameManager.Phase.SelectingHandCards);
        performer.setSelectingHandCardsFilter(null);
        performer.setMaxSelectingCards(1);
        performer.setExactSelectingCards(1);
        performer.startSelectingHandCards("選擇放回牌庫頂的牌", id);
    }

    @Override
    public void performSelection(Player performer, List<Card> cards) {
        if(decreaseNumActions) {
            performer.decreaseNumActions();
        }
        Card card;
        try {
            card = cards.get(0);
            performer.removeHandCard(card);
            performer.gainCardOntoDeck(card);
        } catch (Exception e) {
        }
        performer.checkActionCardsAndEndPlayingActionPhase();
    }
}
