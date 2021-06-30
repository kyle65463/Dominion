package dominion.models.cards.actions;

import dominion.core.GameManager;
import dominion.models.player.Player;
import dominion.models.cards.Card;
import dominion.models.cards.CardStyles;
import dominion.models.cards.CardTypes;

public class CouncilRoom extends Card implements Action {
    // Constructor
    public CouncilRoom() {
        name = "會議廳";
        description = "+4 卡牌\n+1 購買\n\n其他玩家抽取一張卡片。";
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
