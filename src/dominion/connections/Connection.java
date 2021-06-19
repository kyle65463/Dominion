package dominion.connections;

import dominion.models.events.EventAction;

abstract public class Connection implements Runnable{

    protected ActionCallback actionCallback = (m) -> {};
    protected String name = "default";
    protected String ip = "localhost";

    public abstract void setActionCallback(ActionCallback callback);
    public abstract void send(EventAction eventAction);
    public void setName(String name) { this.name = name; }
    public void setIp(String ip) { this.ip = ip; }

    @Override
    public abstract void run();
}
