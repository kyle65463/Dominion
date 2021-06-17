package dominion.connection;

import dominion.model.action.Action;

public interface ActionCallback {
    void send(Action action);
}