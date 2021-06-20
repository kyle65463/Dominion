package dominion.models.game.cards.actions;

import dominion.game.GameManager;
import dominion.models.events.game.DoneSelectingHandCardEvent;
import dominion.models.events.game.SelectHandCardEvent;
import dominion.models.game.Player;
import dominion.models.game.cards.Card;
import dominion.utils.CardStyles;
import dominion.utils.CardTypes;

import java.util.List;

public class Chapel extends Card implements Action, HasSelection{
    // Constructor
    public Chapel() {
        name = "禮拜堂";
        description = "";
        style = CardStyles.white;
        type = CardTypes.action;
        numCost = 2;
    }

    // Variables
    private boolean decreaseNumActions = true;

    // Functions
    @Override
    public void perform(Player performer, boolean decreaseNumActions) {
        this.decreaseNumActions = decreaseNumActions;
        // Save the status of the performer
        GameManager.setCurrentPhase(GameManager.Phase.SelectingHandCards);
        performer.snapshotStatus();
        performer.setMaxSelectingCards(4);

        // Set new handlers
        performer.setActionBarStatus("選擇要移除的牌", "完成");
        performer.setCardSelectedHandler((card) -> {
            GameManager.sendEvent(new SelectHandCardEvent(performer.getId(), card.getId()));
        });
        performer.setActionBarButtonHandler((e) -> {
            GameManager.sendEvent(new DoneSelectingHandCardEvent(performer.getId(), id));
        });
    }

    @Override
    public void performSelection(Player performer, List<Card> cards) {
        performer.trashHandCards(cards);
        performer.recoverStatus();

        if(decreaseNumActions) {
            performer.decreaseNumActions();
        }
        decreaseNumActions = true;
        performer.checkActionCardsAndEndPlayingActionPhase();
    }
}
