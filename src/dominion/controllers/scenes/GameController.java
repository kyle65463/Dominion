package dominion.controllers.scenes;

import dominion.game.Game;
import dominion.game.GameManager;
import dominion.models.User;
import dominion.models.game.*;
import dominion.models.game.cards.Card;
import dominion.models.game.cards.curses.Curse;
import dominion.models.game.cards.treasures.Copper;
import dominion.models.game.cards.treasures.Gold;
import dominion.models.game.cards.treasures.Silver;
import dominion.models.game.cards.victories.Duchy;
import dominion.models.game.cards.victories.Estate;
import dominion.models.game.cards.victories.Province;
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

    }

    public void initialize(List<User> users, User applicationUser) {
        gameScene = new GameScene(rootNode);

        // Set up initial cards
        List<Card> initialCards = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            initialCards.add(new Copper());
        }
        for (int i = 0; i < 3; i++) {
            initialCards.add(new Estate());
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
            players.add(player);
        }

        // Set up purchase areas
        MajorPurchaseArea majorPurchaseArea = new MajorPurchaseArea(majorKingdomCardsBoxNode);
        MinorPurchaseArea minorPurchaseArea = new MinorPurchaseArea(minorKingdomCardsBoxNode);
        List<DisplayedCard> majorKingdomCards = new ArrayList<>();
        List<DisplayedCard> minorKingdomCards = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            majorKingdomCards.add(new DisplayedCard(new Copper(), 10, applicationPlayer));
        }
        minorKingdomCards.add(new DisplayedCard(new Province(), 4 * users.size(), applicationPlayer));
        minorKingdomCards.add(new DisplayedCard(new Duchy(), 4 * users.size(), applicationPlayer));
        minorKingdomCards.add(new DisplayedCard(new Estate(), 4 * users.size(), applicationPlayer));
        minorKingdomCards.add(new DisplayedCard(new Curse(), 10 * users.size(), applicationPlayer));
        minorKingdomCards.add(new DisplayedCard(new Gold(), 30, applicationPlayer));
        minorKingdomCards.add(new DisplayedCard(new Silver(), 40, applicationPlayer));
        minorKingdomCards.add(new DisplayedCard(new Copper(), 60 - 7 * users.size(), applicationPlayer));

        majorPurchaseArea.setDisplayedCards(majorKingdomCards);
        minorPurchaseArea.setDisplayedCards(minorKingdomCards);

        // Set up game manager
        GameManager.initialize(players);
        for(Player player : players){
            player.drawCards(5);
        }

        applicationPlayer.selectTreasureCards();

        // Run the game
        Game game = new Game();
        Thread thread = new Thread(game);
        thread.start();
    }
}
