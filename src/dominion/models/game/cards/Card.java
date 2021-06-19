package dominion.models.game.cards;

import dominion.controllers.components.CardController;
import dominion.game.GameManager;
import dominion.models.game.HasUi;
import dominion.models.game.cards.treasures.Treasure;
import javafx.event.EventHandler;

import java.io.Serializable;
import java.util.Random;

public abstract class Card implements HasUi, Cloneable{
    // Constructor
    public Card() {
        disableUi();
        setId(GameManager.getRandomInt());
    }

    // Variables
    /* Properties */
    protected String name = "";
    protected String type = "";
    protected String description = "";
    protected String style = "";
    protected int numCost = 0;
    protected int numRemain = 0;
    protected int id = -1;

    /* UI */
    protected boolean isEnableUi = false;
    protected CardController uiController;

    // Function
    public String getStyle() {
        return style;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public int getNumCost() {
        return numCost;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void disableUi() {
        if(uiController != null) {
            System.out.println("Delete");
            uiController.deleteOnScene();
        }
        uiController = null;
        isEnableUi = false;
    }

    public boolean getEnableUi () {
        return isEnableUi;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public void enableUi() {
        if (uiController == null) {
            uiController = new CardController(this);
            uiController.setNumRemainLabel(numRemain);
            if (this instanceof Treasure) {
                uiController.setNumValueLabel(((Treasure) this).getNumValue());
            }
        }
        isEnableUi = true;
    }

    public void flipToBack() {
        if (isEnableUi) {
            uiController.flipToBack();
        }
    }

    public void disableOnPressed() {
        if (isEnableUi) {
            uiController.setOnPressed((e -> {
            }));
        }
    }

    public void setOnPressed(EventHandler eventHandler) {
        if (isEnableUi) {
            uiController.setOnPressed(eventHandler);
        }
    }

    public void flipToFront() {
        if (isEnableUi) {
            uiController.flipToFront();
        }
    }

    public void setNumRemain(int numRemain) {
        if (isEnableUi) {
            uiController.setNumRemainLabel(numRemain);
        }
    }

    public CardController getController() {
        return uiController;
    }
}
