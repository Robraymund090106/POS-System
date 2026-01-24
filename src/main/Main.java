package main;
import main.database.*;
import main.model.*;
import main.view.*;


public class Main {
    public static User currentUser;

    public static void main(String[] args) {

        
    
        DatabaseManager.initializeDatabase();
        new LoginFrame();

    }
}

