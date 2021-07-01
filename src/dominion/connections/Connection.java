package dominion.connections;

import dominion.models.events.Event;
import dominion.models.handlers.MyEventHandler;

abstract public class Connection implements Runnable{
    protected MyEventHandler myEventHandler = (m) -> {};
    protected String name = "default";
    protected String ip = "localhost";
    protected String port = "1234";
    public abstract void setEventHandler(MyEventHandler callback);
    public abstract void send(Event event);
    public void setName(String name) { this.name = name; }
    public void setIp(String ip) { this.ip = ip; }
    public void setPort(String port) {this.port = port;}
    @Override
    public abstract void run();
}
