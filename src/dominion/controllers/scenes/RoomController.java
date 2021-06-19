package dominion.controllers.scenes;

import dominion.connections.Server;
import dominion.models.User;
import dominion.models.events.EventAction;
import dominion.models.events.connections.ConnectionAccepted;
import dominion.models.events.Message;
import dominion.connections.Connection;
import dominion.models.events.connections.StartGameEvent;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
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

public class RoomController {
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

    Connection connection;

    Stage stage;

    User applicationUser;
    List<User> users = new ArrayList<>();

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void startGame(ActionEvent event) {
        int randomSeed = new Random().nextInt(10000);
        connection.send(new StartGameEvent(randomSeed));
    }

    public void initialize(User user, List<User> users, Connection connection) {
        this.applicationUser = user;
        for (User u : users) {
            addUser(u);
        }
        nameLabel.setText(user.getName());
        this.connection = connection;
        connection.setActionCallback((action) -> Platform.runLater(() -> handleAction(action)));
        textField.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                onPressed();
            }
        });
        if (!(connection instanceof Server)) {
            startGameButton.setVisible(false);
            startGameButton.setDisable(true);
        }
    }

    public void handleAction(EventAction eventAction) {
        if (eventAction instanceof Message) {
            addMessage(((Message)eventAction).getUsername() + ": " + ((Message)eventAction).getContent());
        }
        if (eventAction instanceof ConnectionAccepted) {
            User acceptedUser = ((ConnectionAccepted) eventAction).getAcceptedUser();
            addUser(acceptedUser);
        }
        if(eventAction instanceof StartGameEvent) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/scenes/game.fxml"));
                Parent root = loader.load();
                GameController controller = loader.getController();
                controller.initialize(users, applicationUser, connection, ((StartGameEvent) eventAction).getRandomSeed());

                stage.setScene(new Scene(root));
                stage.show();
            }
            catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    public void addMessage(String message) {
        messages.getChildren().add(new Label(message));
    }

    public void onPressed() {
        if (!textField.getText().isEmpty()) {
            String message = textField.getText();
            connection.send(new Message(nameLabel.getText(), message));
            textField.setText("");
        }
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
}
