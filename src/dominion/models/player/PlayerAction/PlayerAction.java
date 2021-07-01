package dominion.models.player.PlayerAction;


import dominion.models.player.Player;
import dominion.models.player.container.Deck;
import dominion.models.player.container.DiscardPile;
import dominion.models.player.container.FieldCards;
import dominion.models.player.container.HandCards;

public abstract class PlayerAction {
    public abstract void perform(Player player, HandCards handCards, Deck deck, DiscardPile discardPile, FieldCards fieldCards);
}
