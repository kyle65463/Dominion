package application;

import application.action.Action;
import application.connection.Client;
import application.util.Navigator;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class EnterRoomController {
    @FXML
    Button cancelButton;
    @FXML
    Button confirmButton;
    @FXML
    TextField nameField;

    Client client = new Client();

    @FXML
    public void initialize() {
        nameField.setText("client123");
    }

    public void cancel(ActionEvent event) {
        Navigator.to(event, "application/main.fxml");
    }

    public void confirm(ActionEvent event) {
        client.setStatusCallback((m) -> Platform.runLater(() -> checkStatus(m, event)));
        Thread serverThread = new Thread(client);
        serverThread.start();
    }

    public void checkStatus(Action action, ActionEvent event) {
        String status = action.getContent();
        if(status == "Connected") {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("room.fxml"));
                Parent root = loader.load();
                RoomController roomController = loader.getController();
                roomController.initialize(nameField.getText(), client);
                Navigator.to(event, root);
            }
            catch (Exception e) {
                System.out.println(e);
            }
        }
    }
}
