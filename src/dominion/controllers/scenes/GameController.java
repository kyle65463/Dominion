package dominion.controllers.scenes;

import dominion.connections.Connection;
import dominion.game.Game;
import dominion.game.GameManager;
import dominion.game.Logger;
import dominion.models.User;
import dominion.models.game.*;
import dominion.models.game.cards.Card;
import dominion.models.game.cards.actions.*;
import dominion.models.game.cards.curses.Curse;
import dominion.models.game.cards.treasures.Copper;
import dominion.models.game.cards.treasures.Gold;
import dominion.models.game.cards.treasures.Silver;
import dominion.models.game.cards.victories.Duchy;
import dominion.models.game.cards.victories.Estate;
import dominion.models.game.cards.victories.Gardens;
import dominion.models.game.cards.victories.Province;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
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
    VBox messageBoxNode;
    @FXML
    ScrollPane scrollPane;
    @FXML
    GridPane opponentsStatusBox;
    @FXML
    Pane playerStatusBox;
    @FXML
    Pane winnerBox;
    @FXML
    Label winnerLabel;


    @FXML
    void initialize() {

    }

    public void initialize(List<User> users, User applicationUser, Connection connection, int randomSeed) {
        scrollPane.vvalueProperty().bind(messageBoxNode.heightProperty());
        WinnerDialog.initialize(winnerBox, winnerLabel);
        Logger.initialize(messageBoxNode);
        GameManager.setRandomSeed(randomSeed);
        GameScene.initialize(rootNode);

        // Set up players
        Player applicationPlayer = new Player(applicationUser);
        List<Player> players = new ArrayList<>();
        FieldCards fieldCards = new FieldCards();
        fieldCards.enableUi();
        int index = 0;
        for (User user : users) {
            Player player = new Player(user);
            PlayerStatus playerStatus = player.getPlayerStatus();
            if(user.getId() == applicationUser.getId()) {
                applicationPlayer = player;
                player.enableUi();
                playerStatusBox.getChildren().add(playerStatus.getController().getRootNode());
            }
            else {
                opponentsStatusBox.add(playerStatus.getController().getRootNode(), index, 0);
                index++;
            }

            List<Card> initialCards = new ArrayList<>();
            for (int i = 0; i < 7; i++) {
                initialCards.add(new Copper());
            }
            for (int i = 0; i < 3; i++) {
                initialCards.add(new Estate());
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

        // Top 5
        majorKingdomCards.add(new DisplayedCard(new Smithy(), 10, applicationPlayer, 7));
        majorKingdomCards.add(new DisplayedCard(new CouncilRoom(), 10, applicationPlayer, 8));
        majorKingdomCards.add(new DisplayedCard(new Laboratory(), 10, applicationPlayer, 9));
        majorKingdomCards.add(new DisplayedCard(new Market(), 10, applicationPlayer, 10));
        majorKingdomCards.add(new DisplayedCard(new Festival(), 10, applicationPlayer, 11));

        // Bottom 5
        majorKingdomCards.add(new DisplayedCard(new Cellar(), 10, applicationPlayer, 12));
        majorKingdomCards.add(new DisplayedCard(new Chapel(), 10, applicationPlayer, 13));
        majorKingdomCards.add(new DisplayedCard(new Village(), 10, applicationPlayer, 14));
        majorKingdomCards.add(new DisplayedCard(new MoneyLender(), 10, applicationPlayer, 15));
        majorKingdomCards.add(new DisplayedCard(new Gardens(), 4 * users.size(), applicationPlayer, 16));
        majorPurchaseArea.setDisplayedCards(majorKingdomCards);

        // Scores
        minorKingdomCards.add(new DisplayedCard(new Province(), 4 * users.size(), applicationPlayer, 0));
        minorKingdomCards.add(new DisplayedCard(new Duchy(), 4 * users.size(), applicationPlayer, 1));
        minorKingdomCards.add(new DisplayedCard(new Estate(), 4 * users.size(), applicationPlayer, 2));
        minorKingdomCards.add(new DisplayedCard(new Curse(), 10 * (users.size() - 1), applicationPlayer, 3));
        minorKingdomCards.add(new DisplayedCard(new Gold(), 30, applicationPlayer, 4));
        minorKingdomCards.add(new DisplayedCard(new Silver(), 40, applicationPlayer, 5));
        minorKingdomCards.add(new DisplayedCard(new Copper(), 60 - 7 * users.size(), applicationPlayer, 6));
        minorPurchaseArea.setDisplayedCards(minorKingdomCards);

        // Set up game manager
        GameManager.initialize(players, connection, applicationPlayer, majorPurchaseArea, minorPurchaseArea, randomSeed);
        for(Player player : players){
            player.drawCards(5);
            player.setActionBarStatus("等待其他玩家的回合", "");
            player.reset();
        }

        // Run the game
        Game game = new Game();
        Thread thread = new Thread(game);
        thread.start();
    }
}
