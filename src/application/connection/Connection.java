package application.connection;

import application.action.Action;

abstract public class Connection implements Runnable{

    protected ActionCallback actionCallback = (m) -> {};


    public abstract void setActionCallback(ActionCallback callback);

    public abstract void send(Action action);

    @Override
    public abstract void run();
}
