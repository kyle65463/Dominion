package dominion.models.events.game;

import dominion.core.GameManager;

public class DoneSelectingReactionCardEvent extends GameEvent {
    // Constructor
    public DoneSelectingReactionCardEvent(int playerId) {
        super(playerId);
    }

    // Functions
    @Override
    public void perform() {
        if (GameManager.getCurrentPhase() == GameManager.Phase.SelectingReactionCard) {
            GameManager.returnLastPhase();
            GameManager.signalCondition(GameManager.isDoneReacting, GameManager.attackLock);
        }
    }
}
