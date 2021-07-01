package dominion.models.cards;

import dominion.controllers.components.CardController;
import dominion.controllers.components.CardDescriptionController;
import dominion.core.GameManager;
import dominion.models.areas.GameScene;
import dominion.models.HasUi;
import dominion.models.cards.treasures.Treasure;
import dominion.models.handlers.CardNextMoveHandler;
import javafx.event.EventHandler;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public abstract class Card implements HasUi, Cloneable {
    // Constructor
    public Card() {
        disableUi();
        setId();
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
    protected CardDescriptionController descriptionController;
    protected CardNextMoveHandler nextMoveHandler = ()->{};

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

    public void setId() {
        this.id = GameManager.getRandomInt();
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
            descriptionController = new CardDescriptionController(this, false);
        }
        isEnableUi = true;
    }

    public void setCardNextMove(CardNextMoveHandler handler) {
        this.nextMoveHandler = handler;
    }

    public void clearCardNextMove() {
        this.nextMoveHandler = null;
    }

    public void doNextMove() {
        this.nextMoveHandler.perform();
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
                    if (button == MouseButton.PRIMARY && !GameScene.isDisplayingDescription) {
                        eventHandler.handle(e);
                    } else if (button == MouseButton.SECONDARY && !GameScene.isDisplayingDescription) {
                        displayDescription();
                        GameScene.isDisplayingDescription = true;
                        GameScene.setOnPressed((e1) -> {
                            if (e1 instanceof MouseEvent) {
                                if (((MouseEvent) e1).getButton() == MouseButton.PRIMARY && GameScene.isDisplayingDescription) {
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

    public int getNumSelected() {
        if (isEnableUi) {
            return uiController.getNumSelected();
        }
        return 0;
    }

    public void setNumSelected(int numSelected) {
        if (isEnableUi) {
            uiController.setNumSelected(numSelected);
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
