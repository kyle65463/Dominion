package dominion.models.handlers;

import dominion.models.events.Event;

public interface MyEventHandler {
    void handle(Event event);
}