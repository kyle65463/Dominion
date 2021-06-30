package dominion.models.events.connections;

import dominion.models.events.Event;

public abstract class LeaveEvent extends Event {
    public LeaveEvent(){}
    public abstract void perform();
}
