package dominion.models.player;

import dominion.core.GameManager;
import dominion.models.areas.DisplayedCard;
import dominion.models.areas.LogBox;
import dominion.models.User;
import dominion.models.events.game.*;
import dominion.models.cards.Card;
import dominion.models.cards.actions.Reaction;
import dominion.models.handlers.*;
import dominion.models.player.PlayerAction.*;
import dominion.models.player.container.Deck;
import dominion.models.player.container.DiscardPile;
import dominion.models.player.container.FieldCards;
import dominion.models.player.container.HandCards;
import javafx.event.EventHandler;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class Player {
    // Constructor
    public Player(User user) {
        this(user.getName(), user.getId(),user);
    }

    public Player(String name, int id,User user) {
        this.name = name;
        this.id = id;
        deck = new Deck();
        discardPile = new DiscardPile();
        handCards = new HandCards();
        actionBar = new ActionBar();
        playerStatus = new PlayerStatus();
        playerStatus.setName(name);
        this.user = user;
    }

    // Variables
    final private String name;
    final private int id;
    private int numScores = 3;

    private int numActions = 1;
    private int numCoins = 0;
    private int numPurchases = 1;

    private boolean isEnableUi;
    private boolean immuneNextAttack = false;

    private User user;
    /* Components */
    private final Deck deck;
    private final DiscardPile discardPile;
    private FieldCards fieldCards;
    private final HandCards handCards;
    private final ActionBar actionBar;
    private final PlayerStatus playerStatus;

    private AfterPlayerActionHandler afterPlayerActionHandler = ()->{};
    private AfterPlayCardHandler afterPlayCardHandler = ()->{};

    private int exactSelectedCards = 0;
    private int maxSelectedCard = Integer.MAX_VALUE;
    private List<Card> selectedCards = new ArrayList<>();
    private List<DisplayedCard> selectedDisplayedCards = new ArrayList<>();
    private CardFilter selectingHandCardsFilter;
    private DisplayedCardFilter selectingDisplayedCardsFilter;

    // Functions

    public int getExactSelectedCards() { return exactSelectedCards; }

    public int getMaxSelectedCards() { return maxSelectedCard; }

    public int getSelectedCardsSize() { return selectedCards.size(); }

    public List<Card> getSelectedCards() { return selectedCards; }

    public List<DisplayedCard> getSelectedDisplayedCards() { return selectedDisplayedCards; }

    public User getUser(){return user;}

    public void setImmuneNextAttack(boolean b) {
        immuneNextAttack = b;
    }

    public void setAfterPlayerActionHandler(AfterPlayerActionHandler handler)  { afterPlayerActionHandler = handler; }

    public void clearAfterPlayerActionHandler() { afterPlayCardHandler = null; }

    public AfterPlayerActionHandler getAfterPlayerActionHandler() { return afterPlayerActionHandler; }

    public void setAfterPlayCardHandler(AfterPlayCardHandler handler) { afterPlayCardHandler = handler; }

    public AfterPlayCardHandler getAfterPlayCardHandler() { return afterPlayCardHandler; }

    public PlayerStatus getPlayerStatus() {
        return playerStatus;
    }

    public boolean getImmuneNextAttack() {
        return immuneNextAttack;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public int getNumCoins() {
        return numCoins;
    }

    public int getNumPurchases() {
        return numPurchases;
    }

    public int getNumActions() { return numActions; }

    public void enableUi() {
        deck.enableUi();
        discardPile.enableUi();
        handCards.enableUi();
        actionBar.enableUi();
    }

    public void buyNewCard(Card card) {
        LogBox.logBuyCard(this, card);
        discardPile.addCard(card);
        setPlayerStatusValues();
    }

    public void decreaseNumActions() {
        numActions--;
        setActionBarValues();
        if (numActions <= 0) {
            GameManager.sendEvent(new EndPlayingActionsPhaseEvent(id));
        }
    }

    public void increaseNumActions(int numIncrease) {
        numActions += numIncrease;
        setActionBarValues();
    }

    public void increaseNumPurchases(int numIncrease) {
        numPurchases += numIncrease;
        setActionBarValues();
    }

    public void decreaseNumPurchases() {
        numPurchases--;
        setActionBarValues();
        if (numPurchases <= 0) {
            GameManager.sendEvent(new EndBuyingPhaseEvent(id));
        }
    }

    public void increaseNumCoins(int numIncrease) {
        numCoins += numIncrease;
        setActionBarValues();
    }

    public void decreaseNumCoins(int numDecreases) {
        numCoins -= numDecreases;
        setActionBarValues();
    }

    public void setFieldCards(FieldCards fieldCards) {
        this.fieldCards = fieldCards;
    }

    public FieldCards getFieldCards() { return this.fieldCards; }

    public void setDeckCards(List<Card> cards) {
        deck.addCards(cards, true);
        setPlayerStatusValues();
    }

    public List<Card> getHandCards() {
        return handCards.getCards();
    }

    public void displayHandCards() {
        LogBox.logDisplayHandCards(this, handCards.getCards());
    }

    public List<Card> getAllCards() {
        List<Card> cards = new ArrayList<>();
        if(fieldCards != null){
            cards.addAll(fieldCards.getCards());
        }
        cards.addAll(handCards.getCards());
        cards.addAll(discardPile.getCards());
        cards.addAll(deck.getCards());
        return cards;
    }

    public void performAction(PlayerAction playerAction) {
//        TODO: discardAllHandCards', discardAllFieldCards, trashHandCard, trashHandCards,
//        TODO: discardHandCard, discardHandCards, startSelectingHandCards, doneHandCardsSelection
//        TODO: selectHandCard, startSelectingDisplayedCards,
        System.out.println("performing action: " + playerAction);
        playerAction.perform(this, handCards, deck, discardPile, fieldCards);
        setPlayerStatusValues();
        setActionBarValues();
        afterPlayerActionHandler.perform();
    }

    public void checkActionCardsAndEndPlayingActionPhase() {
        if (!hasActionCards() || numActions == 0) {
            GameManager.sendEvent(new EndPlayingActionsPhaseEvent(id));
        }
    }

    public boolean hasActionCards() {
        boolean result = handCards.hasActionCards();
        return result;
    }

    public boolean hasReactionCards() {
        boolean result = handCards.hasReactionCards();
        return result;
    }

    public void react(int cardId) {
        Card card = handCards.getCardByCardId(cardId);
        LogBox.logReactCard(this, card);
        Reaction reactionCard = (Reaction) card;
        reactionCard.performReaction(this);
    }

    public Pair<String, String> getActionBarStatus() {
        String originalStatus = actionBar.getStatus();
        String originalButtonText = actionBar.getRightButtonText();
        return new Pair<>(originalStatus, originalButtonText);
    }

    public void setActionBarStatus(String status, String rightButtonText, String leftButtonText) {
        actionBar.setStatus(status);
        actionBar.setRightButtonText(rightButtonText);
        actionBar.setLeftButtonText(leftButtonText);
    }

    public void setActionBarStatus(String status, String rightButtonText) {
        actionBar.setStatus(status);
        actionBar.setRightButtonText(rightButtonText);
    }

    public EventHandler getActionBarButtonOnPressed() {
        return actionBar.getRightButtonOnPressed();
    }

    public void setActionBarRightButtonHandler(EventHandler eventHandler) {
        actionBar.setRightButtonOnPressed(eventHandler);
    }

    public void setActionBarLeftButtonHandler(EventHandler eventHandler) {
        actionBar.setLeftButtonOnPressed(eventHandler);
    }

    public void setActionBarLeftButtonText(String text) {
        actionBar.setLeftButtonText(text);
    }

    public void enableLeftButton(boolean b) {
        actionBar.enableLeftButton(b);
    }

    public void enableRightButton(boolean b) {
        actionBar.enableRightButton(b);
    }

    public CardSelectedHandler getCardSelectedHandler() {
        return handCards.getCardSelectedHandler();
    }

    public void setCardSelectedHandler(CardSelectedHandler cardSelectedHandler) {
        handCards.setCardSelectedHandler(cardSelectedHandler);
    }

    public void removeCardSelectedHandler() {
        handCards.removeCardSelectedHandler();
    }

    public void resetActionBarValues() {
        // Update action status
        numActions = 1;
        numCoins = 0;
        numPurchases = 1;
        setActionBarValues();
    }

    public int getNumScores() {
        return numScores;
    }

    private void setActionBarValues() {
        actionBar.setNumActions(numActions);
        actionBar.setNumPurchases(numPurchases);
        actionBar.setNumCoins(numCoins);
    }

    /*
        Call this when finishing operations on deck/hand/discard pile.
    */
    private void setPlayerStatusValues() {
        // Update num cards of deck/hand/discard pile
        int numDeckCards = deck.getNumCards();
        int numDiscardPileCards = discardPile.getNumCards();
        int numHandCards = handCards.getNumCards();
        playerStatus.setNumDeckCards(numDeckCards);
        playerStatus.setNumDiscardPileCards(numDiscardPileCards);
        playerStatus.setNumHandCards(numHandCards);

        // Update scores
        numScores = handCards.getNumScores(this) + deck.getNumScores(this) + discardPile.getNumScores(this);
        playerStatus.setScore(numScores);
    }


    class StatusSnapshot {
        String buttonText;
        String status;
        EventHandler buttonHandler;
        CardSelectedHandler cardSelectedHandler;

        StatusSnapshot(String buttonText, String status, EventHandler buttonHandler, CardSelectedHandler cardSelectedHandler) {
            this.buttonText = buttonText;
            this.status = status;
            this.buttonHandler = buttonHandler;
            this.cardSelectedHandler = cardSelectedHandler;
        }
    }

    private StatusSnapshot statusSnapshot;

    public void snapshotStatus() {
        statusSnapshot = new StatusSnapshot(actionBar.getRightButtonText(), actionBar.getStatus(),
                actionBar.getRightButtonOnPressed(), handCards.getCardSelectedHandler());
    }

    public void recoverStatus() {
        if (statusSnapshot != null) {
            setActionBarStatus(statusSnapshot.status, statusSnapshot.buttonText);
            setCardSelectedHandler(statusSnapshot.cardSelectedHandler);
            setActionBarRightButtonHandler(statusSnapshot.buttonHandler);
            statusSnapshot = null;
        }
    }

    public void setSelectingHandCardsFilter(CardFilter filter) {
        if (GameManager.getCurrentPhase() == GameManager.Phase.SelectingHandCards) {
            this.selectingHandCardsFilter = filter;
        }
    }

    public CardFilter getSelectingHandCardsFilter() {
        return this.selectingHandCardsFilter;
    }

    public void setSelectingDisplayedCardsFilter(DisplayedCardFilter filter) {
        if (GameManager.getCurrentPhase() == GameManager.Phase.SelectingDisplayedCards) {
            this.selectingDisplayedCardsFilter = filter;
        }
    }

    public DisplayedCardFilter getSelectingDisplayedCardsFilter() { return this.selectingDisplayedCardsFilter; }

    public void setMaxSelectedCards(int maxSelectedCards) {
        if (GameManager.getCurrentPhase() == GameManager.Phase.SelectingHandCards ||
                GameManager.getCurrentPhase() == GameManager.Phase.SelectingDisplayedCards) {
            this.maxSelectedCard = maxSelectedCards;
        }
    }

    public void setExactSelectedCards(int exactSelectedCards) {
        if (GameManager.getCurrentPhase() == GameManager.Phase.SelectingHandCards ||
                GameManager.getCurrentPhase() == GameManager.Phase.SelectingDisplayedCards) {
            this.exactSelectedCards = exactSelectedCards;
            this.maxSelectedCard = exactSelectedCards;
            actionBar.enableRightButton(false);
        }
    }

    public List<Card> popDeckTop(int numCards) {
        List<Card> ret = deck.popTopCards(numCards);
        setPlayerStatusValues();
        return ret;
    }
}
