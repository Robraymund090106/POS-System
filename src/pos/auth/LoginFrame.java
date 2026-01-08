package pos.auth;

import java.awt.*;
import javax.swing.*;

public class LoginFrame extends JFrame {
    public LoginFrame(String role) {

        setTitle(role + " LOGIN");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        
        JPanel wrapper = new JPanel(new GridBagLayout());
        wrapper.setBackground(new Color(20, 50, 120));
        wrapper.setFocusable(false);

        
        JPanel panel = new JPanel();
        panel.setBackground(new Color(20, 50, 120));
        panel.setLayout(null);
        panel.setPreferredSize(new Dimension(450, 500));
        panel.setFocusable(false);

        JLabel title = new JLabel("Hi Welcome, Plz Log In");
        title.setBounds(50, 40, 350, 30);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 18));
        title.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel roleLabel = new JLabel(role + " LOGIN");
        roleLabel.setBounds(50, 80, 350, 30);
        roleLabel.setForeground(Color.WHITE);
        roleLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        roleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JTextField emailField = new JTextField();
        emailField.setBounds(75, 150, 300, 40);
        emailField.setBorder(BorderFactory.createTitledBorder("Email Address"));
        emailField.setFocusable(true);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(75, 210, 300, 40);
        passwordField.setBorder(BorderFactory.createTitledBorder("Password"));
        passwordField.setFocusable(true);

        JButton loginButton = new JButton("LOGIN");
        loginButton.setBounds(75, 280, 300, 45);
        loginButton.setBackground(new Color(212, 175, 55));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFont(new Font("Arial", Font.BOLD, 16));
        loginButton.setFocusPainted(false);
        loginButton.setFocusable(false);

        JLabel forgot = new JLabel("Forgot Password?");
        forgot.setBounds(150, 345, 200, 30);
        forgot.setForeground(Color.WHITE);

        panel.add(title);
        panel.add(roleLabel);
        panel.add(emailField);
        panel.add(passwordField);
        panel.add(loginButton);
        panel.add(forgot);

        wrapper.add(panel);
        add(wrapper);

        setVisible(true);

        
        
    }
}