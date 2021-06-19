package dominion.game;

import dominion.models.events.game.EndPlayingActionsPhaseEvent;
import dominion.models.game.Player;
import javafx.application.Platform;


public class Game implements Runnable {
    // Constructor
    public Game() {

    }

    // Variables

    // Functions
    public void run() {

        while (true) {
            Player currentPlayer = GameManager.getCurrentPlayer();
            System.out.println("Player " + currentPlayer.getName() + "'s turn");

            // Playing action cards
            System.out.println("playing action cards phase");
            Platform.runLater(() -> {
                if (currentPlayer.hasActionCards()) {
                    currentPlayer.selectActionCards();
                }
                else{
                    GameManager.sendEvent(new EndPlayingActionsPhaseEvent(currentPlayer.getId()));
                }
            });
            waitForPlayingActionsPhasesEnd();

            // Buying cards
            System.out.println("buying cards phase");
            Platform.runLater(() -> {
                currentPlayer.selectTreasureCards();
            });
            waitForBuyingPhasesEnd();

            // Reset cards
            Platform.runLater(() -> {
                currentPlayer.discardHandCards();
                currentPlayer.discardFieldCards();
                currentPlayer.drawCards(5);
                currentPlayer.reset();
            });

            GameManager.endTurn();

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
