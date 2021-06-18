package dominion.controllers.scenes;

import dominion.connections.Server;
import dominion.models.User;
import dominion.models.action.Action;
import dominion.models.action.ConnectionAccepted;
import dominion.models.action.Message;
import dominion.connections.Connection;
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

import java.util.List;

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

    public void startGame(ActionEvent event) {
        Navigator.to(event, "resources/scenes/game.fxml");
    }

    public void initialize(User user, List<User> users, Connection connection) {
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

    public void handleAction(Action action) {
        if (action instanceof Message) {
            addMessage(action.getUsername() + ": " + action.getContent());
        }
        if (action instanceof ConnectionAccepted) {
            User acceptedUser = ((ConnectionAccepted) action).getAcceptedUser();
            addUser(acceptedUser);
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
