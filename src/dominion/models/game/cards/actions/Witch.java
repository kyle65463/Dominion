package dominion.models.game.cards.actions;

import dominion.game.Game;
import dominion.game.GameManager;
import dominion.models.events.game.AttackEvent;
import dominion.models.game.DisplayedCard;
import dominion.models.game.Player;
import dominion.models.game.cards.Card;
import dominion.models.game.cards.curses.Curse;
import dominion.utils.CardStyles;
import dominion.utils.CardTypes;
import javafx.application.Platform;

import java.util.List;

public class Witch extends Card implements Action, Attack {
    public Witch() {
        name = "女巫";
        description = "";
        style = CardStyles.white;
        type = CardTypes.action;
        numCost = 5;
    }

    @Override
    public void perform(Player performer, boolean decreaseNumActions) {
        performer.drawCards(2);
        Thread thread = new Thread(()-> {
            Platform.exit();
            List<Player> players = GameManager.getPlayers();
            for (Player attacked : players) {
                if (attacked.getId() == performer.getId()) {
                    continue;
                } else {
                    if (attacked.hasReactionCards()) {
                        GameManager.sendEvent(new AttackEvent(performer.getId(), attacked.getId()));
                        GameManager.waitConditionLock(GameManager.getIsDoneReacting(), GameManager.attackLock);
                    }
                    if (attacked.getImmuneNextAttack()) {
                        attacked.setImmuneNextAttack(false);
                    } else {
                        Platform.runLater(()-> {
                            performAttack(performer, attacked);
                        });
                    }
                }
            }
        });
        thread.run();
        System.out.println("after run");
        if (decreaseNumActions) {
            performer.decreaseNumActions();
        }
        performer.checkActionCardsAndEndPlayingActionPhase();
    }

    @Override
    public void performAttack(Player performer, Player attacked) {
        Card curse = new Curse();
        DisplayedCard card = GameManager.getDisplayCardByCard(curse);
        if (card.getNumRemain() > 0) {
            attacked.receiveNewCard(curse);
            card.decreaseNumRemain();
        }
    }
}