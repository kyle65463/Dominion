package dominion.models.game;

import dominion.models.game.cards.Card;

public interface CardFilter {
    public boolean filter(Card card);
}
