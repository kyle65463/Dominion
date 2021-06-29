package dominion.models.events.game;

import dominion.models.game.DisplayedCard;
import dominion.models.game.Player;

import java.util.List;

public interface HasDisplayedSelection {
    public void performDisplayedSelection(Player performer, List<DisplayedCard> displayedCards);
}
