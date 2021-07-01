package dominion.models.handlers;

import dominion.models.cards.Card;

public interface CardFilter {
    boolean filter(Card card);
}
