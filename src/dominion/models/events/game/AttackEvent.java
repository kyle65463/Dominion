package dominion.models.events.game;

import dominion.core.GameManager;
import dominion.models.player.Player;
import dominion.models.cards.actions.Reaction;


public class AttackEvent extends GameEvent{
    // Constructor
    public AttackEvent(int playerId, int attackedId) {
        super(playerId);
        this.attackedId = attackedId;
    }

    // Variables
    private int attackedId;

    // Functions
    @Override
    public void perform() {
        // Inform the attacked one is under attack
        Player attacked = GameManager.getPlayerById(attackedId);
        GameManager.setCurrentPhase(GameManager.Phase.SelectingReactionCard);
        attacked.snapshotStatus();
        attacked.setActionBarStatus("展示應對牌", "不展示");
        attacked.setCardSelectedHandler((card) -> {
            if(card instanceof Reaction) {
                GameManager.sendEvent(new SelectReactionCardEvent(attackedId, card.getId()));
                attacked.recoverStatus();
                GameManager.sendEvent(new DoneSelectingReactionCardEvent(attackedId));
            }
        });
        attacked.setActionBarRightButtonHandler((e) -> {
            GameManager.sendEvent(new DoneSelectingReactionCardEvent(attackedId));
            attacked.recoverStatus();
        });
    }
}
