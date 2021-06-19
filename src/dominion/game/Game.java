package dominion.game;

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
            if (currentPlayer.hasActionCards()) {
                Platform.runLater(() -> {
                    currentPlayer.selectActionCards();
                });
                waitForPlayingActionCardsPhasesEnd();
            }

            // Buying cards
            System.out.println("buying cards phase");
            Platform.runLater(() -> {
                currentPlayer.selectTreasureCards();
            });
            waitForPlayingActionCardsPhasesEnd();

            // Reset cards
            Platform.runLater(() -> {
                currentPlayer.endTurn();
            });

            GameManager.endTurn();

        }
    }

    private void waitForPlayingActionCardsPhasesEnd() {
        GameManager.lock.lock();
        try {
            GameManager.getIsPlayActionCardsPhaseEnd().await();
        } catch (Exception e) {

        } finally {
            GameManager.lock.unlock();
        }
    }

    private void waitForBuyingCardsPhasesEnd() {
        GameManager.lock.lock();
        try {
            GameManager.getIsBuyingCardsPhaseEnd().await();
        } catch (Exception e) {

        } finally {
            GameManager.lock.unlock();
        }
    }
}
