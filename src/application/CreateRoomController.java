package application;

import application.action.Action;
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

    private Server server = new Server();
    public void confirm(ActionEvent event) {
        server.setStatusCallback((m) -> Platform.runLater(() -> checkStatus(m, event)));
        Thread serverThread = new Thread(server);
        serverThread.start();
    }

    public void checkStatus(Action action, ActionEvent event) {
        String status = action.getContent();
        if(status == "Connected") {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("room.fxml"));
                Parent root = loader.load();
                RoomController roomController = loader.getController();
                roomController.initialize(nameField.getText(), server);
                Navigator.to(event, root);
            }
            catch (Exception e) {
                System.out.println(e);
            }
        }
    }
}
