package main.backend;

public class LoginLogic {
    public static boolean validateCredentials(String username, String password) {
        // Placeholder logic for validating credentials
        return "admin".equals(username) && "admin123".equals(password);
    }

    public static boolean validUsername(String username){
        return username != null && !username.trim().isEmpty();
    }
}
