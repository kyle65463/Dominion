package dominion.models.events.game;

import dominion.core.GameManager;
import dominion.models.areas.LogBox;
import dominion.models.areas.DisplayedCard;
import dominion.models.player.Player;
import dominion.models.cards.Card;
import dominion.utils.VoicePlayer;

public class BuyCardEvent extends GameEvent{
    // Constructor
    public BuyCardEvent(int playerId, int displayedCardId) {
        super(playerId);
        this.displayedCardId = displayedCardId;
    }

    // Variables
    private int displayedCardId;

    // Functions
    @Override
    public void perform() {
        Player player = GameManager.getPlayerById(playerId);
        DisplayedCard displayedCard = GameManager.getDisplayedCardById(displayedCardId);
        if(GameManager.getCurrentPhase() == GameManager.Phase.BuyingCards) {
            if (player.getId() == GameManager.getCurrentPlayer().getId()) {
                Card card = displayedCard.getCard();
                if (player.getNumCoins() >= card.getNumCost() && player.getNumPurchases() > 0 && displayedCard.getNumRemain() > 0) {
                    try {
                        Card newCard = displayedCard.instantiateNewCard();
                        player.receiveNewCard(newCard);
                        player.decreaseNumPurchases();
                        player.decreaseNumCoins(card.getNumCost());
                        displayedCard.decreaseNumRemain();

                        LogBox.logBuyCard(player, newCard);
                        if(GameManager.getApplicationPlayer().getId() == GameManager.getCurrentPlayer().getId())
                            VoicePlayer.playEffect(2);

                    } catch (Exception e) {
                        System.out.println("error");
                    }
                }
            }
        }
    }
}
