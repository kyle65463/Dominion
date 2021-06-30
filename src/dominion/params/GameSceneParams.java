package dominion.params;

import dominion.connections.Connection;
import dominion.models.User;

import java.util.List;

public class GameSceneParams extends SceneParams {
    // Constructors
    public GameSceneParams(User applicationUser, List<User> users, Connection connection, int randomSeed,
                           List<Integer> basicCardIds, List<Integer> allEnabledCardIds) {
        this.applicationUser = applicationUser;
        this.users = users;
        this.connection = connection;
        this.randomSeed = randomSeed;
        this.basicCardIds = basicCardIds;
        this.allEnabledCardIds = allEnabledCardIds;
    }

    // Variables
    public final List<User> users;
    public final User applicationUser;
    public final Connection connection;
    public final int randomSeed;
    public final List<Integer> basicCardIds;
    public final List<Integer> allEnabledCardIds;
}
