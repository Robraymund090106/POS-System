package main;
import main.view.*;
import main.database.*;


public class Main {
    public static void main(String[] args) {
        DatabaseManager.initializeDatabase();
        new LoginFrame();
    }
}
