package service;

import model.User;

public class Authentication {

    private static User loggedInUser;

    public static void setLoggedUser(User user){
        loggedInUser = user;
    }

    public static User getLoggedInUser (){
        return loggedInUser;
    }

    public static void logout(){
        loggedInUser = null;
    }

}
