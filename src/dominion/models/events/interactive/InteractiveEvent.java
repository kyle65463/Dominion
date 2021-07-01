package dominion.models.events.interactive;

import dominion.models.events.Event;

public abstract class InteractiveEvent extends Event {
    // Constructor
    public InteractiveEvent() { }

    public abstract void perform();
}