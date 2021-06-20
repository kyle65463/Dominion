package dominion.models.game.cards.actions;

import dominion.models.game.Player;
import dominion.models.game.cards.Card;

import java.util.List;

public interface HasSelection {
    public void performSelection(Player performer, List<Card> cards);
}
