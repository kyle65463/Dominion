package application.connection;

import application.action.Action;

abstract public class Connection implements Runnable{

    protected Callback setOutput = (m) -> {};
    protected Callback setStatus = (m) -> {};


    public abstract void setOutputCallback(Callback setOutput);

    public void setStatusCallback(Callback setStatus) {
        this.setStatus = setStatus;
    }

    public abstract void send(Action action);

    @Override
    public abstract void run();
}
