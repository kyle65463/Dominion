package dominion.game;

import dominion.models.events.game.EndBuyingPhaseEvent;
import dominion.models.events.game.EndPlayingActionsPhaseEvent;
import dominion.models.events.game.PlayCardEvent;
import dominion.models.game.Player;
import dominion.models.game.cards.actions.Action;
import dominion.models.game.cards.treasures.Treasure;
import javafx.application.Platform;


public class Game implements Runnable {
    // Functions
    public void run() {
        while (true) {
            // New turn
            Player currentPlayer = GameManager.getCurrentPlayer();
            System.out.println("Player " + currentPlayer.getName() + "'s turn");

            // Playing action cards
            Platform.runLater(() -> {
                if (currentPlayer.hasActionCards()) {
                    System.out.println("playing action cards phase");
                    currentPlayer.setActionBarStatus("你可以打出行動卡", "結束行動");
                    currentPlayer.setActionBarButtonHandler((e) -> {
                        GameManager.sendEvent(new EndPlayingActionsPhaseEvent(currentPlayer.getId()));
                    });
                    currentPlayer.setCardSelectedHandler((card)->{
                        if(card instanceof Action) {
                            GameManager.sendEvent(new PlayCardEvent(currentPlayer.getId(), card.getId()));
                        }
                    });
                }
                else{
                    GameManager.sendEvent(new EndPlayingActionsPhaseEvent(currentPlayer.getId()));
                }
            });
            waitForPlayingActionsPhasesEnd();
            currentPlayer.removeCardSelectedHandler();

            // Buying cards
            Platform.runLater(() -> {
                System.out.println("buying cards phase");
                currentPlayer.setActionBarStatus("你可以購買卡片", "結束購買");
                currentPlayer.setActionBarButtonHandler((e) -> {
                    GameManager.sendEvent(new EndBuyingPhaseEvent(currentPlayer.getId()));
                });
                currentPlayer.setCardSelectedHandler((card)->{
                    if(card instanceof Treasure) {
                        GameManager.sendEvent(new PlayCardEvent(currentPlayer.getId(), card.getId()));
                    }
                });
            });
            waitForBuyingPhasesEnd();
            currentPlayer.removeCardSelectedHandler();

            // Reset
            Platform.runLater(() -> {
                currentPlayer.discardAllHandCards();
                currentPlayer.discardAllFieldCards();
                currentPlayer.drawCards(5);
                currentPlayer.setActionBarStatus("等待其他玩家的回合", "");
                currentPlayer.reset();
            });

            GameManager.endTurn();
            GameManager.checkGameOver();
        }
    }

    private void waitForPlayingActionsPhasesEnd() {
        GameManager.lock.lock();
        try {
            GameManager.getIsPlayActionsPhaseEnd().await();
        } catch (Exception e) {

        } finally {
            GameManager.lock.unlock();
        }
    }

    private void waitForBuyingPhasesEnd() {
        GameManager.lock.lock();
        try {
            GameManager.getIsBuyingPhaseEnd().await();
        } catch (Exception e) {

        } finally {
            GameManager.lock.unlock();
        }
    }
}
