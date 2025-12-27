package pos.auth;

public class User {
    private int id;
    private String username;
    private String password;
    private String role;
    private String fullname;
    private String email;
    
    public User(int id, String username, String password, String role, String fullname, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.fullname = fullname;
        this.email = email;
    }


    // Getters and Setters

    public int getId() { return id;}

    public String getUsername() { return username; }

    public String getPassword() { return password; }

    public String getRole() { return role; }

    public String getFullname() { return fullname; }

    public String getEmail() { return email; }

    public void setPassword(String password) { this.password = password; }

    public void setEmail(String email) { this.email = email; }

    public void setFullname(String fullname) { this.fullname = fullname; }

    public void setRole(String role) { this.role = role; }

    public void setUsername(String username) { this.username = username; }

    public void setId(int id) { this.id = id; }

    //Methods

    public boolean authenticate(String password) {
        return this.password.equals(password);
    }

    public boolean isAdmin() {
        return "admin".equalsIgnoreCase(this.role);
    }

    public boolean isCashier() {
        return "cashier".equalsIgnoreCase(this.role);
    }

    public boolean isManager() {
        return "manager".equalsIgnoreCase(this.role);
    }

    public void displayUserInfo() {
        System.out.println("User ID: " + id);
        System.out.println("Username: " + username);
        System.out.println("Full Name: " + fullname);
        System.out.println("Email: " + email);
        System.out.println("Role: " + role);
    }



    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", role='" + role + '\'' +
                ", fullname='" + fullname + '\'' +
                ", email='" + email + '\'' +
                '}';
    }


}
