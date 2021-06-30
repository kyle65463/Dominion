package dominion.core;

import dominion.models.areas.LogBox;
import dominion.models.events.game.EndBuyingPhaseEvent;
import dominion.models.events.game.EndPlayingActionsPhaseEvent;
import dominion.models.events.game.PlayCardEvent;
import dominion.models.player.Player;
import dominion.models.cards.Card;
import dominion.models.cards.actions.Action;
import dominion.models.cards.treasures.Treasure;
import dominion.utils.VoicePlayer;
import javafx.application.Platform;

import java.util.List;

public class Game implements Runnable {
    // Functions
    public void run() {
        while (true) {
            // New turn
            Player currentPlayer = GameManager.getCurrentPlayer();
            Player applicationPlayer = GameManager.getApplicationPlayer();
            if(applicationPlayer.getId() == currentPlayer.getId()){
                VoicePlayer.playEffect(0);
            }
            Platform.runLater(() -> {
                LogBox.logStartTurn(currentPlayer);
            });
            System.out.println("Player " + currentPlayer.getName() + "'s turn");

            // Playing action cards
            GameManager.setCurrentPhase(GameManager.Phase.PlayingActions);
            Platform.runLater(() -> {
                if (currentPlayer.hasActionCards()) {
                    System.out.println("playing action cards phase");
                    currentPlayer.setActionBarStatus("你可以打出行動卡", "結束行動");
                    currentPlayer.setActionBarRightButtonHandler((e) -> {
                        GameManager.sendEvent(new EndPlayingActionsPhaseEvent(currentPlayer.getId()));
                    });
                    currentPlayer.setCardSelectedHandler((card) -> {
                        if (card instanceof Action) {
                            GameManager.sendEvent(new PlayCardEvent(currentPlayer.getId(), card.getId()));
                        }
                    });
                } else {
                    GameManager.sendEvent(new EndPlayingActionsPhaseEvent(currentPlayer.getId()));
                }
            });
            GameManager.waitCondition(GameManager.isPlayingActionsPhaseEnd, GameManager.gameLock);
            currentPlayer.removeCardSelectedHandler();

            // Buying cards phase
            GameManager.setCurrentPhase(GameManager.Phase.BuyingCards);
            Platform.runLater(() -> {
                System.out.println("buying cards phase");
                currentPlayer.setActionBarStatus("你可以購買卡片", "結束購買");
                currentPlayer.setActionBarRightButtonHandler((e) -> {
                    GameManager.sendEvent(new EndBuyingPhaseEvent(currentPlayer.getId()));
                });
                List<Card> cards = GameManager.getCurrentPlayer().getHandCards();
                if (cards.stream().anyMatch(card -> card instanceof Treasure)) {
                    currentPlayer.setActionBarLeftButtonText("自動打出錢幣");
                    currentPlayer.enableLeftButton(true);
                    currentPlayer.setActionBarLeftButtonHandler((e) -> {
                        for (Card card : cards) {
                            if (card instanceof Treasure) {
                                GameManager.sendEvent((new PlayCardEvent(currentPlayer.getId(), card.getId())));
                            }
                        }
                    });
                } else {
                    currentPlayer.enableLeftButton(false);
                }
                currentPlayer.setCardSelectedHandler((card) -> {
                    if (card instanceof Treasure) {
                        GameManager.sendEvent(new PlayCardEvent(currentPlayer.getId(), card.getId()));
                    }
                });
            });
            GameManager.waitCondition(GameManager.isBuyingPhaseEnd, GameManager.gameLock);
            currentPlayer.removeCardSelectedHandler();
            currentPlayer.enableLeftButton(false);

            // Reset phase
            GameManager.setCurrentPhase(GameManager.Phase.Reset);
            Platform.runLater(() -> {
                currentPlayer.discardAllHandCards();
                currentPlayer.discardAllFieldCards();
                currentPlayer.drawCards(5);
                currentPlayer.setActionBarStatus("等待其他玩家的回合", "");
                currentPlayer.reset();
            });

            Platform.runLater(() -> {
                LogBox.logEndTurn();
            });
            GameManager.endTurn();
            if(GameManager.checkGameOver())
                break;
        }
    }
}
