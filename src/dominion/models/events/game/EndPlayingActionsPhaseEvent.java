package dominion.models.events.game;

import dominion.game.GameManager;
import dominion.models.game.Player;

public class EndPlayingActionsPhaseEvent extends GameEvent{
    // Constructor
    public EndPlayingActionsPhaseEvent(int playerId) {
        super(playerId);
    }

    // Functions
    @Override
    public void perform() {
        Player player = GameManager.getPlayerById(playerId);
        if(GameManager.getCurrentPhase() == GameManager.Phase.PlayingActions) {
            if (player.getId() == GameManager.getCurrentPlayer().getId()) {
                GameManager.endPlayingActionsPhase();
            }
        }
    }
}
