package main.model;

public class User {
    private String username;
    private String password;
    private String Fullname;
    private String gender;
    private String bday;
    private String email;
    private String role;
    private boolean isActive;
    private int userId;
    private int age;

    public User(String username, String password, String Fullname, String gender,String bday, String email, String role, boolean isActive, int userId, int age) {
        this.username = username;
        this.password = password;
        this.Fullname = Fullname;
        this.gender = gender;
        this.bday = bday;
        this.email = email;
        this.role = role;
        this.isActive = isActive;
        this.userId = userId;
        this.age = age;
    }

    // Getters and Setters
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getFullname() {
        return Fullname;
    }
    public void setFullname(String Fullname) {
        this.Fullname = Fullname;
    }

    public String getgender(){
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getBday() {
        return bday;
    }
    public void setBday(String bday) {
        this.bday = bday;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
    public boolean isActive() {
        return isActive;
    }
    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

    // method to check if user is ADMIN
    public boolean isAdmin() {
        return "ADMIN".equalsIgnoreCase(this.role);
    }
}
