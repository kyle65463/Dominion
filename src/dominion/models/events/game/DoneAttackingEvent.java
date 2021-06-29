package dominion.models.events.game;

import dominion.game.GameManager;

public class DoneAttackingEvent extends GameEvent {
    public DoneAttackingEvent(int playerId) {
        super(playerId);
    }

    // Functions
    @Override
    public void perform() {
        GameManager.signalCondition(GameManager.isDoneAttacking, GameManager.attackLock);
    }
}
