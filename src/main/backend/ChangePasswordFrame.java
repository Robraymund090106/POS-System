package main.backend;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.HeadlessException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import main.model.User;

public class ChangePasswordFrame extends JFrame {
    User user;

    public ChangePasswordFrame(User user) throws HeadlessException {
        this.user = user;
        
        setTitle("Change Password");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        Color navy = new Color(0, 0, 128);

        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        panel.setBackground(navy);
        
        JLabel currentPasswordLabel = new JLabel("Current Password: ");
        currentPasswordLabel.setFont(new Font("Arial", Font.BOLD, 12));
        currentPasswordLabel.setForeground(Color.WHITE);
        
        JTextField currentPassword = new JTextField();
        currentPassword.setFont(new Font("Arial", Font.BOLD, 12));
        currentPassword.setForeground(Color.WHITE);

        
        JLabel newPasswordLabel = new JLabel("New Password: ");
        newPasswordLabel.setFont(new Font("Arial", Font.BOLD, 12));
        newPasswordLabel.setForeground(Color.WHITE);
        
        JTextField newPassword = new JTextField();
        newPassword.setFont(new Font("Arial", Font.BOLD, 12));
        newPassword.setForeground(Color.WHITE);
        
        JLabel confirmPasswordLabel = new JLabel("Confirm Password: ");
        confirmPasswordLabel.setFont(new Font("Arial", Font.BOLD, 12));
        confirmPasswordLabel.setForeground(Color.WHITE);
        
        JTextField confirmPassword = new JTextField();
        confirmPassword.setFont(new Font("Arial", Font.BOLD, 12));
        confirmPassword.setForeground(Color.WHITE);
        
        JButton changePasswordButton = new JButton("Change Password");
        changePasswordButton.setBackground(Color.GREEN);
        changePasswordButton.setForeground(Color.WHITE);
        changePasswordButton.setFont(new Font("Arial", Font.BOLD, 12));
        
        JButton cancelButton = new JButton("Cancel");
        cancelButton.setBackground(Color.RED);
        cancelButton.setForeground(Color.WHITE);
        cancelButton.setFont(new Font("Arial", Font.BOLD, 12));

        panel.add(currentPasswordLabel);
        panel.add(currentPassword);
        panel.add(newPasswordLabel);
        panel.add(newPassword);
        panel.add(confirmPasswordLabel);
        panel.add(confirmPassword);
        panel.add(changePasswordButton);
        panel.add(cancelButton);

        add(panel);
        setVisible(true);
    }
}
