package dominion.models.events.game;

import dominion.models.events.Event;

public abstract class InterActiveEvent extends Event {
    // Constructor
    public InterActiveEvent() { }

    public abstract void perform();
}