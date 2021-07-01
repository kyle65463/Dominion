package dominion.models.player.PlayerAction;

import dominion.core.GameManager;
import dominion.models.areas.PurchaseArea;
import dominion.models.events.game.ClearSelectedDisplayedCardsEvent;
import dominion.models.events.game.DoneSelectingDisplayedCardEvent;
import dominion.models.events.game.SelectDisplayedCardEvent;
import dominion.models.player.Player;
import dominion.models.player.container.Deck;
import dominion.models.player.container.DiscardPile;
import dominion.models.player.container.FieldCards;
import dominion.models.player.container.HandCards;

public class StartSelectingDisplayedCards extends PlayerAction {
    public StartSelectingDisplayedCards(String statusText, int cardId) {
        this.statusText = statusText;
        this.cardId = cardId;
    }

    private String statusText;
    private int cardId;


    @Override
    public void perform(Player player, HandCards handCards, Deck deck, DiscardPile discardPile, FieldCards fieldCards) {
        if (GameManager.getCurrentPhase() == GameManager.Phase.SelectingDisplayedCards) {
            player.snapshotStatus();
            player.setActionBarStatus(statusText, "完成", "重新選擇");
            PurchaseArea.setDisplayedCardSelectedHandler((displayedCard) -> {
                if (player.getSelectingDisplayedCardsFilter() == null || player.getSelectingDisplayedCardsFilter().filter(displayedCard)) {
                    GameManager.sendEvent(new SelectDisplayedCardEvent(player.getId(), displayedCard.getId()));
                }
            });
            player.setActionBarLeftButtonHandler((e) -> {
                GameManager.sendEvent(new ClearSelectedDisplayedCardsEvent(player.getId()));
            });
            player.setActionBarRightButtonHandler((e) -> {
                PurchaseArea.rearrange();
                GameManager.sendEvent(new DoneSelectingDisplayedCardEvent(player.getId(), cardId));
            });
            player.setCardSelectedHandler(((card) -> {
            }));
            player.enableLeftButton(false);
        }
    }
}
