package application.connection;

import application.action.Action;

public interface Callback {
    void set(Action action);
}