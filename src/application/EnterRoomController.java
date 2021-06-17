package application;

import application.action.Action;
import application.action.ConnectionAccepted;
import application.connection.Client;
import application.connection.Connection;
import application.util.Navigator;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.util.List;

public class EnterRoomController {
    @FXML
    Button cancelButton;
    @FXML
    Button confirmButton;
    @FXML
    TextField nameField;

    private Connection connection = new Client();
    private String name = "client";

    @FXML
    public void initialize() {
        nameField.setText(name);
    }

    public void setName(String name) {
        this.name = name;
        nameField.setText(name);
    }

    public void cancel(ActionEvent event) {
        Navigator.to(event, "application/main.fxml");
    }
    public void confirm(ActionEvent event) {
        connection.setName(nameField.getText());
        connection.setActionCallback((m) -> Platform.runLater(() -> checkStatus(m, event)));
        Thread connectionThread = new Thread(connection);
        connectionThread.start();
    }
    public void checkStatus(Action action, ActionEvent event) {
        if(action instanceof ConnectionAccepted) {
            ConnectionAccepted accepted = ((ConnectionAccepted)action);
            User user = accepted.getAcceptedUser();
            List<User> users = accepted.getUsers();
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("room.fxml"));
                Parent root = loader.load();
                RoomController roomController = loader.getController();
                roomController.initialize(user, users, connection);
                Navigator.to(event, root);
            }
            catch (Exception e) {
                System.out.println(e);
            }
        }
    }
}
