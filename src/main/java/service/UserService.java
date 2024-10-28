package service;

import model.Author;
import model.Moderator;
import model.User;

public abstract class UserService {

    public User signUp (String username, String password, int choiceForUser) {
        User user;
        if (choiceForUser == 1) {
            user = new Author(username, password, password);
        } else if (choiceForUser == 2) {
            user = new Moderator(username, password);
        }else {
            System.out.println("Invalid choice! ");
            return null;
        }
        addUserToDatabase(user);
        return user;
    }

    public User userLogin(String username, String password) {
        User user = isPasswordCorrectForUsername (username, password);
        if (user != null) {
            Authentication.setLoggedUser(user);
            return user;
        }
        return null;
    }

    public User isPasswordCorrectForUsername(String username, String password) {
        User userSignedUp = userHasSignedUp(username) ;
        if (password.equals(userSignedUp.getPassword()))
            return userSignedUp;
        return null;
    }

    public abstract void addUserToDatabase(User user);

    public abstract User userHasSignedUp(String username);
}
