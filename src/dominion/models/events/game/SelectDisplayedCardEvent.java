package dominion.models.events.game;

public class SelectDisplayedCardEvent extends GameEvent {
    // Constructor
    public SelectDisplayedCardEvent(int playerId, int displayedCardId) {
        super(playerId);
        this.displayedCardId = displayedCardId;
    }

    // Variables
    private int displayedCardId;

    // Functions
    @Override
    public void perform() {
        if (GameManager.getCurrentPhase() == GameManager.Phase.SelectingDisplayedCards) {
            Player player = GameManager.getPlayerById(playerId);
            player.selectDisplayedCard(displayedCardId);
//        }
        }
    }
}