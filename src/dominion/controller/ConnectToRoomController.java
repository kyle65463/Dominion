package dominion.controller;

import dominion.model.User;
import dominion.model.action.Action;
import dominion.model.action.ConnectionAccepted;
import dominion.connection.Connection;
import dominion.util.Navigator;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.util.List;

public abstract class ConnectToRoomController {
    // FXML Components
    @FXML
    protected Button cancelButton;
    @FXML
    protected Button confirmButton;
    @FXML
    protected TextField nameField;

    // Variables
    protected Connection connection;

    // Functions
    public abstract void initialize();

    public void setName(String name) {
        nameField.setText(name);
    }

    public void cancel(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("resources/view/main.fxml"));
            Parent root = loader.load();
            MainController mainController = loader.getController();
            mainController.setName(nameField.getText());
            Navigator.to(event, root);
        }
        catch (Exception e) {

        }
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
                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("resources/view/room.fxml"));
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
