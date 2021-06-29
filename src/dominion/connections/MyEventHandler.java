package dominion.connections;

import dominion.models.events.Event;

public interface MyEventHandler {
    void handle(Event event);
}