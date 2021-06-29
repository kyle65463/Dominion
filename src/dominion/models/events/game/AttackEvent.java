package dominion.models.events.game;

import dominion.game.GameManager;
import dominion.models.game.Player;


public class AttackEvent extends GameEvent{
    public AttackEvent(int playerId, int attackedId) {
        super(playerId);
        this.attackedId = attackedId;
    }

    private int attackedId;

    @Override
    public void perform() {
//        inform the attacked one he/she is under attack
        Player attacked = GameManager.getPlayerById(attackedId);
        GameManager.setCurrentPhase(GameManager.Phase.SelectingReactionCard);
        attacked.snapshotStatus();

        // Set new handlers
        attacked.setActionBarStatus("選擇應對牌:)", "完成");
        attacked.setCardSelectedHandler((card) -> {
            GameManager.sendEvent(new SelectReactionCardEvent(attackedId, card.getId()));
            attacked.recoverStatus();
            GameManager.sendEvent(new DoneSelectingReactionCardEvent(attackedId));
        });
        attacked.setActionBarButtonHandler((e) -> {
            GameManager.sendEvent(new DoneSelectingReactionCardEvent(attackedId));
            attacked.recoverStatus();
        });
    }
}
