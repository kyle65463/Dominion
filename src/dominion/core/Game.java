package dominion.core;

import dominion.models.areas.LogBox;
import dominion.models.cards.Card;
import dominion.models.cards.actions.Action;
import dominion.models.cards.treasures.Copper;
import dominion.models.cards.treasures.Gold;
import dominion.models.cards.treasures.Silver;
import dominion.models.cards.treasures.Treasure;
import dominion.models.events.game.EndBuyingPhaseEvent;
import dominion.models.events.game.EndPlayingActionsPhaseEvent;
import dominion.models.events.game.PlayCardEvent;
import dominion.models.player.Player;
import dominion.models.player.PlayerAction.DiscardAllFieldCards;
import dominion.models.player.PlayerAction.DiscardAllHandCards;
import dominion.models.player.PlayerAction.DrawCards;
import dominion.utils.UiThread;
import dominion.utils.VoicePlayer;

import java.util.List;

/*
    The game loop.
 */
public class Game implements Runnable {
    // Functions
    public void run() {
        do {
            // New turn
            Player currentPlayer = GameManager.getCurrentPlayer();
            playNewTurnSoundEffect(currentPlayer);
            logNewTurn(currentPlayer);

            if (currentPlayer.hasActionCards()) {
                // Playing action cards phase
                GameManager.setCurrentPhase(GameManager.Phase.PlayingActions);
                startPlayingActionsPhase(currentPlayer);
                GameManager.waitCondition(GameManager.isPlayingActionsPhaseEnd, GameManager.gameLock);
            }

            // Buying cards phase
            GameManager.setCurrentPhase(GameManager.Phase.BuyingCards);
            startBuyingCardsPhase(currentPlayer);
            GameManager.waitCondition(GameManager.isBuyingPhaseEnd, GameManager.gameLock);

            // Reset phase
            GameManager.setCurrentPhase(GameManager.Phase.Reset);
            reset(currentPlayer);

            // End turn
            logEndTurn();
            GameManager.endTurn();
        } while (!GameManager.checkGameOver());
    }

    private void logNewTurn(Player currentPlayer) {
        UiThread.run(() -> LogBox.logStartTurn(currentPlayer));
    }

    private void logEndTurn() {
        UiThread.run(LogBox::logEndTurn);
        try {
            Thread.sleep(100);
        }
        catch (Exception e){

        }
    }

    private void playNewTurnSoundEffect(Player currentPlayer) {
        Player applicationPlayer = GameManager.getApplicationPlayer();
        if (applicationPlayer.getId() == currentPlayer.getId()) {
            VoicePlayer.playEffect(0);
        }
    }

    private void startPlayingActionsPhase(Player currentPlayer){
        UiThread.run(() -> {
            // Set action bar
            currentPlayer.setActionBarStatus("你可以打出行動卡", "結束行動");
            currentPlayer.setActionBarRightButtonHandler((e) -> {
                GameManager.sendEvent(new EndPlayingActionsPhaseEvent(currentPlayer.getId()));
            });

            // Set card selected handler
            currentPlayer.setCardSelectedHandler((card) -> {
                if (card instanceof Action) {
                    GameManager.sendEvent(new PlayCardEvent(currentPlayer.getId(), card.getId()));
                }
            });
        });
    }

    private void startBuyingCardsPhase(Player currentPlayer){
        UiThread.run(() -> {
            UiThread.run(() -> {
                // Set action bar
                currentPlayer.setActionBarStatus("你可以購買卡片", "結束購買");
                currentPlayer.setActionBarRightButtonHandler((e) -> {
                    GameManager.sendEvent(new EndBuyingPhaseEvent(currentPlayer.getId()));
                });

                // Set auto playing treasure button
                List<Card> cards = GameManager.getCurrentPlayer().getHandCards();
                if (cards.stream().anyMatch(card -> card instanceof Treasure)) {
                    currentPlayer.enableLeftButton(true);
                    currentPlayer.setActionBarLeftButtonText("自動打出錢幣卡");
                    currentPlayer.setActionBarLeftButtonHandler((e) -> {
                        List<Card> handCards = GameManager.getCurrentPlayer().getHandCards();
                        for (Card card : handCards) {
                            if (card instanceof Copper || card instanceof Silver || card instanceof Gold) {
                                GameManager.sendEvent((new PlayCardEvent(currentPlayer.getId(), card.getId())));
                            }
                        }
                    });
                } else {
                    currentPlayer.enableLeftButton(false);
                }

                // Set card selected handler
                currentPlayer.setCardSelectedHandler((card) -> {
                    if (card instanceof Treasure) {
                        GameManager.sendEvent(new PlayCardEvent(currentPlayer.getId(), card.getId()));
                    }
                });
            });
        });
    }

    private void reset(Player currentPlayer){
        UiThread.run(() -> {
            currentPlayer.removeCardSelectedHandler();
            currentPlayer.setAfterPlayerActionHandler(() -> {
            });
            currentPlayer.enableLeftButton(false);
            currentPlayer.performAction(new DiscardAllHandCards());
            currentPlayer.performAction(new DiscardAllFieldCards());
            currentPlayer.performAction(new DrawCards(5));

            currentPlayer.setActionBarStatus("等待其他玩家的回合", "");
            currentPlayer.resetActionBarValues();
        });
    }
}
