package dominion.models.cards.actions;

import dominion.core.GameManager;
import dominion.models.areas.DisplayedCard;
import dominion.models.cards.Card;
import dominion.models.cards.CardStyles;
import dominion.models.cards.CardTypes;
import dominion.models.cards.treasures.Treasure;
import dominion.models.events.game.HasDisplayedCardsSelection;
import dominion.models.expansions.Dominion;
import dominion.models.player.Player;
import dominion.models.player.PlayerAction.ReceiveNewHandCard;
import dominion.models.player.PlayerAction.StartSelectingDisplayedCards;
import dominion.models.player.PlayerAction.StartSelectingHandCards;
import dominion.models.player.PlayerAction.TrashHandCards;

import java.util.List;

public class Mine extends Card implements Dominion, Action, HasHandCardsSelection, HasDisplayedCardsSelection {
    public Mine() {
        name = "礦坑";
        description = "你可以移除手上的一張錢幣卡，獲得一張價值至多加3塊的錢幣卡到手中。" ;
        style = CardStyles.white;
        type = CardTypes.action;
        numCost = 5;
    }

    private boolean decreaseNumActions;

    @Override
    public void perform(Player performer, boolean decreaseNumActions) {
        this.decreaseNumActions = decreaseNumActions;
        if(performer.getHandCards().stream().anyMatch(card -> card instanceof Treasure)) {
            GameManager.setCurrentPhase(GameManager.Phase.SelectingHandCards);
            performer.setExactSelectedCards(1);
            performer.setSelectingHandCardsFilter(card -> card instanceof Treasure);
            performer.performAction(new StartSelectingHandCards("選擇要移除的錢幣卡", id));
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
        if (cards.size() > 0) {
            Card card = cards.get(0);
            performer.performAction(new TrashHandCards(cards));

            GameManager.setCurrentPhase(GameManager.Phase.SelectingDisplayedCards);
            performer.setExactSelectedCards(1);
            performer.setSelectingDisplayedCardsFilter( (displayedCard) -> displayedCard.getCard().getNumCost() <= 3 + card.getNumCost() && displayedCard.getCard() instanceof Treasure);
            performer.performAction(new StartSelectingDisplayedCards("選擇要加到手牌的錢幣卡", id));
        } else {
            if (decreaseNumActions) {
                performer.decreaseNumActions();
            }
            doNextMove();
        }
    }

    @Override
    public void performDisplayedSelection(Player performer, List<DisplayedCard> displayedCards) {
        if (displayedCards.size() > 0) {
            DisplayedCard displayedCard = displayedCards.get(0);
            Card card = displayedCard.instantiateNewCard();
            performer.performAction(new ReceiveNewHandCard(card));
            displayedCard.decreaseNumRemain();
        }

        if (decreaseNumActions) {
            performer.decreaseNumActions();
        }
        doNextMove();
    }
}
