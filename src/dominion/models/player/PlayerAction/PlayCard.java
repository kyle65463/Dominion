package dominion.models.player.PlayerAction;

import dominion.models.areas.LogBox;
import dominion.models.cards.Card;
import dominion.models.cards.actions.Action;
import dominion.models.cards.treasures.Treasure;
import dominion.models.handlers.AfterPlayCardHandler;
import dominion.models.handlers.CardNextMoveHandler;
import dominion.models.player.Player;
import dominion.models.player.container.Deck;
import dominion.models.player.container.DiscardPile;
import dominion.models.player.container.FieldCards;
import dominion.models.player.container.HandCards;

public class PlayCard extends PlayerAction {
    public PlayCard(Player player, int cardId, boolean decreaseNumActions, CardNextMoveHandler cardNextMoveHandler) {
        this.player = player;
        this.cardId = cardId;
        this.decreaseNumActions = decreaseNumActions;
        this.cardNextMoveHandler = cardNextMoveHandler;
    }

    private int cardId;
    private boolean decreaseNumActions;
    private CardNextMoveHandler cardNextMoveHandler;

    @Override
    public void perform(HandCards handCards, Deck deck, DiscardPile discardPile, FieldCards fieldCards) {
        Card card = handCards.getCardByCardId(cardId);
        LogBox.logPlayCard(player, card);
        handCards.removeCard(card);
        fieldCards.addCard(card);
        if (card instanceof Treasure) {
            player.increaseNumCoins(((Treasure) card).getNumValue());
        } else if (card instanceof Action) {
            card.setCardNextMove(cardNextMoveHandler == null ? ()->{
                player.checkActionCardsAndEndPlayingActionPhase();
            } : cardNextMoveHandler);
            ((Action) card).perform(player, decreaseNumActions);
        }

        AfterPlayCardHandler afterPlayCardHandler;
        if (player.getAfterPlayCardHandler() != null) {
            afterPlayCardHandler = player.getAfterPlayCardHandler();
        } else {
            afterPlayCardHandler = ()->{};
        }
        player.setAfterPlayerActionHandler(()->{
            afterPlayCardHandler.perform();
        });
    }
}
