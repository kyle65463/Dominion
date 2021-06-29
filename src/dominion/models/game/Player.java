package dominion.models.game;

import dominion.game.GameManager;
import dominion.game.Logger;
import dominion.models.User;
import dominion.models.events.game.EndBuyingPhaseEvent;
import dominion.models.events.game.EndPlayingActionsPhaseEvent;
import dominion.models.events.game.PlayCardEvent;
import dominion.models.game.cards.Card;
import dominion.models.game.cards.actions.Action;
import dominion.models.game.cards.actions.HasSelection;
import dominion.models.game.cards.actions.Reaction;
import dominion.models.game.cards.treasures.Treasure;
import javafx.event.EventHandler;
import javafx.util.Pair;

import java.util.ArrayList;
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
    private int numScores = 3;

    private int numActions = 1;
    private int numCoins = 0;
    private int numPurchases = 1;

    private boolean isEnableUi;
    private boolean immuneNextAttack = false;
    private Deck deck;
    private DiscardPile discardPile;
    private FieldCards fieldCards;
    private HandCards handCards;
    private ActionBar actionBar;
    private PlayerStatus playerStatus;

    private int maxSelectedCard = Integer.MAX_VALUE;
    private int exactSelectingCards = 0;
    private List<Card> selectedCards = new ArrayList<>();

    // Functions
    public PlayerStatus getPlayerStatus() {
        return playerStatus;
    }

    public void setImmuneNextAttack(boolean b) {
        immuneNextAttack = b;
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

    public int getNumActions() {
        return numActions;
    }

    public void enableUi() {
        deck.enableUi();
        discardPile.enableUi();
        handCards.enableUi();
        actionBar.enableUi();
    }

    public void receiveNewCard(Card card) {
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

    public void setMaxSelectingCards(int maxSelectingCards) {
        this.maxSelectedCard = maxSelectingCards;
    }

    public void setExactSelectingCards(int exactSelectingCards) {
        this.exactSelectingCards = exactSelectingCards;
        this.maxSelectedCard = exactSelectingCards;
        actionBar.setButtonVisible(false);
    }

    public void resetMaxSelectingCards() {
        maxSelectedCard = Integer.MAX_VALUE;
    }

    public void resetExactSelectingCards() {
        exactSelectingCards = 0;
    }

    public void setFieldCards(FieldCards fieldCards) {
        this.fieldCards = fieldCards;
    }

    public void setDeckCards(List<Card> cards) {
        deck.addCards(cards, true);
        setPlayerStatusValues();
    }

    public List<Card> getHandCards() {
        return handCards.getCards();
    }

    public List<Card> getAllCards() {
        List<Card> cards = new ArrayList<>();
        cards.addAll(handCards.getCards());
        cards.addAll(discardPile.getCards());
        cards.addAll(deck.getCards());
        cards.addAll(fieldCards.getCards());
        return cards;
    }

    public void playCard(int cardId, boolean decreaseNumActions) {
        Card card = handCards.getCardByCardId(cardId);
        Logger.logPlayCard(this, card);
        handCards.removeCard(card);
        fieldCards.addCard(card);
        if (card instanceof Treasure) {
            numCoins += ((Treasure) card).getNumValue();
        } else if (card instanceof Action) {
            ((Action) card).perform(this, decreaseNumActions);
        }

        setPlayerStatusValues();
        setActionBarValues();
    }

    public void checkActionCardsAndEndPlayingActionPhase() {
        if(!hasActionCards()) {
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
        Logger.logReactCard(this, card);
        Reaction reactionCard = (Reaction) card;
        reactionCard.performReaction(this);
    }

    public Pair<String, String> getActionBarStatus() {
        String originalStatus = actionBar.getStatus();
        String originalButtonText = actionBar.getButtonText();
        return new Pair<>(originalStatus, originalButtonText);
    }

    public void setActionBarStatus(String status, String buttonText) {
        actionBar.setStatus(status);
        actionBar.setButtonText(buttonText);
    }

    public EventHandler getActionBarButtonOnPressed() {
        return actionBar.getButtonOnPressed();
    }

    public void setActionBarButtonHandler(EventHandler eventHandler) {
        actionBar.setButtonOnPressed(eventHandler);
    }

    public void setActionBarAutoTreasureHandler(EventHandler eventHandler) {
        actionBar.setAutoTreasureOnPressed(eventHandler);
    }

    public void setActionBarAutoTreasure(boolean b) {
        actionBar.setAutoTreasure(b);
    }

    public List<Card> getSelectedCards() {
        return new ArrayList<>(selectedCards);
    }

    public void selectCard(int cardId) {
        Card card = handCards.getCardByCardId(cardId);
        if (selectedCards.contains(card)) {
            card.removeHighlight();
            selectedCards.remove(card);
        } else if(selectedCards.size() < maxSelectedCard){
            card.setHighlight();
            selectedCards.add(card);
        }

        if(exactSelectingCards > 0) {
            if(selectedCards.size() == exactSelectingCards){
                actionBar.setButtonVisible(true);
            }
            else {
                actionBar.setButtonVisible(false);
            }
        }
    }

    public void clearSelectingCards() {
        for (Card card : selectedCards) {
            card.removeHighlight();
        }
        selectedCards.clear();
    }

    public void doneSelection(int cardId) {
        HasSelection card = (HasSelection) fieldCards.getCardByCardId(cardId);
        card.performSelection(this, selectedCards);
        resetMaxSelectingCards();
        resetExactSelectingCards();
        actionBar.setButtonVisible(true);
        clearSelectingCards();
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

    public void discardAllHandCards() {
        // Discard all hand cards to discard pile
        List<Card> cards = handCards.getCards();
        discardPile.addCards(cards);
        handCards.removeAllCards();
        setPlayerStatusValues();
    }

    public void discardAllFieldCards() {
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

    public void trashHandCards(List<Card> cards) {
        for(Card card : cards) {
            System.out.println(card);
            Logger.logTrashCard(this, card);
        }
        handCards.removeCards(cards);
        for(Card card : cards) {
            card.disableUi();
        }
        setPlayerStatusValues();
    }

    public void discardHandCards(List<Card> cards) {

        handCards.removeCards(cards);
        discardPile.addCards(cards);
        setPlayerStatusValues();
    }

    public void gainCard(CardSelectedHandler cardSelectedHandler) {

    }

    public void drawCards(int numCards) {
        // Check bounds
        if (numCards > discardPile.getNumCards() + deck.getNumCards()) {
            List<Card> cards = deck.popCards(deck.getNumCards());
            handCards.addCards(cards);
            List<Card> newCards = discardPile.getCards();
            discardPile.removeCards();
            handCards.addCards(newCards);
            Logger.logDrawCard(this, cards.size() + newCards.size());
            return;
        }

        // Refill the deck if it's empty
        if (deck.isEmpty()) {
            List<Card> newCards = discardPile.getCards();
            discardPile.removeCards();
            deck.addCards(newCards, true);
        }

        // Draw cards from the deck
        List<Card> cards = deck.popCards(numCards);
        Logger.logDrawCard(this, cards.size());
        handCards.addCards(cards);

        // Draw cards again if not enough
        if (cards.size() < numCards) {
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
        statusSnapshot = new StatusSnapshot(actionBar.getButtonText(), actionBar.getStatus(),
                actionBar.getButtonOnPressed(), handCards.getCardSelectedHandler());
    }

    public void recoverStatus() {
        if (statusSnapshot != null) {
            setActionBarStatus(statusSnapshot.status, statusSnapshot.buttonText);
            setCardSelectedHandler(statusSnapshot.cardSelectedHandler);
            setActionBarButtonHandler(statusSnapshot.buttonHandler);
            statusSnapshot = null;
        }
    }
}
