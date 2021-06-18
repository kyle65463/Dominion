package dominion.components;

import javafx.scene.layout.GridPane;

import java.util.List;

public class MajorKingdomCardsBox {
    // Constructor
    public MajorKingdomCardsBox(GridPane gridPane) {
        this.gridPane = gridPane;
    }

    // Variables
    GridPane gridPane;

    // Functions
    public void setKingdomCards(List<KingdomCard> kingdomCards) {
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 5; j++) {
                int index = i * 5 + j;
                if (index >= kingdomCards.size()){
                    break;
                }
                KingdomCard kingdomCard = kingdomCards.get(index);
                gridPane.add(kingdomCard.getNode(), j, i);
            }
        }
    }
}
