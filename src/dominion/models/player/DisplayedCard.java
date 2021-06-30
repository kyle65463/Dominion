package dominion.models.player;

import dominion.controllers.components.DisplayedCardController;
import dominion.controllers.components.FullCardController;
import dominion.core.GameManager;
import dominion.models.HasUi;
import dominion.models.areas.GameScene;
import dominion.models.events.game.BuyCardEvent;
import dominion.models.events.game.SelectDisplayedCardEvent;
import dominion.models.cards.Card;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

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
    private FullCardController descriptionController;

    // Functions
    public void enableUi() {
        this.uiController = new DisplayedCardController(card);
        this.descriptionController = new FullCardController(card);
        setNumRemain(numRemain);
        uiController.setOnPressed((e) -> {
            if (e instanceof MouseEvent) {
                MouseButton button = ((MouseEvent) e).getButton();
                if (button == MouseButton.PRIMARY) {
                    if(GameManager.getCurrentPhase() == GameManager.Phase.BuyingCards) {
                        GameManager.sendEvent(new BuyCardEvent(player.getId(), id));
                    }
                    else if(GameManager.getCurrentPhase() == GameManager.Phase.SelectingDisplayedCards) {
                        GameManager.sendEvent(new SelectDisplayedCardEvent(player.getId(), id));
                    }
                } else if (button == MouseButton.SECONDARY && GameScene.isDisplayingDescription == false) {
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

    public void displayDescription(){
        GameScene.disable();
        GameScene.add(descriptionController);
    }

    public void hideDescription(){
        GameScene.enable();
        descriptionController.deleteOnScene();
    }
}
