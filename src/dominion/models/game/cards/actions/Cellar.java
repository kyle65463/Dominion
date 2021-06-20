package dominion.models.game.cards.actions;

import dominion.game.GameManager;
import dominion.models.events.game.DoneSelectingEvent;
import dominion.models.events.game.SelectCardEvent;
import dominion.models.game.CardSelectedHandler;
import dominion.models.game.Player;
import dominion.models.game.cards.Card;
import dominion.utils.CardStyles;
import dominion.utils.CardTypes;
import javafx.event.EventHandler;
import javafx.util.Pair;

import java.util.List;

public class Cellar extends Card implements Action, HasSelection{
    // Constructor
    public Cellar() {
        name = "地窖";
        description = "";
        style = CardStyles.white;
        type = CardTypes.action;
        numCost = 2;
    }

    // Functions
    @Override
    public void perform(Player performer) {
        // Save the status of the performer
        performer.snapshotStatus();

        // Set new handlers
        performer.setActionBarStatus("選擇要丟棄的牌", "完成");
        performer.setCardSelectedHandler((card) -> {
            GameManager.sendEvent(new SelectCardEvent(performer.getId(), card.getId()));
        });
        performer.setActionBarButtonHandler((e) -> {
            GameManager.sendEvent(new DoneSelectingEvent(performer.getId(), id));
        });
    }

    @Override
    public void performSelection(Player performer, List<Card> cards) {
        performer.discardHandCards(cards);
        performer.drawCards(cards.size());
        performer.recoverStatus();

        performer.decreaseNumActions();
        performer.checkActionCardsAndEndPlayingActionPhase();
    }
}
