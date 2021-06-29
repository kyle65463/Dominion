package dominion.models.events.game;

import dominion.models.events.Event;

public abstract class VoiceEvent extends Event {
    // Constructor
    public VoiceEvent() { }

    public abstract void perform();
}