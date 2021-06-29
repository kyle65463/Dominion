package dominion.models.game;

import dominion.controllers.components.DisplayedCardController;
import dominion.models.game.DisplayedCard;
import dominion.models.game.cards.Card;
import javafx.scene.layout.GridPane;

import java.util.List;

public class MinorPurchaseArea extends PurchaseArea{
    // Constructor
    public MinorPurchaseArea(GridPane gridPane) {
        super(gridPane, 4, 2);
    }

    // Functions
    public boolean isGameOver() {
        return displayedCards.get(0).getNumRemain() == 0;
    }
}
