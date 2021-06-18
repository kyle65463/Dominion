package dominion.connections;

import dominion.models.action.Action;

public interface ActionCallback {
    void send(Action action);
}