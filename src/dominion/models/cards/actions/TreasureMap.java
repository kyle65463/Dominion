package dominion.models.cards.actions;

import dominion.core.GameManager;
import dominion.models.areas.DisplayedCard;
import dominion.models.areas.PurchaseArea;
import dominion.models.cards.curses.Curse;
import dominion.models.cards.treasures.Gold;
import dominion.models.player.Player;
import dominion.models.cards.Card;
import dominion.models.cards.CardStyles;
import dominion.models.cards.CardTypes;

import java.util.List;

public class TreasureMap extends Card implements Action, HasHandCardsSelection {
    // Constructor
    public TreasureMap() {
        name = "藏寶圖";
        description = "移除此卡跟手上的一張藏寶圖，如果你移除了兩張藏寶圖，則獲得四張黃金，且放在你的牌庫頂上。";
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

        performer.trashHandCard(this);
        if(performer.getHandCards().stream().anyMatch(card -> card instanceof TreasureMap)) {
            GameManager.setCurrentPhase(GameManager.Phase.SelectingHandCards);
            performer.setExactSelectingCards(1);
            performer.setSelectingHandCardsFilter(card -> card instanceof TreasureMap);
            performer.startSelectingHandCards("選擇要移除的牌", id);
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
        performer.trashHandCards(cards);
        for(int i = 0; i < 4; i++) {
            Card gold = new Gold();
            DisplayedCard card = PurchaseArea.getDisplayedCardByCard(gold);
            if (card.getNumRemain() > 0) {
                performer.receiveNewCardOnDeck(gold);
                card.decreaseNumRemain();
            }
        }

        if (decreaseNumActions) {
            performer.decreaseNumActions();
        }
        decreaseNumActions = true;
        doNextMove();
    }
}
