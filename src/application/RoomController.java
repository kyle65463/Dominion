package application;

import application.action.Action;
import application.connection.Connection;
import application.connection.Server;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;

import java.awt.event.KeyEvent;

public class RoomController {
    @FXML
    Label nameLabel;
    @FXML
    VBox messages;
    @FXML
    TextField textField;

    @FXML
    public void initialize() {
        nameLabel.setText("");
    }

    Connection connection;
    public void initialize(String name, Connection connection) {
        nameLabel.setText(name);
        this.connection = connection;
        connection.setOutputCallback((action) -> Platform.runLater(() -> handleAction(action)));
        textField.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.ENTER){
                onPressed();
            }
        });
    }

    public void handleAction(Action action) {
        addMessage(action.getUsername() + ": " + action.getContent());
    }

    public void addMessage(String message) {
        messages.getChildren().add(new Label(message));
    }

    public void onPressed() {
        if (!textField.getText().isEmpty()) {
            String message = textField.getText();
            connection.send(new Action(nameLabel.getText(), message));
            textField.setText("");
        }
    }
}
