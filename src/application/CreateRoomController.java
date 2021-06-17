package application;

import application.action.Action;
import application.connection.Connection;
import application.connection.Server;
import application.util.Navigator;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class CreateRoomController {
    @FXML
    Button cancelButton;
    @FXML
    Button confirmButton;
    @FXML
    TextField nameField;
    @FXML
    TextField ipField;

    @FXML
    public void initialize() {
        ipField.setText("localhost");
        nameField.setText("host");
    }

    public void cancel(ActionEvent event) {
        Navigator.to(event, "application/main.fxml");
    }

    private Connection connection = new Server();
    public void confirm(ActionEvent event) {
        connection.setActionCallback((m) -> Platform.runLater(() -> checkStatus(m, event)));
        Thread connectionThread = new Thread(connection);
        connectionThread.start();
    }

    public void checkStatus(Action action, ActionEvent event) {
        String status = action.getContent();
        if(status == "connected") {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("room.fxml"));
                Parent root = loader.load();
                RoomController roomController = loader.getController();
                roomController.initialize(nameField.getText(), connection);
                Navigator.to(event, root);
            }
            catch (Exception e) {
                System.out.println(e);
            }
        }
    }
}
