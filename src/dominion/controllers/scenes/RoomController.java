package dominion.controllers.scenes;

import dominion.connections.Server;
import dominion.models.User;
import dominion.models.events.Event;
import dominion.models.events.connections.ConnectionAccepted;
import dominion.models.events.Message;
import dominion.connections.Connection;
import dominion.models.events.connections.StartGameEvent;
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

public class RoomController extends SceneController{
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

    // Functions
    public void initialize(Stage stage, SceneParams sceneParams) {
        // Unpack parameters
        this.stage = stage;
        RoomSceneParams parameters = (RoomSceneParams) sceneParams;
        List<User> users = parameters.users;
        User applicationUser =  parameters.applicationUser;
        Connection connection = parameters.connection;

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
        if (!(connection instanceof Server)) {
            startGameButton.setVisible(false);
            startGameButton.setDisable(true);
        }
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
        connection.send(new StartGameEvent(randomSeed));
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
            navigateToGameScene(((StartGameEvent) event).getRandomSeed());
        }
    }

    public void navigateToGameSettingsScene(ActionEvent event) {
        GameSettingsSceneParams parameters = new GameSettingsSceneParams();
        Navigator.to(stage, "resources/scenes/game_settings.fxml", parameters);
    }

    public void navigateToGameScene(int randomSeed) {
        GameSceneParams parameters = new GameSceneParams(
                applicationUser, users, connection, randomSeed);
        Navigator.to(stage, "resources/scenes/game.fxml", parameters);
    }
}