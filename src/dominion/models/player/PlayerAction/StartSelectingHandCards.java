package dominion.models.player.PlayerAction;

import dominion.core.GameManager;
import dominion.models.events.game.ClearSelectedHandCardsEvent;
import dominion.models.events.game.DoneSelectingHandCardEvent;
import dominion.models.events.game.SelectHandCardEvent;
import dominion.models.player.Player;
import dominion.models.player.container.Deck;
import dominion.models.player.container.DiscardPile;
import dominion.models.player.container.FieldCards;
import dominion.models.player.container.HandCards;

public class StartSelectingHandCards extends PlayerAction {
    public StartSelectingHandCards(String statusText, int cardId) {
        this.statusText = statusText;
        this.cardId = cardId;
    }

    private String statusText;
    private int cardId;

    @Override
    public void perform(Player player, HandCards handCards, Deck deck, DiscardPile discardPile, FieldCards fieldCards) {
        if (GameManager.getCurrentPhase() == GameManager.Phase.SelectingHandCards) {
            player.snapshotStatus();
            player.setActionBarStatus(statusText, "完成", "重新選擇");
            player.setCardSelectedHandler((card) -> {
                if (player.getSelectingHandCardsFilter() == null || player.getSelectingHandCardsFilter().filter(card)) {
                    GameManager.sendEvent(new SelectHandCardEvent(player.getId(), card.getId()));
                }
            });
            player.setActionBarLeftButtonHandler((e) -> {
                GameManager.sendEvent(new ClearSelectedHandCardsEvent(player.getId()));
            });
            player.setActionBarRightButtonHandler((e) -> {
                GameManager.sendEvent(new DoneSelectingHandCardEvent(player.getId(), cardId));
            });
            player.enableLeftButton(false);
            if (player.getExactSelectedCards() > 0) {
                player.enableRightButton(player.getSelectedCardsSize() == player.getExactSelectedCards());
            }
        }
    }
}
