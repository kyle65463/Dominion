package dominion.models.player.PlayerAction;

import dominion.models.player.*;

public abstract class PlayerAction {
    protected Player player;
    public abstract void perform(HandCards handCards, Deck deck, DiscardPile discardPile, FieldCards fieldCards);
}
