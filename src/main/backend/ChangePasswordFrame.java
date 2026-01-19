package main.backend;

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
        //this.user = user;
        setTitle("Change Password");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel currentPasswordLabel = new JLabel("Current Password: ");
        currentPasswordLabel.setFont(new Font("Arial", Font.BOLD, 12));

        JTextField currentPassword = new JTextField();
        JLabel newPasswordLabel = new JLabel("New Password: ");
        newPasswordLabel.setFont(new Font("Arial", Font.BOLD, 12));

        JTextField newPassword = new JTextField();
        JLabel confirmPasswordLabel = new JLabel("Confirm Password: ");
        confirmPasswordLabel.setFont(new Font("Arial", Font.BOLD, 12));

        JTextField confirmPassword = new JTextField();
        JButton changePasswordButton = new JButton("Change Password");
        JButton cancelButton = new JButton("Cancel");
        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));

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