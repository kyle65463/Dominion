package dominion.controllers.scenes;

import dominion.models.User;
import dominion.models.events.EventAction;
import dominion.models.events.connections.ConnectionAccepted;
import dominion.connections.Connection;
import dominion.utils.Navigator;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.List;

public abstract class ConnectToRoomController {
    // FXML Components
    @FXML
    protected Button cancelButton;
    @FXML
    protected Button confirmButton;
    @FXML
    protected TextField nameField;
    @FXML
    protected TextField ipField;

    // Variables
    protected Connection connection;

    // Functions
    public abstract void initialize();

    public void setName(String name) {
        nameField.setText(name);
    }

    public void cancel(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("resources/scenes/main.fxml"));
            Parent root = loader.load();
            MainController mainController = loader.getController();
            mainController.setName(nameField.getText());
            Navigator.to(event, root);
        }
        catch (Exception e) {

        }
    }

    public void confirm(ActionEvent event) {
        if (ipField != null) {
            connection.setIp(ipField.getText());
        }
        connection.setName(nameField.getText());
        connection.setActionCallback((m) -> Platform.runLater(() -> checkStatus(m, event)));
        Thread connectionThread = new Thread(connection);
        connectionThread.start();
    }

    public void checkStatus(EventAction eventAction, ActionEvent event) {
        if(eventAction instanceof ConnectionAccepted) {
            ConnectionAccepted accepted = ((ConnectionAccepted) eventAction);
            User user = accepted.getAcceptedUser();
            List<User> users = accepted.getUsers();
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("resources/scenes/room.fxml"));
                Parent root = loader.load();
                RoomController roomController = loader.getController();
                roomController.initialize(user, users, connection);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(new Scene(root));
                roomController.setStage(stage);

                stage.show();

            }
            catch (Exception e) {
                System.out.println(e);
            }
        }
    }
}