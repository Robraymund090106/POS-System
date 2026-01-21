package main.backend;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import main.model.User;

public class ChangePasswordFrame extends JFrame {
    User user;

    public ChangePasswordFrame(User user) throws HeadlessException {
        this.user = user;
        
        setTitle("Change Password");
        setSize(500, 400); 
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        Color navy = new Color(0, 0, 128);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(navy);
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        Dimension textFieldSize = new Dimension(250, 40); 

        JLabel currentPasswordLabel = new JLabel("Current Password: ");
        currentPasswordLabel.setFont(new Font("Arial", Font.BOLD, 12));
        currentPasswordLabel.setForeground(Color.WHITE);
        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 0;
        panel.add(currentPasswordLabel, gbc);

        JTextField currentPassword = new JTextField();
        currentPassword.setPreferredSize(textFieldSize); 
        gbc.gridx = 1; gbc.gridy = 0; gbc.weightx = 1.0;
        panel.add(currentPassword, gbc);

        JLabel newPasswordLabel = new JLabel("New Password: ");
        newPasswordLabel.setFont(new Font("Arial", Font.BOLD, 12));
        newPasswordLabel.setForeground(Color.WHITE);
        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(newPasswordLabel, gbc);

        JTextField newPassword = new JTextField();
        newPassword.setPreferredSize(textFieldSize);
        gbc.gridx = 1; gbc.gridy = 1;
        panel.add(newPassword, gbc);

        JLabel confirmPasswordLabel = new JLabel("Confirm Password: ");
        confirmPasswordLabel.setFont(new Font("Arial", Font.BOLD, 12));
        confirmPasswordLabel.setForeground(Color.WHITE);
        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(confirmPasswordLabel, gbc);

        JTextField confirmPassword = new JTextField();
        confirmPassword.setPreferredSize(textFieldSize);
        gbc.gridx = 1; gbc.gridy = 2;
        panel.add(confirmPassword, gbc);

        JButton changePasswordButton = new JButton("Change Password");
        changePasswordButton.setBackground(Color.GREEN);
        changePasswordButton.setForeground(Color.WHITE);
        changePasswordButton.setPreferredSize(new Dimension(150, 40));
        gbc.gridx = 0; gbc.gridy = 3;
        panel.add(changePasswordButton, gbc);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.setBackground(Color.RED);
        cancelButton.setForeground(Color.WHITE);
        cancelButton.setPreferredSize(new Dimension(150, 40));
        gbc.gridx = 1; gbc.gridy = 3;
        panel.add(cancelButton, gbc);

        add(panel);
        setVisible(true);
    }
}