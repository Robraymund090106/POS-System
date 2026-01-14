package main;
import main.view.*;
import main.database.*;
import main.model.*;


public class Main {
    public static User currentUser;

    public static void main(String[] args) {

        

        DatabaseManager.initializeDatabase();
        new LoginFrame();

    }
}
