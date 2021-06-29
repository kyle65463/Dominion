package dominion.models.game.cards;

import dominion.controllers.components.CardController;
import dominion.controllers.components.FullCardController;
import dominion.game.GameManager;
import dominion.models.game.GameScene;
import dominion.models.game.HasUi;
import dominion.models.game.cards.treasures.Treasure;
import javafx.event.EventHandler;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public abstract class Card implements HasUi, Cloneable {
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
    protected FullCardController descriptionController;

    private boolean someCondition = false;

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

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void disableUi() {
        if (uiController != null) {
            uiController.deleteOnScene();
        }
        uiController = null;
        isEnableUi = false;
    }

    public boolean getEnableUi() {
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
        if (descriptionController == null) {
            descriptionController = new FullCardController(this);
        }
        isEnableUi = true;
    }

    public void flipToBack() {
        if (isEnableUi) {
            uiController.flipToBack();
        }
    }

    public void setHighlight() {
        if (isEnableUi) {
            uiController.setHighlight();
        }
    }

    public void removeHighlight() {
        if (isEnableUi) {
            uiController.removeHighlight();
        }
    }

    public void setOnPressed(EventHandler eventHandler) {
        if (isEnableUi) {
            uiController.setOnPressed((e) -> {
                if (e instanceof MouseEvent) {
                    MouseButton button = ((MouseEvent) e).getButton();
                    if (button == MouseButton.PRIMARY && GameScene.isDisplayingDescription == false) {
                        eventHandler.handle(e);
                    }
                    else if (button == MouseButton.SECONDARY && GameScene.isDisplayingDescription == false) {
                        displayDescription();
                        GameScene.isDisplayingDescription = true;
                        GameScene.setOnPressed((e1)->{
                            if(e1 instanceof MouseEvent){
                                if(((MouseEvent) e1).getButton() == MouseButton.PRIMARY &&  GameScene.isDisplayingDescription == true) {
                                    hideDescription();
                                    GameScene.isDisplayingDescription = false;
                                    GameScene.setOnPressed((ee) -> {
                                    });
                                }
                            }
                        });
                    }
                }
            });
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

    private void displayDescription() {
        GameScene.disable();
        GameScene.add(descriptionController);
    }

    private void hideDescription() {
        GameScene.enable();
        descriptionController.deleteOnScene();
    }
}
