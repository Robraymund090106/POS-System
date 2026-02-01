package main.backend;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import main.model.User;
import main.view.DB_Staff;
import main.database.DatabaseManager;

public class ChangePasswordFrame extends JFrame {
    User user;

    public ChangePasswordFrame(User user) throws HeadlessException {
        this.user = user;
        
        setTitle("Change Password");
        setSize(500, 450); // Increased height slightly for the checkbox
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        Color navy = new Color(15, 35, 80);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(navy);
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        Dimension textFieldSize = new Dimension(250, 40); 

        // Current Password
        JLabel currentPasswordLabel = new JLabel("Current Password: ");
        currentPasswordLabel.setFont(new Font("Arial", Font.BOLD, 12));
        currentPasswordLabel.setForeground(Color.WHITE);
        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 0;
        panel.add(currentPasswordLabel, gbc);

        JPasswordField currentPassword = new JPasswordField();
        currentPassword.setPreferredSize(textFieldSize); 
        gbc.gridx = 1; gbc.gridy = 0; gbc.weightx = 1.0;
        panel.add(currentPassword, gbc);

        // New Password
        JLabel newPasswordLabel = new JLabel("New Password: ");
        newPasswordLabel.setFont(new Font("Arial", Font.BOLD, 12));
        newPasswordLabel.setForeground(Color.WHITE);
        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(newPasswordLabel, gbc);
  

        JPasswordField newPassword = new JPasswordField();
        newPassword.setPreferredSize(textFieldSize);
        gbc.gridx = 1; gbc.gridy = 1;
        panel.add(newPassword, gbc);
        newPassword.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                if(emailvalidator.isValidPassword(new String(newPassword.getPassword()))) {
                    newPassword.setForeground(Color.GREEN);
                } else {
                    newPassword.setForeground(Color.RED);
                }
            }
        });


        // Confirm Password
        JLabel confirmPasswordLabel = new JLabel("Confirm Password: ");
        confirmPasswordLabel.setFont(new Font("Arial", Font.BOLD, 12));
        confirmPasswordLabel.setForeground(Color.WHITE);
        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(confirmPasswordLabel, gbc);

        JPasswordField confirmPassword = new JPasswordField();
        confirmPassword.setPreferredSize(textFieldSize);
        gbc.gridx = 1; gbc.gridy = 2;
        panel.add(confirmPassword, gbc);

        // --- SHOW PASSWORD CHECKBOX ---
        JCheckBox showPassword = new JCheckBox("Show Passwords");
        showPassword.setBackground(navy);
        showPassword.setForeground(Color.WHITE);
        showPassword.setFocusable(false);
        gbc.gridx = 1; gbc.gridy = 3;
        panel.add(showPassword, gbc);

        char defaultEchoChar = currentPassword.getEchoChar();

        showPassword.addActionListener(e -> {
            if (showPassword.isSelected()) {
                currentPassword.setEchoChar((char) 0);
                newPassword.setEchoChar((char) 0);
                confirmPassword.setEchoChar((char) 0);
            } else {
                currentPassword.setEchoChar(defaultEchoChar);
                newPassword.setEchoChar(defaultEchoChar);
                confirmPassword.setEchoChar(defaultEchoChar);
            }
        });

        // Buttons
        JButton changePasswordButton = new JButton("Change Password");
        changePasswordButton.setBackground(new Color(165, 215, 155));
        changePasswordButton.setForeground(Color.BLACK); // Changed to black for better contrast on light green
        changePasswordButton.setPreferredSize(new Dimension(150, 40));
        gbc.gridx = 0; gbc.gridy = 4;
        panel.add(changePasswordButton, gbc);

        changePasswordButton.addActionListener(e -> {
            String currentPass = new String(currentPassword.getPassword());
            String newPass = new String(newPassword.getPassword());
            String confirmPass = new String(confirmPassword.getPassword());

            if(currentPass.isEmpty() || newPass.isEmpty() || confirmPass.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if(!currentPass.equals(user.getPassword())){
                JOptionPane.showMessageDialog(this, "Current password is incorrect.", "Error", JOptionPane.ERROR_MESSAGE);
                currentPassword.setText("");
                return;
            }
            if(newPass.equals(currentPass)) {
                JOptionPane.showMessageDialog(this, "New password must be different from current password.", "Error", JOptionPane.ERROR_MESSAGE);
                newPassword.setText("");
                return;
            }
            if(!newPass.equals(confirmPass)) {
                JOptionPane.showMessageDialog(this, "New password and confirm password do not match.", "Error", JOptionPane.ERROR_MESSAGE);
                confirmPassword.setText("");
                return;
            }

            if(!emailvalidator.isValidPassword(newPass)) {
                JOptionPane.showMessageDialog(this, "Password must be 8-20 characters long and include at least one uppercase letter, one lowercase letter, one digit, and one special character (!@#$%^&*()-+).", "Error", JOptionPane.ERROR_MESSAGE);
                newPassword.setText("");
                return;
            }


            user.setPassword(newPass);

            DatabaseManager.updatePassword(user.getUsername(), newPass);
            dispose();
            JOptionPane.showMessageDialog(this, "Password changed successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

            
        });

        JButton cancelButton = new JButton("Cancel");
        cancelButton.setBackground(new Color(220, 100, 100));
        cancelButton.setForeground(Color.WHITE);
        cancelButton.setPreferredSize(new Dimension(150, 40));
        gbc.gridx = 1; gbc.gridy = 4;
        panel.add(cancelButton, gbc);
        cancelButton.addActionListener(e -> dispose());

        add(panel);
        setVisible(true);
    }

}