package dominion.models.areas;

import javafx.scene.layout.GridPane;

public class MinorPurchaseArea extends PurchaseArea {
    // Constructor
    public MinorPurchaseArea(GridPane gridPane) {
        super(gridPane, 4, 2);
    }

    // Functions
    public boolean isGameOver() {
        return displayedCards.get(0).getNumRemain() == 0;
    }
}
