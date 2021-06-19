package dominion.models.game.cards.actions;

import dominion.models.game.Player;

public interface Action {
    public void perform(Player performer);
}
