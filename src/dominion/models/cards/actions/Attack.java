package dominion.models.cards.actions;

import dominion.models.player.Player;

public interface Attack {
    public void performAttack(Player performer, Player attacked);
    public void performAfterAttack(Player performer);
}
