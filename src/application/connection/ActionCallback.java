package application.connection;

import application.action.Action;

public interface ActionCallback {
    void send(Action action);
}