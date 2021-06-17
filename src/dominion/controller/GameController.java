package dominion.controller;

import dominion.component.ActionBar;
import dominion.component.KingdomCard;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class GameController {
    @FXML
    GridPane kingdomCardBox;
    @FXML
    GridPane coinCardBox;
    @FXML
    VBox messageBox;
    @FXML
    Pane actionBar;

    @FXML
    void initialize() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 2; j++) {
                kingdomCardBox.add(new KingdomCard().getNode(), i, j);
            }
        }

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 4; j++) {
                KingdomCard kingdomCard = new KingdomCard();
                kingdomCard.setScale(0.7);
                coinCardBox.add(kingdomCard.getNode(), i, j);
            }
        }

        actionBar.getChildren().add((Pane) new ActionBar().getNode());
    }
}
