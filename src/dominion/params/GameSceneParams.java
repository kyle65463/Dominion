package dominion.params;

import dominion.connections.Connection;
import dominion.models.User;

import java.util.List;

public class GameSceneParams extends SceneParams {
    // Constructor
    public GameSceneParams(User applicationUser, List<User> users, Connection connection, int randomSeed){
        this.applicationUser = applicationUser;
        this.users = users;
        this.connection = connection;
        this.randomSeed = randomSeed;
    }

    // Variables
    public final List<User> users;
    public final User applicationUser;
    public final Connection connection;
    public final int randomSeed;
}
