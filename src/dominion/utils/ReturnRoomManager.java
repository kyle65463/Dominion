package dominion.utils;

import dominion.connections.Connection;
import dominion.models.User;
import dominion.params.RoomSceneParams;
import dominion.params.SceneParams;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class ReturnRoomManager {
    public static Stage stage;
    public static User applicationUser;
    public static List<User> users = new ArrayList<>();
    public static Connection connection;
    public static List<Integer> basicCardIds;
    public static List<Integer> allEnabledCardIds;

    public static void getParams(Stage stage,SceneParams sceneParams){
        ReturnRoomManager.stage = stage;
        RoomSceneParams params = (RoomSceneParams) sceneParams;

        ReturnRoomManager.users = params.users;

        ReturnRoomManager.applicationUser = params.applicationUser;
        ReturnRoomManager.connection = params.connection;
        ReturnRoomManager.basicCardIds = params.basicCardIds;
        ReturnRoomManager.allEnabledCardIds = params.allEnabledCardIds;
    }

    public static void navigateToRoomScene() {
        RoomSceneParams parameters = new RoomSceneParams(
                applicationUser, users, connection,
                basicCardIds, allEnabledCardIds
        );

        Navigator.to(stage, "resources/scenes/room.fxml", parameters);
    }

}
