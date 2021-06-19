package dominion.game;

import dominion.models.game.*;

import java.util.List;

public class GameManager {
    // Constructor
    public GameManager(Player player, List<Player> players) {
        this.player = player;
        this.players = players;
    }

    // Variables
    private Player currentPlayer;       // The player playing actions
    private Player player;              // The player of this application
    private List<Player> players;       // All players
}
