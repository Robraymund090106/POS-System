package main.backend;

import java.util.regex.Pattern;
import java.util.regex.Matcher;
import javax.swing.JOptionPane;

public class emailvalidator {
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+\\.[a-z]{2,6}$";

    public static boolean isValidEmail(String email) {
        if (email == null) return false;
        
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        
        return matcher.matches();
}

    public static boolean isValidPassword(String password) {
        if (password == null || password.length() < 8 || password.length() > 20) return false;

        boolean hasUpper = false;
        boolean hasLower = false;
        boolean hasDigit = false;
        boolean hasSpecial = false;

        for (char ch : password.toCharArray()) {
            if (Character.isUpperCase(ch)) hasUpper = true;
            else if (Character.isLowerCase(ch)) hasLower = true;
            else if (Character.isDigit(ch)) hasDigit = true;
            else if ("!@#$%^&*()-+".indexOf(ch) >= 0) hasSpecial = true;
        }

        return hasUpper && hasLower && hasDigit && hasSpecial;
    }

    public static boolean isValidUsername(String username) {
        if (username == null || username.length() < 5 || username.length() > 30) return false;

        for (int i = 0; i < username.length(); i++) {
            if(i < 3 && !Character.isLetter(username.charAt(i))){
                
                return false;
            }
        }

        for (char ch : username.toCharArray()) {
            if (!Character.isLetterOrDigit(ch) && ch != '_' && ch != '.') {
                JOptionPane.showMessageDialog(null, "Username can only contain letters, digits, underscores, and periods", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }

        return true;
    }
    public static boolean isValidName(String name){
        if (name == null || name.length() < 2 || name.length() > 30) return false;

        for(char ch : name.trim().toCharArray()){
            if (!Character.isLetter(ch) && ch != ' '){
                return false;
            }
        }
        return true;
    }

    public static boolean samePassword(String password1, String password2){
        if(!password1.equals(password2)){
            JOptionPane.showMessageDialog(null, "unequal password and confirm password", "Error", JOptionPane.ERROR_MESSAGE);

            return false;
        }

        return true;

    }
}
