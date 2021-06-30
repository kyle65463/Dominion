package dominion.models.areas;


import dominion.models.cards.Card;
import dominion.models.handlers.DisplayedCardSelectedHandler;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PurchaseArea {
    private static DisplayedCardSelectedHandler displayedCardSelectedHandler = (displayedCard -> {
    });

    public static List<DisplayedCard> getDisplayedCards() {
        return Stream.concat(MajorPurchaseArea.getDisplayedCards().stream(),
                MinorPurchaseArea.getDisplayedCards().stream()).collect(Collectors.toList());
    }

    public static DisplayedCard getDisplayedCardById(int id) {
        for (DisplayedCard displayedCard : getDisplayedCards()) {
            if (displayedCard.getId() == id) {
                return displayedCard;
            }
        }
        return null;
    }

    public static DisplayedCard getDisplayedCardByCard(Card card) {
        for (DisplayedCard displayedCard : getDisplayedCards()) {
            if (displayedCard.getCard().getName().equals(card.getName())) {
                return displayedCard;
            }
        }
        return null;
    }

    public static int getNumNoneRemained() {
        int numNoneRemained = 0;
        for (DisplayedCard displayedCard : getDisplayedCards()) {
            if (displayedCard.getNumRemain() == 0) {
                numNoneRemained++;
            }
        }
        return numNoneRemained;
    }

    public static void setDisplayedCardSelectedHandler(DisplayedCardSelectedHandler handler) {
        PurchaseArea.displayedCardSelectedHandler = handler;
        for (DisplayedCard displayedCard : getDisplayedCards()) {
            displayedCard.setOnPressed(
                    PurchaseArea.displayedCardSelectedHandler
            );
        }
    }

    public static void rearrange() {
        for (DisplayedCard displayedCard : getDisplayedCards()) {
            displayedCard.removeHighlight();
        }
    }


    // Functions
    public static boolean isGameOver() {
        return MinorPurchaseArea.isGameOver();
    }
}
