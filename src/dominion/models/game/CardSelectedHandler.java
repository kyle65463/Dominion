package dominion.models.game;

import dominion.models.game.cards.Card;
import javafx.event.EventHandler;

public interface CardSelectedHandler {
    public void onSelected(Card card);
}
