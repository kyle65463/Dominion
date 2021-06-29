package dominion.connections;

import dominion.models.events.Event;

abstract public class Connection implements Runnable{
    protected MyEventHandler myEventHandler = (m) -> {};
    protected String name = "default";
    protected String ip = "localhost";

    public abstract void setEventHandler(MyEventHandler callback);
    public abstract void send(Event event);
    public void setName(String name) { this.name = name; }
    public void setIp(String ip) { this.ip = ip; }

    @Override
    public abstract void run();
}
