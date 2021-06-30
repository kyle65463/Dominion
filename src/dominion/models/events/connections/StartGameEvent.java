package dominion.models.events.connections;

import java.util.List;

public class StartGameEvent extends ConnectionEvent{
    // Constructor
    public StartGameEvent(int randomSeed, List<Integer> basicCardIds, List<Integer> allEnabledCardIds) {
        this.randomSeed = randomSeed;
        this.basicCardIds = basicCardIds;
        this.allEnabledCardIds = allEnabledCardIds;
    }

    // Variables
    public final int randomSeed;
    public final List<Integer> basicCardIds;
    public final List<Integer> allEnabledCardIds;
}
