package dominion.controllers.scenes;

import dominion.controllers.components.PlayerStatusController;
import dominion.models.game.*;
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
    GridPane opponentsStatusBox;
    @FXML
    Pane playerStatusBox;

    @FXML
    void initialize() {
        GameScene gameScene = new GameScene(rootNode);

        MajorPurchaseArea majorPurchaseArea = new MajorPurchaseArea(majorKingdomCardsBoxNode);
        MinorPurchaseArea minorPurchaseArea = new MinorPurchaseArea(minorKingdomCardsBoxNode);
        List<DisplayedCard> majorKingdomCards = new ArrayList<>();
        List<DisplayedCard> minorKingdomCards = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            majorKingdomCards.add(new DisplayedCard());
        }
        for (int i = 0; i < 7; i++) {
            minorKingdomCards.add(new DisplayedCard());
        }
        majorPurchaseArea.setDisplayedCards(majorKingdomCards);
        minorPurchaseArea.setDisplayedCards(minorKingdomCards);

        ActionBar actionBar = new ActionBar(gameScene);
        for (int i = 0; i < 3; i++) {
            PlayerStatus playerStatus = new PlayerStatus();
            PlayerStatusController playerStatusController = playerStatus.getController();
            opponentsStatusBox.add(playerStatusController.getRootNode(), i, 0);
        }

        PlayerStatus playerStatus = new PlayerStatus();
        PlayerStatusController playerStatusController = playerStatus.getController();
        playerStatusBox.getChildren().add(playerStatusController.getRootNode());

        HandCards handCards = new HandCards(gameScene);
        Card card = new Card();
        handCards.addCard(new Card());
        handCards.addCard(new Card());
        handCards.addCard(new Card());
        handCards.addCard(card);


        FieldCards fieldCards = new FieldCards(gameScene);
        fieldCards.addCard(new Card());
        fieldCards.addCard(new Card());
        fieldCards.addCard(new Card());
        fieldCards.addCard(new Card());
        fieldCards.addCard(new Card());

        DiscardPile discardPile = new DiscardPile(gameScene);
        discardPile.addCard(new Card());

        Deck deckBox = new Deck(gameScene);
        deckBox.addCard(new Card());

//        a.setActionButtonOnPressed((e) -> {
//            handCards.removeCard(card);
//            fieldCards.addCard(card);
//        });
    }
}
