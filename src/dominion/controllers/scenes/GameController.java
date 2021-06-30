package dominion.controllers.scenes;

import dominion.connections.Connection;
import dominion.controllers.components.ChoiceBoxController;
import dominion.controllers.components.SettingButtonController;
import dominion.core.Game;
import dominion.core.GameManager;
import dominion.models.areas.LogBox;
import dominion.models.User;
import dominion.models.areas.GameScene;
import dominion.models.areas.MajorPurchaseArea;
import dominion.models.areas.MinorPurchaseArea;
import dominion.models.areas.WinnerDialog;
import dominion.models.cards.Card;
import dominion.models.cards.CardList;
import dominion.models.cards.actions.*;
import dominion.models.cards.curses.Curses;
import dominion.models.cards.treasures.Copper;
import dominion.models.cards.treasures.Gold;
import dominion.models.cards.treasures.Silver;
import dominion.models.cards.victories.Estate;
import dominion.models.cards.victories.Victory;
import dominion.models.areas.DisplayedCard;
import dominion.models.player.FieldCards;
import dominion.models.player.Player;
import dominion.models.player.PlayerStatus;
import dominion.params.GameSceneParams;
import dominion.params.SceneParams;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameController extends SceneController {
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


    // Functions
    public void initialize(Stage stage, SceneParams sceneParams) {
        // Unpack parameters
        GameSceneParams params = (GameSceneParams) sceneParams;
        List<User> users = params.users;
        User applicationUser = params.applicationUser;
        Connection connection = params.connection;
        int randomSeed = params.randomSeed;
        List<Card> basicCards = CardList.getCardsById(params.basicCardIds);
        List<Card> allEnabledCards = CardList.getCardsById(params.allEnabledCardIds);

        // Set up random seed
        GameManager.setRandomSeed(randomSeed);

        // Set up UIs
        GameScene.initialize(rootNode);
        WinnerDialog.initialize(winnerBox, winnerLabel);
        LogBox.initialize(scrollPane, messageBoxNode);

        ChoiceBoxController c = new ChoiceBoxController();
        GameScene.add(c);
        SettingButtonController s = new SettingButtonController();
        GameScene.add(s);

        // Set up players
        Player applicationPlayer = new Player(applicationUser);
        List<Player> players = new ArrayList<>();
        FieldCards fieldCards = new FieldCards();
        fieldCards.enableUi();
        int index = 0;
        for (User user : users) {
            Player player = new Player(user);
            PlayerStatus playerStatus = player.getPlayerStatus();
            if (user.getId() == applicationUser.getId()) {
                applicationPlayer = player;
                player.enableUi();
                playerStatusBox.getChildren().add(playerStatus.getController().getRootNode());
            } else {
                opponentsStatusBox.add(playerStatus.getController().getRootNode(), index, 0);
                index++;
            }

            List<Card> initialCards = new ArrayList<>();
            for (int i = 0; i < 7; i++) {
//                initialCards.add(new Copper());
                initialCards.add(new ThroneRoom());

            }
            for (int i = 0; i < 3; i++) {
//                initialCards.add(new Estate());
                initialCards.add(new Cellar());
            }

            player.setDeckCards(initialCards);
            player.setFieldCards(fieldCards);
            players.add(player);

        }

        // Set up purchase areas
        MajorPurchaseArea.initialize(majorKingdomCardsBoxNode);
        MinorPurchaseArea.initialize(minorKingdomCardsBoxNode);
        List<DisplayedCard> majorKingdomCards = new ArrayList<>();
        List<DisplayedCard> minorKingdomCards = new ArrayList<>();
        int id = 0;
        for (Card card : basicCards) {
            int numRemain = 10;
            if (card instanceof Victory) {
                numRemain = 4 * players.size();
            }
            if (card instanceof Copper) {
                numRemain = 60 - 7 * players.size();
            }
            if (card instanceof Silver) {
                numRemain = 40;
            }
            if (card instanceof Gold) {
                numRemain = 30;
            }
            if (card instanceof Curses) {
                numRemain = 10 * (players.size() - 1);
            }
            minorKingdomCards.add(new DisplayedCard(card, numRemain, applicationPlayer, id));
            id++;
        }
        MinorPurchaseArea.setDisplayedCards(minorKingdomCards);

        List<Card> enabledCards = randomSelectCards(allEnabledCards, 10);
        enabledCards.sort((a, b) -> a.getNumCost() - b.getNumCost());
        for (Card card : enabledCards) {
            int numRemain = 10;
            if (card instanceof Victory) {
                numRemain = 4 * players.size();
            }
            majorKingdomCards.add(new DisplayedCard(card, numRemain, applicationPlayer, id));
            id++;
        }
        Collections.rotate(majorKingdomCards, 5);
        MajorPurchaseArea.setDisplayedCards(majorKingdomCards);

        // Set up game manager
        GameManager.initialize(players, applicationPlayer, connection);

        // Run the game
        Game game = new Game();
        Thread gameThread = new Thread(game);
        for (Player player : players) {
            player.drawCards(5);
            player.setActionBarStatus("等待其他玩家的回合", "");
            player.reset();
        }
        gameThread.start();
    }

    public List<Card> randomSelectCards(List<Card> list, int numCards) {
        List<Card> cards = new ArrayList<>();
        for (int i = 0; i < numCards; i++) {
            int randomIndex = GameManager.getRandomInt(list.size());
            while (cards.contains(list.get(randomIndex))) {
                randomIndex = GameManager.getRandomInt(list.size());
            }
            cards.add(list.get(randomIndex));
        }
        return cards;
    }
}
