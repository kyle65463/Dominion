package dominion.models.game.cards.actions;

import dominion.game.GameManager;
import dominion.models.events.game.*;
import dominion.models.game.DisplayedCard;
import dominion.models.game.Player;
import dominion.models.game.cards.Card;
import dominion.utils.CardStyles;
import dominion.utils.CardTypes;

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
        performer.snapshotStatus();


        performer.setMaxSelectingCards(1);
        GameManager.setCurrentPhase(GameManager.Phase.SelectingDisplayedCards);
        performer.setActionBarStatus("選擇加到手牌的牌", "完成");
        performer.setActionBarButtonHandler((e) -> {
            GameManager.sendEvent(new DoneSelectingDisplayedCardEvent(performer.getId(), id));
        });
    }

    @Override
    public void performDisplayedSelection(Player performer, List<DisplayedCard> displayedCards) {
        System.out.println("in perform displayed selection");
        System.out.println("Displayed selected: " + displayedCards);
        for (DisplayedCard displayedCard : displayedCards) {
            Card card = displayedCard.getCard();
            try {
                Card newCard = (Card) card.clone();
                System.out.println("new card: " + newCard);
                newCard.setNumRemain(1);
                performer.receiveNewHandCard(newCard);
                displayedCard.decreaseNumRemain();
            }
            catch (Exception e) {

            }
        }


        GameManager.setCurrentPhase(GameManager.Phase.SelectingHandCards);
        performer.snapshotStatus();
        performer.setMaxSelectingCards(1);
        performer.setActionBarStatus("選擇放回牌庫頂的牌", "完成");
        performer.setCardSelectedHandler((card) -> {
            GameManager.sendEvent(new SelectHandCardEvent(performer.getId(), card.getId()));
        });
        performer.setActionBarButtonHandler((e) -> {
            GameManager.sendEvent(new DoneSelectingHandCardEvent(performer.getId(), id));
        });
    }

    @Override
    public void performSelection(Player performer, List<Card> cards) {
        System.out.println("in perform selection");

        if(decreaseNumActions) {
            performer.decreaseNumActions();
        }
        performer.recoverStatus();
        performer.checkActionCardsAndEndPlayingActionPhase();
    }
}
