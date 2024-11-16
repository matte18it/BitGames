package org.example.implementrazioneprogetto.Pattern.Singleton;

public class UserData {
    //Attributi
    private static UserData instance = null;
    private String username;

    //Costruttore
    private UserData(){}

    public static UserData getInstance(){
        if (instance == null)
            instance = new UserData();

        return instance;
    }

    //Metodi
    public void setUsername(String username){
        this.username = username;
    }
    public String getUsername(){
        return username;
    }
}
