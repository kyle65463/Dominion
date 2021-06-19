package dominion.models.events.game;

import dominion.game.GameManager;
import dominion.models.game.DisplayedCard;
import dominion.models.game.Player;
import dominion.models.game.cards.Card;

public class BuyCardEvent extends GameEvent{
    // Constructor
    public BuyCardEvent(Player player, DisplayedCard displayedCard) {
        super(player);
        this.displayedCard = displayedCard;
    }

    // Variables
    private DisplayedCard displayedCard;

    // Functions
    @Override
    public void perform() {
        if(player.getId() == GameManager.getCurrentPlayer().getId()){
            Card card = displayedCard.getCard();
            if(player.getNumCoins() >= card.getNumCost() && player.getNumPurchase() > 0 && displayedCard.getNumRemain() > 0) {
                try {
                    Card newCard = (Card) card.clone();
                    newCard.setNumRemain(1);
                    player.receiveNewCard(newCard);
                    player.decreaseNumPurchases();
                    player.decreaseNumCoins(card.getNumCost());
                    displayedCard.decreaseNumRemain();
                }
                catch (Exception e) {

                }
            }
        }
    }
}
