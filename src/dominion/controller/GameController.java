package dominion.controller;

import dominion.component.*;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;

public class GameController {
    @FXML
    AnchorPane gameScene;
    @FXML
    GridPane kingdomCardBox;
    @FXML
    GridPane coinCardBox;
    @FXML
    VBox messageBox;
    @FXML
    Pane actionBar;
    @FXML
    GridPane opponentsNameStatusBox;
    @FXML
    Pane playerNameStatusBox;

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

        actionBar.getChildren().add(new ActionBar().getNode());
        for (int i = 0; i < 3; i++) {
            opponentsNameStatusBox.add(new NameStatus().getNode(), i, 0);
        }

        playerNameStatusBox.getChildren().add(new NameStatus().getNode());

        HandCardsBox handCardsBox = new HandCardsBox(gameScene);
        Card card = new Card();
        handCardsBox.addCard(new Card());
        handCardsBox.addCard(card);

        FieldCardsBox fieldCardsBox = new FieldCardsBox(gameScene);
        fieldCardsBox.addCard(new Card());
        fieldCardsBox.addCard(new Card());
        fieldCardsBox.addCard(new Card());
        fieldCardsBox.addCard(new Card());
        fieldCardsBox.addCard(new Card());
        fieldCardsBox.addCard(card);
    }
}
