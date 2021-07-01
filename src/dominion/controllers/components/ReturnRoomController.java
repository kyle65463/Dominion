package dominion.controllers.components;

import dominion.connections.Connection;
import dominion.connections.Server;
import dominion.core.GameManager;
import dominion.models.User;
import dominion.models.cards.CardList;
import dominion.params.RoomSceneParams;
import dominion.params.SceneParams;
import dominion.utils.Navigator;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class ReturnRoomController {
    public static Stage stage;
    public static User applicationUser;
    public static List<User> users;
    public static Connection connection;
    public static List<Integer> basicCardIds;
    public static List<Integer> allEnabledCardIds;

    public static void getParams(Stage stage,SceneParams sceneParams){
        ReturnRoomController.stage = stage;
        RoomSceneParams params = (RoomSceneParams) sceneParams;
        ReturnRoomController.users = params.users;
        ReturnRoomController.applicationUser = params.applicationUser;
        ReturnRoomController.connection = params.connection;
        ReturnRoomController.basicCardIds = params.basicCardIds;
        ReturnRoomController.allEnabledCardIds = params.allEnabledCardIds;
    }

    public static void navigateToRoomScene() {
        List<User> existUsers = new ArrayList<>();
        for(int i = 0;i < UsrExistController.users.size();i++){
            if(UsrExistController.users.get(i).isExist())
                existUsers.add(UsrExistController.users.get(i));
        }
        users = existUsers;
        RoomSceneParams parameters = new RoomSceneParams(
                applicationUser, users, connection,
                basicCardIds, allEnabledCardIds
        );

        Navigator.to(stage, "resources/scenes/room.fxml", parameters);
    }

}
