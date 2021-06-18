package dominion.models.action;

import java.io.Serializable;

public abstract class Action implements Serializable{
    public Action(String username, String str) {
        this.username = username;
        this.content = str;
    }

    public String getContent() { return content; }

    public String getUsername() {
        return username;
    }

    private String username;
    private String content;
}
