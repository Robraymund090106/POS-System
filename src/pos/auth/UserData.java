package pos.auth;

public class UserData {

    private int id;
    private String username;
    private String password;
    private String fullName;
    private String role; // e.g., "ADMIN", "MANAGER", "CASHIER"
    private boolean isActive;

    // Constructor
    public UserData(int id, String username, String password, String fullName, String role, boolean isActive) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.role = role;
        this.isActive = isActive;
    }

    // Standard Getters and Setters
    public int getId() { return id; }
    public String getUsername() { return username; }
    public String getRole() { return role; }
    public String getFullName() { return fullName; }
    public boolean isActive() { return isActive; }

    // Helper method for the UI to check permissions
    public boolean isAdmin() {
        return "ADMIN".equalsIgnoreCase(this.role);
    }

    // To display the user in a JTable or List easily
    @Override
    public String toString() {
        return fullName + " (" + role + ")";
    }
}  

