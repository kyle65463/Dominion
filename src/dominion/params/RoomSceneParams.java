package dominion.params;

import dominion.connections.Connection;
import dominion.models.User;

import java.util.ArrayList;
import java.util.List;

public class RoomSceneParams extends SceneParams {
    // Constructor
    public RoomSceneParams(User applicationUser, List<User> users, Connection connection) {
        this(applicationUser, users, connection,  new ArrayList<>(), new ArrayList<>());
    }

    public RoomSceneParams(User applicationUser, List<User> users, Connection connection,
                           List<Integer> basicCardIds, List<Integer> allEnabledCardIds){
        this.applicationUser = applicationUser;
        this.users = users;
        this.connection = connection;
        this.basicCardIds = basicCardIds;
        this.allEnabledCardIds = allEnabledCardIds;
    }

    // Variables
    public final List<User> users;
    public final User applicationUser;
    public final Connection connection;
    public final List<Integer> basicCardIds;
    public final List<Integer> allEnabledCardIds;
}
