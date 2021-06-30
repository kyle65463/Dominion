package dominion.models.events.game;

import dominion.core.GameManager;
import dominion.models.player.Player;

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
                GameManager.signalCondition(GameManager.isPlayingActionsPhaseEnd, GameManager.gameLock);
            }
        }
    }
}
