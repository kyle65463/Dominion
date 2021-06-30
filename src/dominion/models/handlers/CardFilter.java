package dominion.models.handlers;

import dominion.models.cards.Card;

public interface CardFilter {
    public boolean filter(Card card);
}
