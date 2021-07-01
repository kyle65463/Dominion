package dominion.models.player.PlayerAction;


import dominion.models.player.Player;
import dominion.models.player.container.Deck;
import dominion.models.player.container.DiscardPile;
import dominion.models.player.container.FieldCards;
import dominion.models.player.container.HandCards;

public abstract class PlayerAction {
    protected Player player;
    public abstract void perform(HandCards handCards, Deck deck, DiscardPile discardPile, FieldCards fieldCards);
}
