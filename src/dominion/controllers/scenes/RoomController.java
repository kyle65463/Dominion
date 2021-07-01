package dominion.controllers.scenes;

import dominion.connections.Host;
import dominion.utils.ReturnRoomManager;
import dominion.models.User;
import dominion.models.cards.CardFactory;
import dominion.models.events.Event;
import dominion.models.events.connections.ConnectionAccepted;
import dominion.models.events.interactive.Message;
import dominion.connections.Connection;
import dominion.models.events.connections.StartGameEvent;
import dominion.models.expansions.Basic;
import dominion.models.expansions.Dominion;
import dominion.params.GameSceneParams;
import dominion.params.GameSettingsSceneParams;
import dominion.params.RoomSceneParams;
import dominion.params.SceneParams;
import dominion.utils.Navigator;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RoomController extends SceneController {
    @FXML
    Label nameLabel;
    @FXML
    VBox messages;
    @FXML
    TextField textField;
    @FXML
    VBox playerListBox;
    @FXML
    Button startGameButton;
    @FXML
    Button settingsButton;

    // Variables
    private Stage stage;
    private User applicationUser;
    private List<User> users = new ArrayList<>();
    private Connection connection;
    private List<Integer> basicCardIds;
    private List<Integer> allEnabledCardIds;

    // Functions
    public void initialize(Stage stage, SceneParams sceneParams) {
        // Unpack parameters
        ReturnRoomManager.getParams(stage,sceneParams);
        this.stage = stage;
        RoomSceneParams params = (RoomSceneParams) sceneParams;
        List<User> users = params.users;
        User applicationUser = params.applicationUser;
        Connection connection = params.connection;
        this.basicCardIds = params.basicCardIds;
        this.allEnabledCardIds = params.allEnabledCardIds;
        if(basicCardIds.isEmpty()) {
            basicCardIds = CardFactory.getCardIds(CardFactory.getCardsOfExpansion(Basic.class));
        }
        if(allEnabledCardIds.isEmpty()) {
            allEnabledCardIds = CardFactory.getCardIds(CardFactory.getCardsOfExpansion(Dominion.class));
        }

        // Set up UIs
        this.applicationUser = applicationUser;
        for (User u : users) {
            addUser(u);
        }
        nameLabel.setText(applicationUser.getName());
        this.connection = connection;
        connection.setEventHandler((action) -> Platform.runLater(() -> handleEvent(action)));
        textField.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                sendMessage();
            }
        });
        if (!(connection instanceof Host)) {
            startGameButton.setVisible(false);
            startGameButton.setDisable(true);
            settingsButton.setVisible(false);
            settingsButton.setDisable(true);
        }
        stage.setOnCloseRequest(e->{Platform.exit();System.out.println("why are we still here");});
    }

    public void receiveMessage(String message) {
        messages.getChildren().add(new Label(message));
    }

    public void sendMessage() {
        if (!textField.getText().isEmpty()) {
            String message = textField.getText();
            connection.send(new Message(nameLabel.getText(), message));
            textField.setText("");
        }
    }

    public void sendStartGame(ActionEvent event) {
        int randomSeed = new Random().nextInt(10000);
        connection.send(new StartGameEvent(randomSeed, basicCardIds, allEnabledCardIds));
    }

    public void addUser(User newUser) {
        users.add(newUser);
        Label label = new Label(newUser.getName());
        label.setPadding(new Insets(10, 5, 0, 0));
        label.setFont(new Font(17));
        label.setMinWidth(140);
        label.setMaxWidth(140);
        HBox box1 = (HBox) playerListBox.getChildren().get(0);
        HBox box2 = (HBox) playerListBox.getChildren().get(1);
        if (box1.getChildren().size() < 2) {
            box1.getChildren().add(label);
        } else {
            box2.getChildren().add(label);
        }
    }

    public void handleEvent(Event event) {
        if (event instanceof Message) {
            receiveMessage(((Message) event).getUsername() + ": " + ((Message) event).getContent());
        }
        if (event instanceof ConnectionAccepted) {
            User acceptedUser = ((ConnectionAccepted) event).getAcceptedUser();
            addUser(acceptedUser);
        }
        if (event instanceof StartGameEvent) {
            StartGameEvent startGameEvent = (StartGameEvent) event;
            navigateToGameScene(startGameEvent.randomSeed, startGameEvent.basicCardIds, startGameEvent.allEnabledCardIds);
        }
    }

    public void navigateToGameSettingsScene(ActionEvent event) {
        GameSettingsSceneParams params = new GameSettingsSceneParams(
                applicationUser, users, connection,
                basicCardIds, allEnabledCardIds
        );
        Navigator.to(stage, "resources/scenes/game_settings.fxml", params);
    }

    public void navigateToGameScene(int randomSeed, List<Integer> basicCardIds, List<Integer> allEnabledCardIds) {
        GameSceneParams params = new GameSceneParams(
                applicationUser, users, connection, randomSeed,
                basicCardIds, allEnabledCardIds
        );
        Navigator.to(stage, "resources/scenes/game.fxml", params);
    }
}