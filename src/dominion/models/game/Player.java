package dominion.models.game;

import dominion.models.User;
import dominion.models.game.cards.Card;

import java.util.List;

public class Player {
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
    private int score = 3;

    private int numActions = 1;
    private int numCoins = 0;
    private int numPurchase = 1;
    private String status = "";
    private String buttonText = "";

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

    public void enableUi(GameScene gameScene) {
        deck.enableUi(gameScene);
        discardPile.enableUi(gameScene);
        handCards.enableUi(gameScene);
        actionBar.enableUi(gameScene);
    }

    public void setFieldCards(FieldCards fieldCards) {
        this.fieldCards = fieldCards;
    }

    public void setDeckCards(List<Card> cards) {
        deck.addCards(cards, true);
        updatePlayerStatus();
    }

    public void playCard(Card card) {
        handCards.removeCard(card);
        fieldCards.addCard(card);
        updatePlayerStatus();
        updateActionStatus();
    }

    public boolean hasActionCards() {
        return handCards.hasActionCards();
    }

    public void selectActionCards() {
        status = "你可以打出行動卡";
        buttonText = "結束行動";
        handCards.enableActionCards();
        updateActionStatus();
    }

    public void selectTreasureCards() {
        status = "你可以購買卡片";
        buttonText = "結束購買";
        handCards.enableTreasureCards();
        updateActionStatus();
    }

    public void endTurn() {
        // Update action status
        numActions = 1;
        numCoins = 0;
        numPurchase = 1;
        status = "等待其他人的回合";
        buttonText = "";
        updateActionStatus();

        // Discard all hand cards to discard pile
        List<Card> cards = handCards.getCards();
        discardPile.addCards(cards);
        handCards.removeCards();

        // Discard all field cards to discard pile
        cards = fieldCards.getCards();
        discardPile.addCards(cards);
        fieldCards.removeCards();

        // Redraw cards
        drawCards2(5);

        handCards.disableAllCards();
        updatePlayerStatus();
    }

    public void drawCards2(int numCards) {
        if(deck.isEmpty()){
            List<Card> newCards = discardPile.getCards();
            discardPile.removeCards();
            deck.addCards(newCards, true);
        }

        updatePlayerStatus();
    }

    public void drawCards(int numCards) {
        if(deck.isEmpty()){
            List<Card> newCards = discardPile.getCards();
            discardPile.removeCards();
            deck.addCards(newCards, true);
        }

        List<Card> cards = deck.popCards(numCards);
        handCards.addCards(cards);

        updatePlayerStatus();
    }

    private void updateActionStatus() {
        actionBar.setNumActions(numActions);
        actionBar.setNumPurchases(numPurchase);
        actionBar.setNumCoins(numCoins);
        actionBar.setStatus(status);
        actionBar.setButtonText(buttonText);
    }

    private void updatePlayerStatus() {
        int numDeckCards = deck.getNumCards();
        int numDiscardPileCards = discardPile.getNumCards();
        int numHandCards = handCards.getNumCards();
        playerStatus.setNumDeckCards(numDeckCards);
        playerStatus.setNumDiscardPileCards(numDiscardPileCards);
        playerStatus.setNumHandCards(numHandCards);
        playerStatus.setScore(score);
    }
}
