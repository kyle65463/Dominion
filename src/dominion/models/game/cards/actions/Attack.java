package dominion.models.game.cards.actions;

import dominion.models.game.Player;

public interface Attack {
    public void performAttack(Player performer, Player attacked);
}
