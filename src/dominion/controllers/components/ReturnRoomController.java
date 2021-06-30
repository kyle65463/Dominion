package dominion.controllers.components;

import dominion.connections.Connection;
import dominion.models.User;
import dominion.params.RoomSceneParams;
import dominion.params.SceneParams;
import dominion.utils.Navigator;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class ReturnRoomController {
    public static Stage stage;
    public static User applicationUser;
    public static List<User> users = new ArrayList<>();
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
        RoomSceneParams parameters = new RoomSceneParams(
                applicationUser, users, connection,
                basicCardIds, allEnabledCardIds
        );

        Navigator.to(stage, "resources/scenes/room.fxml", parameters);
    }

}
