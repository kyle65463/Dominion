package dominion.models.game;

import dominion.controllers.components.DisplayedCardController;
import dominion.models.game.DisplayedCard;
import dominion.models.game.cards.Card;
import javafx.scene.layout.GridPane;

import java.util.List;

public class MajorPurchaseArea extends PurchaseArea {
    // Constructor
    public MajorPurchaseArea(GridPane gridPane) {
        super(gridPane, 2, 5);
    }
}
