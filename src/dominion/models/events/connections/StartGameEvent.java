package dominion.models.events.connections;

public class StartGameEvent extends ConnectionEvent{
    // Constructor
    public StartGameEvent(int randomSeed) {
        this.randomSeed = randomSeed;
    }

    // Variables
    private int randomSeed;

    // Functions
    public int getRandomSeed() {
        return randomSeed;
    }
}
