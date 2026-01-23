package main;
import main.database.*;
import main.model.*;
import main.view.*;


public class Main {
    public static User currentUser;

    public static void main(String[] args) {

        
    
        DatabaseManager.initializeDatabase();
        Product p1 = new Product(1, "Classic Burger", 149.00, 25, "Main Dish", "images/classic_burger.png");
        Product p2 = new Product(2, "Chicken Alfredo Pasta", 189.00, 15, "Main Dish", "images/chicken_alfredo.png");
        Product p3 = new Product(3, "French Fries", 79.00, 40, "Sides", "images/french_fries.png");
        Product p4 = new Product(4, "Iced Latte", 99.00, 30, "Drinks", "images/iced_latte.png");
        Product p5 = new Product(5, "Chocolate Cake Slice", 120.00, 10, "Desserts", "images/chocolate_cake.png");

        DatabaseManager.addProduct(p1);
        DatabaseManager.addProduct(p2);
        DatabaseManager.addProduct(p3);
        DatabaseManager.addProduct(p4);


        new LoginFrame();

    }
}

