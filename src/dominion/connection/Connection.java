package dominion.connection;

import dominion.model.action.Action;

abstract public class Connection implements Runnable{

    protected ActionCallback actionCallback = (m) -> {};
    protected String name = "default";
    protected String ip = "localhost";

    public abstract void setActionCallback(ActionCallback callback);
    public abstract void send(Action action);
    public void setName(String name) { this.name = name; }
    public void setIp(String ip) { this.ip = ip; }

    @Override
    public abstract void run();
}
