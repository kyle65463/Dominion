package dominion.models.events.game;


import dominion.models.areas.DisplayedCard;
import dominion.models.player.Player;

import java.util.List;

public interface HasDisplayedSelection {
    public void performDisplayedSelection(Player performer, List<DisplayedCard> displayedCards);
}
