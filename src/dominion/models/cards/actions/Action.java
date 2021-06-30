package dominion.models.cards.actions;

import dominion.models.player.Player;

public interface Action {
    public void perform(Player performer, boolean decreaseNumActions);
}
