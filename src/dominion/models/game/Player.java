package dominion.models.game;

import dominion.game.GameManager;
import dominion.models.User;
import dominion.models.events.game.EndBuyingPhaseEvent;
import dominion.models.events.game.EndPlayingActionsPhaseEvent;
import dominion.models.events.game.PlayCardEvent;
import dominion.models.game.cards.Card;
import dominion.models.game.cards.actions.Action;
import dominion.models.game.cards.treasures.Treasure;
import javafx.event.EventHandler;

import java.util.List;

public class Player  {
    // Constructor
    public Player(User user) {
        this(user.getName(), user.getId());
    }

    public Player(String name, int id) {
        this.name = name;
        this.id = id;
        deck = new Deck();
        discardPile = new DiscardPile();
        handCards = new HandCards(this);
        actionBar = new ActionBar();
        playerStatus = new PlayerStatus();
        playerStatus.setName(name);
    }

    // Variables
    final private String name;
    final private int id;
    private int numScores = 3;

    private int numActions = 1;
    private int numCoins = 0;
    private int numPurchases = 1;

    private boolean isEnableUi;
    private Deck deck;
    private DiscardPile discardPile;
    private FieldCards fieldCards;
    private HandCards handCards;
    private ActionBar actionBar;
    private PlayerStatus playerStatus;

    // Functions
    public PlayerStatus getPlayerStatus(){
        return playerStatus;
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

    public int getNumActions() {
        return numActions;
    }

    public void enableUi(GameScene gameScene) {
        deck.enableUi(gameScene);
        discardPile.enableUi(gameScene);
        handCards.enableUi(gameScene);
        actionBar.enableUi(gameScene);
    }

    public void receiveNewCard(Card card) {
        discardPile.addCard(card);
        setPlayerStatusValues();
    }

    public void decreaseNumActions() {
        numActions--;
        setActionBarValues();
        if(numActions <= 0){
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
        if(numPurchases <= 0){
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

    public void setDeckCards(List<Card> cards) {
        deck.addCards(cards, true);
        setPlayerStatusValues();
    }

    public List<Card> getCards() {
        return handCards.getCards();
    }

    public void playCard(int cardId) {
        Card card = handCards.getCardByCardId(cardId);
        handCards.removeCard(card);
        fieldCards.addCard(card);
        if(card instanceof Treasure) {
            numCoins += ((Treasure) card).getNumValue();
        }
        else if(card instanceof Action){
            ((Action) card).perform(this);
            decreaseNumActions();
            if(!hasActionCards()) {
                GameManager.sendEvent(new EndPlayingActionsPhaseEvent(id));
            }
        }

        setPlayerStatusValues();
        setActionBarValues();
    }

    public boolean hasActionCards() {
        boolean result = handCards.hasActionCards();
        return result;
    }


    public void setActionBarStatus(String status, String buttonText) {
        actionBar.setStatus(status);
        actionBar.setButtonText(buttonText);
    }

    public void setActionBarButtonOnPressed(EventHandler eventHandler) {
        actionBar.setButtonOnPressed(eventHandler);
    }

    public void selectCards(CardSelectedHandler cardSelectedHandler) {
        handCards.setSelectingCards(cardSelectedHandler);
    }

    public void disableSelectingCards(){
        handCards.removeSelectingCards();
    }

    public void discardHandCards() {
        // Discard all hand cards to discard pile
        List<Card> cards = handCards.getCards();
        discardPile.addCards(cards);
        handCards.removeCards();
        setPlayerStatusValues();
    }

    public void discardFieldCards() {
        // Discard all field cards to discard pile
        List<Card> cards = fieldCards.getCards();
        fieldCards.removeCards();
        discardPile.addCards(cards);
        setPlayerStatusValues();
    }

    public void reset() {
        // Update action status
        handCards.disableAllCards();
        numActions = 1;
        numCoins = 0;
        numPurchases = 1;
        setActionBarValues();
    }

    public void drawCards(int numCards) {
        // Check bounds
        if(numCards > discardPile.getNumCards() + deck.getNumCards()){
            return;
        }

        // Refill the deck if it's empty
        if(deck.isEmpty()){
            List<Card> newCards = discardPile.getCards();
            discardPile.removeCards();
            deck.addCards(newCards, true);
        }

        // Draw cards from the deck
        List<Card> cards = deck.popCards(numCards);
        handCards.addCards(cards);

        // Draw cards again if not enough
        if(cards.size() < numCards){
            drawCards(numCards - cards.size());
        }

        setPlayerStatusValues();
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
        numScores = handCards.getNumScores() + deck.getNumScores() + discardPile.getNumScores();
        playerStatus.setScore(numScores);
    }
}
