package dominion.controllers.scenes;

import dominion.controllers.components.PlayerStatusController;
import dominion.game.Game;
import dominion.game.GameManager;
import dominion.models.User;
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
    GameScene gameScene;

    @FXML
    void initialize() {
        gameScene = new GameScene(rootNode);

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
    }

    public void initialize(List<User> users, User applicationUser) {
        // Set up initial cards
        List<Card> initialCards = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            initialCards.add(new Card());
        }

        // Set up players
        Player applicationPlayer = new Player(applicationUser);
        List<Player> players = new ArrayList<>();
        FieldCards fieldCards = new FieldCards();
        fieldCards.enableUi(gameScene);
        int index = 0;
        for (User user : users) {
            Player player = new Player(user);
            PlayerStatus playerStatus = player.getPlayerStatus();
            if(user == applicationUser) {
                applicationPlayer = player;
                player.enableUi(gameScene);
                playerStatusBox.getChildren().add(playerStatus.getController().getRootNode());
            }
            else {
                opponentsStatusBox.add(playerStatus.getController().getRootNode(), index, 0);
                index++;
            }

            player.setDeckCards(initialCards);
            player.setFieldCards(fieldCards);
            players.add(new Player(user));
        }

        // Set up game manager
        GameManager gameManager = new GameManager(applicationPlayer, players);

        // Run the game
        Game game = new Game(gameManager);
        game.start();
    }
}
