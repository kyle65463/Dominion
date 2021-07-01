package dominion.models.cards.actions;

import dominion.core.GameManager;
import dominion.models.expansions.Dominion;
import dominion.models.handlers.CardNextMoveHandler;
import dominion.models.player.Player;
import dominion.models.cards.Card;
import dominion.models.cards.CardStyles;
import dominion.models.cards.CardTypes;
import dominion.models.player.PlayerAction.PlayCard;
import dominion.models.player.PlayerAction.RetrieveHandCardFromFieldCards;
import dominion.models.player.PlayerAction.StartSelectingHandCards;

import java.util.List;

public class ThroneRoom extends Card implements Dominion, Action, HasHandCardsSelection {
    // Constructor
    public ThroneRoom() {
        name = "王座廳";
        description = "你可以將手上一張行動牌打出，並執行兩次效果。";
        style = CardStyles.white;
        type = CardTypes.action;
        numCost = 4;
    }

    // Variables
    private boolean decreaseNumActions = true;

    // Functions
    @Override
    public void perform(Player performer, boolean decreaseNumActions) {
        this.decreaseNumActions = decreaseNumActions;

        if(performer.getHandCards().stream().anyMatch(card -> card instanceof Action)) {
            GameManager.setCurrentPhase(GameManager.Phase.SelectingHandCards);
            performer.setMaxSelectedCards(1);
            performer.setSelectingHandCardsFilter(card -> card instanceof Action);
            performer.performAction(new StartSelectingHandCards("選擇要執行的牌", id));
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
            CardNextMoveHandler handler1 = ()->{
                performer.performAction(new RetrieveHandCardFromFieldCards(card));
                CardNextMoveHandler handler2 = ()->{
                    card.clearCardNextMove();
                    performer.checkActionCardsAndEndPlayingActionPhase();
                };
                performer.performAction(new PlayCard(card.getId(), false, handler2));
            };
            performer.performAction(new PlayCard(cards.get(0).getId(), false, handler1));
        }
        performer.setSelectingHandCardsFilter(null);

        if(decreaseNumActions) {
            performer.decreaseNumActions();
        }
        decreaseNumActions = true;
        doNextMove();
    }
}
