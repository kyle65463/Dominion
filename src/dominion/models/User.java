package dominion.models;

import java.io.Serializable;

public class User implements Serializable {
    // Constructor
    public User(int id, String name) {
        this.name = name;
        this.id = id;
    }

    // Variables
    private String name;
    private int id;

    // Functions
    public String getName() {
        return name;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
}
