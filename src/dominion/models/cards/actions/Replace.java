package dominion.models.cards.actions;

import dominion.core.GameManager;
import dominion.models.areas.DisplayedCard;
import dominion.models.cards.AttackPlayers;
import dominion.models.cards.Card;
import dominion.models.cards.CardStyles;
import dominion.models.cards.CardTypes;
import dominion.models.cards.curses.Curse;
import dominion.models.cards.treasures.Treasure;
import dominion.models.cards.victories.Victory;
import dominion.models.events.game.DoneAttackingEvent;
import dominion.models.events.game.HasDisplayedCardsSelection;
import dominion.models.player.Player;

import java.util.List;

public class Replace extends Card implements Action, Attack, HasHandCardsSelection, HasDisplayedCardsSelection {
    public Replace() {
        name = "替身";
        description = "移除手上一張卡片，獲得一張價值至多加2的卡片，如果獲得的卡是錢幣卡或是行動卡，將卡片放到牌庫頂。如果是分數卡，則其他玩家獲得一張詛咒。";
        style = CardStyles.white;
        type = CardTypes.action;
        numCost = 5;
        expansion = Expansion.Intrigue;
    }

    // Variables
    boolean decreaseNumActions = true;

    @Override
    public void perform(Player performer, boolean decreaseNumActions) {
        this.decreaseNumActions = decreaseNumActions;
        GameManager.setCurrentPhase(GameManager.Phase.SelectingHandCards);
        performer.setMaxSelectingCards(1);
        performer.startSelectingHandCards("選擇要移除的牌", id);
    }

    @Override
    public void performSelection(Player performer, List<Card> cards) {
        if (cards.size() > 0) {
            Card card = cards.get(0);
            performer.trashHandCards(cards);

            GameManager.setCurrentPhase(GameManager.Phase.SelectingDisplayedCards);
            performer.setMaxSelectingCards(1);
            performer.setSelectingDisplayedCardsFilter(displayedCard -> displayedCard.getCard().getNumCost() <= card.getNumCost() + 2);
            performer.startSelectingDisplayedCards("獲得一張牌", id);
        } else {
            if (decreaseNumActions) {
                performer.decreaseNumActions();
            }
            decreaseNumActions = true;
            performer.checkActionCardsAndEndPlayingActionPhase();
        }
    }

    @Override
    public void performDisplayedSelection(Player performer, List<DisplayedCard> displayedCards) {
        if (displayedCards.size() > 0) {
            DisplayedCard displayedCard = displayedCards.get(0);
            Card card = displayedCard.instantiateNewCard();
            displayedCard.decreaseNumRemain();

            if (card instanceof Action || card instanceof Treasure) {
                performer.receiveNewCardOnDeck(card);
            }

            if (card instanceof Victory) {
                performer.receiveNewCard(card);
                Thread thread = new Thread(new AttackPlayers(performer, this));
                thread.start();
            }
            else {
                if (decreaseNumActions) {
                    performer.decreaseNumActions();
                }
                decreaseNumActions = true;
                performer.checkActionCardsAndEndPlayingActionPhase();
            }
        }
        else {
            if (decreaseNumActions) {
                performer.decreaseNumActions();
            }
            decreaseNumActions = true;
            performer.checkActionCardsAndEndPlayingActionPhase();
        }
    }

    @Override
    public void performAttack(Player performer, Player attacked) {
        Card curse = new Curse();
        DisplayedCard card = GameManager.getDisplayCardByCard(curse);
        if (card.getNumRemain() > 0) {
            attacked.receiveNewCard(curse);
            card.decreaseNumRemain();
        }
        GameManager.sendEvent(new DoneAttackingEvent(attacked.getId()));
    }

    @Override
    public void performAfterAttack(Player performer) {
        if (decreaseNumActions) {
            performer.decreaseNumActions();
        }
        decreaseNumActions = true;
        performer.checkActionCardsAndEndPlayingActionPhase();
    }
}