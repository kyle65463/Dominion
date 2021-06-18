package dominion.controllers.scenes;

import dominion.components.*;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class GameController {
    @FXML
    AnchorPane rootNode;
    @FXML
    GridPane majorKingdomCardsBoxNode;
    @FXML
    GridPane minorKingdomCardsBoxNode;
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
        GameScene gameScene = new GameScene(rootNode);

        MajorKingdomCardsBox majorKingdomCardsBox = new MajorKingdomCardsBox(majorKingdomCardsBoxNode);
        MinorKingdomCardsBox minorKingdomCardsBox = new MinorKingdomCardsBox(minorKingdomCardsBoxNode);
        List<KingdomCard> majorKingdomCards = new ArrayList<>();
        List<KingdomCard> minorKingdomCards = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            majorKingdomCards.add(new KingdomCard());
        }
        for (int i = 0; i < 7; i++) {
            minorKingdomCards.add(new KingdomCard());
        }
        majorKingdomCardsBox.setKingdomCards(majorKingdomCards);
        minorKingdomCardsBox.setKingdomCards(minorKingdomCards);

        ActionBar a = new ActionBar();
        actionBar.getChildren().add(a.getNode());
        for (int i = 0; i < 3; i++) {
            opponentsNameStatusBox.add(new NameStatus().getNode(), i, 0);
        }

        playerNameStatusBox.getChildren().add(new NameStatus().getNode());

        HandCardsBox handCardsBox = new HandCardsBox(gameScene);
        Card card = new Card();
        handCardsBox.addCard(new Card());
        handCardsBox.addCard(new Card());
        handCardsBox.addCard(new Card());
        handCardsBox.addCard(card);


        FieldCardsBox fieldCardsBox = new FieldCardsBox(gameScene);
        fieldCardsBox.addCard(new Card());
        fieldCardsBox.addCard(new Card());
        fieldCardsBox.addCard(new Card());
        fieldCardsBox.addCard(new Card());
        fieldCardsBox.addCard(new Card());

        DiscardPileBox discardPileBox = new DiscardPileBox(gameScene);
        discardPileBox.addCard(new Card());

        DeckBox deckBox = new DeckBox(gameScene);
        deckBox.addCard(new Card());

        a.setActionButtonOnPressed((e) -> {
            handCardsBox.removeCard(card);
            fieldCardsBox.addCard(card);
        });
    }
}
