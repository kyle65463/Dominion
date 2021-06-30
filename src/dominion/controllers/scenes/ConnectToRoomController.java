package dominion.controllers.scenes;

import dominion.models.User;
import dominion.models.events.Event;
import dominion.models.events.connections.ConnectionAccepted;
import dominion.connections.Connection;
import dominion.params.ConnectToRoomSceneParams;
import dominion.params.MainSceneParams;
import dominion.params.RoomSceneParams;
import dominion.params.SceneParams;
import dominion.utils.Navigator;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.List;

public abstract class ConnectToRoomController extends SceneController{
    // FXML Components
    @FXML
    protected Button cancelButton;
    @FXML
    protected Button confirmButton;
    @FXML
    protected TextField nameField;
    @FXML
    protected TextField portField;
    @FXML
    protected TextField ipField;

    // Variables
    protected Stage stage;
    protected Connection connection;
    protected String defaultName;
    protected String defaultPort;
    protected String defaultIp;

    // Functions
    public void initialize(Stage stage, SceneParams sceneParams) {
        ConnectToRoomSceneParams params = (ConnectToRoomSceneParams) sceneParams;
        this.stage = stage;
        this.defaultName = params.defaultName;
        this.defaultPort = params.defaultPort;
        this.defaultIp = params.defaultIp;
    }

    public void cancel(ActionEvent e) {
       navigateToMainScene();
    }

    public void confirm(ActionEvent e) {
        if (ipField != null) {
            connection.setIp(ipField.getText());
        }
        connection.setName(nameField.getText());
        connection.setEventHandler((event) -> Platform.runLater(() -> handleEvent(event)));
        connection.setPort(portField.getText());
        Thread connectionThread = new Thread(connection);
        connectionThread.start();
    }

    public void handleEvent(Event event) {
        if(event instanceof ConnectionAccepted accepted) {
            User user = accepted.getAcceptedUser();
            List<User> users = accepted.getUsers();
            navigateToRoomScene(user, users);
        }
    }

    /* Navigation */
    public void navigateToRoomScene(User applicationUser, List<User> users) {
        RoomSceneParams parameters = new RoomSceneParams(applicationUser, users, connection);
        Navigator.to(stage, "resources/scenes/room.fxml", parameters);
    }

    public void navigateToMainScene() {
        MainSceneParams parameters = new MainSceneParams(defaultName, defaultIp, defaultPort);
        Navigator.to(stage, "resources/scenes/main.fxml", parameters);
    }
}