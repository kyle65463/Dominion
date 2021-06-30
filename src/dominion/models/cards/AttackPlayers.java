package dominion.models.cards;

import dominion.core.GameManager;
import dominion.models.events.game.AttackEvent;
import dominion.models.player.Player;
import dominion.models.cards.actions.Attack;
import javafx.application.Platform;

import java.util.Collections;
import java.util.List;

public class AttackPlayers implements Runnable {
    // Constructor
    public AttackPlayers(Player performer, Attack attackCard, Boolean decreaseNumActions) {
        this.performer = performer;
        this.attackCard = attackCard;
        this.decreaseNumActions = decreaseNumActions;
    }

    // Variables
    private Boolean decreaseNumActions;
    private Player performer;
    private Attack attackCard;

    // Functions
    @Override
    public void run() {
        Platform.runLater(() -> {
            performer.snapshotStatus();
            performer.setActionBarStatus("等待其他玩家應對", "");
            performer.setCardSelectedHandler((card) -> {
            });
            performer.setActionBarRightButtonHandler((e) -> {
            });
        });

        List<Player> players = GameManager.getPlayers();
        Collections.rotate(players, players.indexOf(performer));
        for (Player attacked : players) {
            if (attacked.getId() != performer.getId()){
                if (attacked.hasReactionCards()) {
                    GameManager.sendEvent(new AttackEvent(performer.getId(), attacked.getId()));
                    GameManager.waitCondition(GameManager.isDoneReacting, GameManager.attackLock);
                }
                if (attacked.getImmuneNextAttack()) {
                    attacked.setImmuneNextAttack(false);
                } else {
                    Platform.runLater(() -> {
                        attackCard.performAttack(performer, attacked);
                    });
                    GameManager.waitCondition(GameManager.isDoneAttacking, GameManager.attackLock);
                }
            }
        }

        Platform.runLater(() -> {
            performer.recoverStatus();
            attackCard.performAfterAttack(performer);
            if (decreaseNumActions) {
                performer.decreaseNumActions();
            }
            performer.checkActionCardsAndEndPlayingActionPhase();
        });
    }
}
