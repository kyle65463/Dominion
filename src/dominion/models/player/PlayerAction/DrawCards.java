package dominion.models.player.PlayerAction;

import dominion.models.areas.LogBox;
import dominion.models.cards.Card;
import dominion.models.player.*;

import java.util.List;

public class DrawCards extends PlayerAction {
    public DrawCards(Player player, int numCards){
        this.player= player;
        this.numCards = numCards;
    }

    private int numCards;

    @Override
    public void perform(HandCards handCards, Deck deck, DiscardPile discardPile, FieldCards fieldCards) {
        // Check bounds
        if (numCards > discardPile.getNumCards() + deck.getNumCards()) {
            List<Card> cards = deck.popCards(deck.getNumCards());
            handCards.addCards(cards);
            List<Card> newCards = discardPile.getCards();
            discardPile.removeCards();
            handCards.addCards(newCards);
            LogBox.logDrawCard(player, cards.size() + newCards.size());
            return;
        }

        // Refill the deck if it's empty
        if (deck.isEmpty()) {
            List<Card> newCards = discardPile.getCards();
            discardPile.removeCards();
            deck.addCards(newCards, true);
        }

        // Draw cards from the deck
        List<Card> cards = deck.popCards(numCards);
        LogBox.logDrawCard(player, cards.size());
        handCards.addCards(cards);

        // Draw cards again if not enough
        if (cards.size() < numCards) {
            new DrawCards(player, numCards - cards.size());
            player.performPlayerAction(new DrawCards(player, numCards - cards.size()));
        }
    }
}
