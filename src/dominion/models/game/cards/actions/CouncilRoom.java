package dominion.models.game.cards.actions;

import dominion.game.GameManager;
import dominion.models.game.Player;
import dominion.models.game.cards.Card;
import dominion.utils.CardStyles;
import dominion.utils.CardTypes;

public class CouncilRoom extends Card implements Action {
    // Constructor
    public CouncilRoom() {
        name = "會議廳";
        description = "";
        style = CardStyles.white;
        type = CardTypes.action;
        numCost = 5;
    }

    // Functions
    @Override
    public void perform(Player performer, boolean decreaseNumActions) {
        performer.drawCards(4);
        performer.increaseNumPurchases(1);
        for(Player player : GameManager.getPlayers()) {
            if(player.getId() != performer.getId()){
                player.drawCards(1);
            }
        }

        if(decreaseNumActions) {
            performer.decreaseNumActions();
        }
        performer.checkActionCardsAndEndPlayingActionPhase();
    }
}
