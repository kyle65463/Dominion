package application.connection;

import application.action.Action;

abstract public class Connection implements Runnable{

    protected ActionCallback actionCallback = (m) -> {};
    protected String name = "default";

    public abstract void setActionCallback(ActionCallback callback);
    public abstract void send(Action action);
    public void setName(String name) { this.name = name; }

    @Override
    public abstract void run();
}
