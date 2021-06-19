package dominion.connections;

import dominion.models.events.EventAction;

public interface ActionCallback {
    void send(EventAction eventAction);
}