package dominion.models.cards.actions;

import dominion.models.player.Player;
import dominion.models.cards.Card;

import java.util.List;

public interface HasHandCardsSelection {
    public void performSelection(Player performer, List<Card> cards);
}
