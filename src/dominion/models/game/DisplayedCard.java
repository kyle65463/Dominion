package dominion.models.game;

import dominion.controllers.components.DisplayedCardController;
import dominion.game.GameManager;
import dominion.models.events.game.BuyCardEvent;
import dominion.models.game.cards.Card;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import java.io.Serializable;

public class DisplayedCard implements HasUi {
    // Constructor
    public DisplayedCard(Card card, int numRemain, Player player, int id) {
        this.player = player;
        this.card = card;
        this.numRemain = numRemain;
        this.id = id;
        card.disableUi();

        // Impossible with no ui
        enableUi();
    }

    // Variables
    private int id;
    private Player player;
    private Card card;
    private int numRemain;
    private boolean isEnableUi;
    private DisplayedCardController uiController;

    // Functions
    public void enableUi() {
        this.uiController = new DisplayedCardController(card);
        setNumRemain(numRemain);
        uiController.setOnPressed((e) -> {
            if (e instanceof MouseEvent) {
                MouseButton button = ((MouseEvent) e).getButton();
                if (button == MouseButton.PRIMARY) {
                    GameManager.sendEvent(new BuyCardEvent(player.getId(), id));
                } else if (button == MouseButton.SECONDARY) {
                    System.out.println("Show description");
                }
            }
        });
        isEnableUi = true;
    }

    public int getId() {
        return id;
    }

    public Card getCard() {
        return card;
    }

    public int getNumRemain() {
        return numRemain;
    }

    public void setNumRemain(int numRemain) {
        this.numRemain = numRemain;
        uiController.setNumRemain(numRemain);
    }

    public void decreaseNumRemain() {
        numRemain--;
        setNumRemain(numRemain);
    }

    public DisplayedCardController getController() {
        return uiController;
    }
}
