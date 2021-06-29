package dominion.models.game.cards.actions;

import dominion.models.game.Player;

public interface Reaction {
    public void performReaction(Player performer);
}