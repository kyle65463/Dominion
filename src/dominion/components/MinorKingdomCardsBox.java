package dominion.components;

import javafx.scene.layout.GridPane;

import java.util.List;

public class MinorKingdomCardsBox {
    // Constructor
    public MinorKingdomCardsBox(GridPane gridPane) {
        this.gridPane = gridPane;
    }

    // Variables
    GridPane gridPane;

    // Functions
    public void setKingdomCards(List<KingdomCard> kingdomCards) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 2; j++) {
                int index = i * 2 + j;
                if (index >= kingdomCards.size()){
                    break;
                }
                KingdomCard kingdomCard = kingdomCards.get(index);
                kingdomCard.setScale(0.7);
                gridPane.add(kingdomCard.getNode(), j, i);
            }
        }
    }
}
