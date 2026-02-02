package main.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import main.model.Product;
import main.model.User;

public class DatabaseManager {

    private static final String DB_URL = "jdbc:sqlite:pos.db";

    // since we are working with an embedded database (SQLite), each device has its own local copy of the database.
    // hence hindi tayo makakashare ng one database
    // returns a Connection to the SQLite database file (creates file if it doesn't exist)
    private static Connection connect() {
        try {
            return DriverManager.getConnection(DB_URL);
        } catch (SQLException e) {
            System.err.println("Failed to connect to database: " + e.getMessage());
            return null;
        }
    }

    // this method creates the tables for the database ensuring na lahat tayo have the same structure
    public static void initializeDatabase() {

    try (Connection conn = connect()) {
        if (conn != null) {
            Statement stmt = conn.createStatement();
            
        
            String sql = "CREATE TABLE IF NOT EXISTS User (" +
                         "userId INTEGER PRIMARY KEY AUTOINCREMENT, " +
                         "username TEXT NOT NULL UNIQUE, " +
                         "password TEXT NOT NULL, " +
                         "fullName TEXT, " +
                         "gender TEXT, " +
                         "birthday TEXT, " +
                         "email TEXT, " +
                         "role TEXT, " +
                         "isActive INTEGER DEFAULT 1, " +
                         "age INTEGER)";
            
            stmt.execute(sql);
            
            
            String addAdmin = "INSERT OR IGNORE INTO User (username, password, fullname, gender, birthday, role, email, age) " +
                              "VALUES ('admin', 'admin123', 'Rob Raymundo', 'Male', 'September 1 2006', 'ADMIN', 'admin@pos.com', 25)";
            stmt.execute(addAdmin);

            String productTable = "CREATE TABLE IF NOT EXISTS Product (" +
                                 "productId INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                 "name TEXT NOT NULL UNIQUE, " +
                                 "price REAL NOT NULL, " +
                                 "stock INTEGER, " +
                                 "category TEXT, " +
                                 "imagePath TEXT)";
            stmt.execute(productTable);

            // Add sample items 
            String addSample = "INSERT OR IGNORE INTO Product (name, price, stock, category, imagePath) " +
                               "VALUES ('Classic Burger', 150.00, 50, 'Food', 'src/main/image/burger.png'), " +
                               "('Iced Coffee', 85.00, 100, 'Drink', 'src/main/image/coffee.png')";
            stmt.execute(addSample);

            stmt.execute("DELETE FROM Product WHERE productId NOT IN (SELECT MIN(productId) FROM Product GROUP BY name)");

            String salesTable = "CREATE TABLE IF NOT EXISTS Sales (" +
                                "saleId INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                "userId INTEGER, " +
                                "totalPrice REAL, " +
                                "cashGiven REAL, " +
                                "change REAL, " +
                                "paymentMethod TEXT, " +
                                "timestamp DATETIME DEFAULT CURRENT_TIMESTAMP)";

            stmt.execute(salesTable);

            //String addSalesSample = "INSERT OR IGNORE INTO Sales (userId, totalPrice, cashGiven, change, paymentMethod) " +
                                   //"VALUES (1, 235.00, 300.00, 65.00, 'Cash')";
//stmt.execute(addSalesSample);

            String saleItemsTable = "CREATE TABLE IF NOT EXISTS SaleItems (" +
                                    "itemId INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                    "saleId INTEGER, " +
                                    "productId INTEGER, " +
                                    "quantity INTEGER, " +
                                    "price REAL, " +
                                    "FOREIGN KEY(saleId) REFERENCES Sales(saleId), " +
                                    "FOREIGN KEY(productId) REFERENCES Product(productId))";
            stmt.execute(saleItemsTable);


            
            System.out.println("Database checked/created successfully.");
        }
        } catch (SQLException e) {
            System.err.println("Database Init Error: " + e.getMessage());
        }
    }   

    public static boolean existsByUsername(String username) {
    String sql = "SELECT COUNT(*) FROM User WHERE username = ?";
    
    try (Connection conn = connect();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        
        pstmt.setString(1, username);
        ResultSet rs = pstmt.executeQuery();
        
        if (rs.next()) {

            return rs.getInt(1) > 0;
        }
    } catch (SQLException e) {
        System.err.println("Existence check error: " + e.getMessage());
    }
    return false;
    }

    

    public static boolean checkPassword(String username, String password) {

        String sql = "SELECT password FROM User WHERE username = ?";

        try (Connection conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {

        
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String storedPassword = rs.getString("password");
        
                return storedPassword.equals(password);
            }
        } catch (SQLException e) {
            System.err.println("Password check error: " + e.getMessage());
        }
                return false; 
    }

    public static boolean addUser(User user) {
    String sql = "INSERT INTO User (username, password, fullName, gender, birthday, email, role, isActive, age) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

    try (Connection conn = connect();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {

        pstmt.setString(1, user.getUsername());
        pstmt.setString(2, user.getPassword());
        pstmt.setString(3, user.getFullname());
        pstmt.setString(4, user.getgender());
        pstmt.setString(5, user.getBday());
        pstmt.setString(6, user.getEmail());
        pstmt.setString(7, user.getRole());
        if (user.getRole().equalsIgnoreCase("STAFF")) {
            pstmt.setInt(8, 0); 
        } else {
            pstmt.setInt(8, user.isActive() ? 1 : 0);
        }
        pstmt.setInt(9, user.getAge());

        pstmt.executeUpdate();
        return true;
    } catch (SQLException e) {
        System.err.println("Error adding user: " + e.getMessage());
        return false;
    }
}

    public static User findByUsername(String username) {
    
    String sql = "SELECT * FROM User WHERE username = ?";
    
    try (Connection conn = connect();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        
        
        pstmt.setString(1, username);
        ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {
            return new User(
                rs.getString("username"),
                rs.getString("password"),
                rs.getString("fullname"),
                rs.getString("gender"),
                rs.getString("birthday"),
                rs.getString("email"),
                rs.getString("role"),
                rs.getInt("isActive") == 1,
                rs.getInt("userId"),
                rs.getInt("age")
            );
        }
    } catch (SQLException e) {
        System.err.println("Error finding user: " + e.getMessage());
    }
    return null; 
}

    public static List<Product> getAllProducts() {
    List<Product> products = new ArrayList<>();
    String sql = "SELECT * FROM Product";
    
    try (Connection conn = connect();
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(sql)) {
        
        while (rs.next()) {
            products.add(new Product(
                rs.getInt("productId"),
                rs.getString("name"),
                rs.getDouble("price"),
                rs.getInt("stock"),
                rs.getString("category"),
                rs.getString("imagePath")
            ));
        }
    } catch (SQLException e) {
        System.err.println("Error fetching products: " + e.getMessage());
    }
    return products;
}

public static boolean updateProductStock(String productName, int quantitySold) {
    // This SQL subtracts the sold amount from the current stock
    String sql = "UPDATE Product SET stock = stock - ? WHERE name = ?";

    try (Connection conn = connect();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {

        pstmt.setInt(1, quantitySold);
        pstmt.setString(2, productName);

        int rowsAffected = pstmt.executeUpdate();
        return rowsAffected > 0;

    } catch (SQLException e) {
        System.err.println("Error updating stock: " + e.getMessage());
        return false;
    }
}

public static List<Product> getProductsByCategory(String category) {
    List<Product> products = new ArrayList<>();
    String sql = "SELECT * FROM Product WHERE category = ?";
    
    try (Connection conn = connect();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        
        pstmt.setString(1, category);
        ResultSet rs = pstmt.executeQuery();
        
        while (rs.next()) {
            products.add(new Product(
                rs.getInt("productId"),
                rs.getString("name"),
                rs.getDouble("price"),
                rs.getInt("stock"),
                rs.getString("category"),
                rs.getString("imagePath")
            ));
        }
    } catch (SQLException e) {
        System.err.println("Error filtering products: " + e.getMessage());
    }
    return products;
}

    public static boolean addProduct(Product product) {

        String checkSql = "SELECT count(*) FROM Product WHERE name = ?";
    String insertSql = "INSERT INTO Product (name, price, stock, category, imagePath) VALUES (?, ?, ?, ?, ?)";

    try (Connection conn = connect()) {
        // Validation Step
        try (PreparedStatement checkStmt = conn.prepareStatement(checkSql)) {
            checkStmt.setString(1, product.getName());
            ResultSet rs = checkStmt.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                System.out.println("Product already exists!");
                return false; // Reject the addition
            }
        }

        // 2. If it doesn't exist, proceed with Insert
        try (PreparedStatement pstmt = conn.prepareStatement(insertSql)) {
            pstmt.setString(1, product.getName());
            pstmt.setDouble(2, product.getPrice());
            pstmt.setInt(3, product.getStock());
            pstmt.setString(4, product.getCategory());
            pstmt.setString(5, product.getImagePath());

            pstmt.executeUpdate();
            return true;
        }
    } catch (SQLException e) {
        System.err.println("Database error: " + e.getMessage());
        return false;
    }
    }
    

    public static void deleteProduct(String productName) {
        String sql = "DELETE FROM Product WHERE name = ?";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, productName);
            pstmt.executeUpdate();
            
        } catch (SQLException e) {
            System.err.println("Error deleting product: " + e.getMessage());
        }

    }

    public static List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM User";
        
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                users.add(new User(
                rs.getString("username"),
                rs.getString("password"),
                rs.getString("fullName"),
                rs.getString("gender"),
                rs.getString("birthday"),
                rs.getString("email"),
                rs.getString("role"),
                rs.getInt("isActive") == 1,
                rs.getInt("userId"),
                rs.getInt("age")
            ));
            }
        } catch (SQLException e) {
            System.err.println("Error fetching users: " + e.getMessage());
        }
        return users;
    }

    public static boolean deactivateUser(String username) {
        String sql = "UPDATE User SET isActive = 0 WHERE username = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, username);
            int rowsAffected = pstmt.executeUpdate();
            
            return rowsAffected > 0; 
        } catch (SQLException e) {
            System.err.println("Deactivation Error: " + e.getMessage());
            return false;
        }


    }
    


    public static boolean activateUser(String username) {
    String sql = "UPDATE User SET isActive = 1 WHERE username = ?";
    
    try (Connection conn = connect();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        
        pstmt.setString(1, username);
        int rowsAffected = pstmt.executeUpdate();
        
        return rowsAffected > 0; 
        
    } catch (SQLException e) {
        System.err.println("Activation Error: " + e.getMessage());
        return false;
    }
}

public static User getUserByUsername(String username) {
    String sql = "SELECT * FROM User WHERE username = ?";
    try (Connection conn = connect();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        
        pstmt.setString(1, username);
        ResultSet rs = pstmt.executeQuery();
        
        if (rs.next()) {
            return new User(
                rs.getString("username"),
                rs.getString("password"),
                rs.getString("fullName"),
                rs.getString("gender"),
                rs.getString("birthday"),
                rs.getString("email"),
                rs.getString("role"),
                rs.getInt("isActive") == 1,
                rs.getInt("userId"),
                rs.getInt("age")
            );
        }
    } catch (SQLException e) {
        System.err.println("Error fetching user: " + e.getMessage());
    }
    return null;
}


public static boolean recordSale(int userId, double total, double cash, double change, List<String> itemNames, List<Double> itemPrices, String paymentmethod) {
    String saleSql = "INSERT INTO Sales (userId, totalPrice, cashGiven, change, paymentMethod) VALUES (?, ?, ?, ?, ?)";
    String itemSql = "INSERT INTO SaleItems (saleId, productId, quantity, price) VALUES (?, (SELECT productId FROM Product WHERE name = ?), ?, ?)";

    try (Connection conn = connect()) {
        conn.setAutoCommit(false); // Start transaction

        try (PreparedStatement salePstmt = conn.prepareStatement(saleSql, Statement.RETURN_GENERATED_KEYS)) {
            salePstmt.setInt(1, userId);
            salePstmt.setDouble(2, total);
            salePstmt.setDouble(3, cash);
            salePstmt.setDouble(4, change);
            salePstmt.setString(5, paymentmethod); 
            salePstmt.executeUpdate();

            // Get the saleId that was just created
            ResultSet rs = salePstmt.getGeneratedKeys();
            if (rs.next()) {
                int saleId = rs.getInt(1);

                try (PreparedStatement itemPstmt = conn.prepareStatement(itemSql)) {
                    for (int i = 0; i < itemNames.size(); i++) {
                        itemPstmt.setInt(1, saleId);
                        itemPstmt.setString(2, itemNames.get(i));
                        itemPstmt.setInt(3, 1); // Quantity is 1 per list entry
                        itemPstmt.setDouble(4, itemPrices.get(i));
                        itemPstmt.addBatch();
                    }
                    itemPstmt.executeBatch();
                }
            }
            conn.commit(); // Save everything
            return true;
        } catch (SQLException e) {
            conn.rollback(); // Undo if error occurs
            System.err.println("Transaction Rollback: " + e.getMessage());
            return false;
        }
    } catch (SQLException e) {
        System.err.println("Database Connection Error: " + e.getMessage());
        return false;
    }
}



public static boolean updatePassword(String username, String newPassword) {
    String sql = "UPDATE User SET password = ? WHERE username = ?";

    try (Connection conn = connect();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        
        pstmt.setString(1, newPassword);
        pstmt.setString(2, username);
        
        int rowsAffected = pstmt.executeUpdate();
        return rowsAffected > 0; 
        
    } catch (SQLException e) {
        System.err.println("Password Update Error: " + e.getMessage());
        return false;
    }
}

public static boolean isValidAdminPassword(String enteredPassword) {
    // This query asks: "Is there ANY admin with this password?"
    String sql = "SELECT 1 FROM User WHERE password = ? AND role = 'ADMIN' LIMIT 1";

    try (Connection conn = connect();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {

        pstmt.setString(1, enteredPassword);
        
        try (ResultSet rs = pstmt.executeQuery()) {
            // If rs.next() is true, it means at least one admin matched
            return rs.next();
        }
    } catch (SQLException e) {
        System.err.println("Admin override error: " + e.getMessage());
        return false;
    }
}



public static Map<String, Integer> getTop10Bestsellers() {
    Map<String, Integer> map = new java.util.LinkedHashMap<>();
    // Added LIMIT 10 to the end of the query
    String sql = "SELECT p.name, SUM(si.quantity) as total " +
                 "FROM Product p " +
                 "JOIN SaleItems si ON p.productId = si.productId " +
                 "GROUP BY p.productId " +
                 "ORDER BY total DESC " +
                 "LIMIT 10"; 
    
    try (Connection conn = connect();
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(sql)) {
        while (rs.next()) {
            map.put(rs.getString("name"), rs.getInt("total"));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return map;
}

public static List<String[]> getDetailedTransactions(int userId) {
    List<String[]> transactions = new ArrayList<>();
    // %Y-%m-%d %H:%M ignores seconds so items in the same minute stay grouped
    String sql = "SELECT p.name, SUM(si.quantity) as totalQty, " +
                 "strftime('%Y-%m-%d %H:%M', s.timestamp) as saleTime " +
                 "FROM Sales s " +
                 "JOIN SaleItems si ON s.saleId = si.saleId " +
                 "JOIN Product p ON si.productId = p.productId " +
                 "WHERE s.userId = ? " +
                 "GROUP BY p.name, saleTime " +
                 "ORDER BY s.timestamp DESC";

    try (Connection conn = connect();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        
        pstmt.setInt(1, userId);
        ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {
            transactions.add(new String[]{
                rs.getString("name"),
                String.valueOf(rs.getInt("totalQty")),
                rs.getString("saleTime") // Now contains Date + Time
            });
        }
    } catch (SQLException e) {
        System.err.println("Error fetching timed transactions: " + e.getMessage());
    }
    return transactions;
}
}

