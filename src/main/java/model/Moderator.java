package model;

public class Moderator extends User{

    public Moderator(int id, String username, String password) {
        super(id, username, password);
    }

    public Moderator(String username, String password) {
        super(username, password);
    }

}