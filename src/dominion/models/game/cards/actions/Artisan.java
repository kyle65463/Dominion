package dominion.models.game.cards.actions;

import dominion.game.Game;
import dominion.game.GameManager;
import dominion.models.events.game.*;
import dominion.models.game.*;
import dominion.models.game.cards.Card;
import dominion.utils.CardStyles;
import dominion.utils.CardTypes;
import javafx.event.EventHandler;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import java.util.List;
import java.util.stream.Stream;

public class Artisan extends Card implements Action, HasSelection, HasDisplayedSelection {
    // Constructor
    public Artisan() {
        name = "藝術家";
        description = "";
        style = CardStyles.white;
        type = CardTypes.action;
        numCost = 6;
    }

    private int selection_time = 0;
    private boolean decreaseNumActions = true;
    private EventHandler originalHandler;

    @Override
    public void perform(Player performer, boolean decreaseNumActions) {
        this.decreaseNumActions = decreaseNumActions;
        performer.snapshotStatus();


        performer.setMaxSelectingCards(1);
        GameManager.setCurrentPhase(GameManager.Phase.SelectingDisplayedCards);

        Stream<DisplayedCard> purchaseAreaStream = Stream.concat(
                MajorPurchaseArea.getDisplayedCards().stream(),
                MinorPurchaseArea.getDisplayedCards().stream()
        );


        purchaseAreaStream.forEach((displayedCard)-> {
            System.out.println("displayed card: " + displayedCard);
            displayedCard.setDisplayCardEventHandler((e) -> {
                if (e instanceof MouseEvent) {
                    MouseButton button = ((MouseEvent) e).getButton();
                    if (button == MouseButton.PRIMARY) {
                        if ((GameManager.getCurrentPhase() == GameManager.Phase.SelectingDisplayedCards) &&
                                (displayedCard.getCard().getNumCost() <= 5)) {
                            System.out.println(displayedCard.getCard() + " cost " + displayedCard.getCard().getNumCost());
                            GameManager.sendEvent(new SelectDisplayedCardEvent(performer.getId(), displayedCard.getId()));
                        }
                    } else if (button == MouseButton.SECONDARY && GameScene.isDisplayingDescription == false) {
                        displayedCard.displayDescription();
                        GameScene.isDisplayingDescription = true;
                        GameScene.setOnPressed((e1) -> {
                            if (e1 instanceof MouseEvent) {
                                if (((MouseEvent) e1).getButton() == MouseButton.PRIMARY && GameScene.isDisplayingDescription == true) {
                                    displayedCard.hideDescription();
                                    GameScene.isDisplayingDescription = false;
                                    GameScene.setOnPressed((ee) -> {
                                    });
                                }
                            }
                        });
                    }
                }
            });
        });


        performer.setActionBarStatus("選擇加到手牌的牌", "完成");
        performer.setActionBarButtonHandler((e) -> {
            GameManager.sendEvent(new DoneSelectingDisplayedCardEvent(performer.getId(), id));
        });
    }

    @Override
    public void performDisplayedSelection(Player performer, List<DisplayedCard> displayedCards) {
        try {
            DisplayedCard displayedCard = displayedCards.get(0);
            Card card = displayedCard.getCard();
            Card newCard = (Card) card.clone();
            newCard.setNumRemain(1);
            performer.receiveNewHandCard(newCard);
            displayedCard.decreaseNumRemain();
        } catch (Exception e) {

        }

        GameManager.setCurrentPhase(GameManager.Phase.SelectingHandCards);
        performer.snapshotStatus();
        performer.setMaxSelectingCards(1);
        performer.setActionBarStatus("選擇放回牌庫頂的牌", "完成");
        performer.setCardSelectedHandler((card) -> {
            GameManager.sendEvent(new SelectHandCardEvent(performer.getId(), card.getId()));
        });
        performer.setActionBarButtonHandler((e) -> {
            GameManager.sendEvent(new DoneSelectingHandCardEvent(performer.getId(), id));
        });
        Stream.concat(MajorPurchaseArea.getDisplayedCards().stream(),
                      MinorPurchaseArea.getDisplayedCards().stream()).forEach(dummyDisplayedCard -> {
                          DisplayedCard displayedCard = GameManager.getDisplayedCardById(dummyDisplayedCard.getId());
                          displayedCard.setDisplayCardEventHandler(displayedCard.getOriginalHandler());
        });
    }

    @Override
    public void performSelection(Player performer, List<Card> cards) {
        System.out.println("in perform selection");

        if(decreaseNumActions) {
            performer.decreaseNumActions();
        }
        performer.recoverStatus();
        performer.checkActionCardsAndEndPlayingActionPhase();
    }
}
