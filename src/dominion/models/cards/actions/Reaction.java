package dominion.models.cards.actions;

import dominion.models.player.Player;

public interface Reaction {
    public void performReaction(Player performer);
}