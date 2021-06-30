package dominion.params;

import dominion.connections.Connection;
import dominion.models.User;

import java.util.List;

public class RoomSceneParams extends SceneParams {
    // Constructor
    public RoomSceneParams(User applicationUser, List<User> users, Connection connection){
        this.applicationUser = applicationUser;
        this.users = users;
        this.connection = connection;
    }

    // Variables
    public final List<User> users;
    public final User applicationUser;
    public final Connection connection;
}
