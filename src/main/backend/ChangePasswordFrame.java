package main.backend;

import javax.swing.*;

import main.model.User;

public class ChangePasswordFrame extends JFrame{
    User user;

    public ChangePasswordFrame(User user){
        this.user = user;
        setTitle("Change Password");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel CurrentPasswordLabel = new JLabel("Current Password: ");
        JTextField CurrentPassword = new JTextField();
        JLabel NewPasswordLabel = new JLabel("New Password: ");
        JTextField NewPassword = new JTextField();
        JLabel ConfirmPasswordLabel = new JLabel("Confirm Password: ");
        JTextField ConfirmPassword = new JTextField();
        JButton ChangePasswordButton = new JButton("Change Password");
        JButton CancelButton = new JButton("Cancel");
        JPanel panel = new JPanel();
        panel.add(CurrentPasswordLabel);
        panel.add(CurrentPassword);
        panel.add(NewPasswordLabel);
        panel.add(NewPassword);
        panel.add(ConfirmPasswordLabel);
        panel.add(ConfirmPassword);
        panel.add(ChangePasswordButton);
        panel.add(CancelButton);








        add(panel);
        setVisible(true);
    }
    
}
