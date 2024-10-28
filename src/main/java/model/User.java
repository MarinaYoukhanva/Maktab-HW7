package model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public abstract class User {

    private int id;
    private String username;
    private String password;

    public User(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

}
