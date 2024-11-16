package org.example.implementrazioneprogetto.Pattern.Singleton;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager {
    //Attributi
    private static DBManager instance = null;
    Connection con = null;

    //Costruttore
    private DBManager(){}

    //Metodi
    public static DBManager getInstance(){
        if (instance == null)
            instance = new DBManager();

        return instance;
    }
    public Connection getConnection(){
        if (con == null){
            try {
                con = DriverManager.getConnection("YOUR POSTGRES URL", "YOUR POSTGRES USERNAME", "YOUR POSTGRES PASSWORD");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return con;
    }
}
