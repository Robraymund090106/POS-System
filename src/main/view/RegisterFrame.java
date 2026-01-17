package main.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RegisterFrame extends JFrame {

    private JLabel Username;
    private JTextField txtUsername;
    private JLabel Password;
    private JPasswordField txtPassword;
    private JLabel ConfirmPassword;
    private JPasswordField txtConfirmPassword;
    private JLabel Email;
    private JTextField txtEmail;
    private JLabel age;
    private JTextField txtAge;


    public RegisterFrame() {

        JLabel label = new JLabel("Registration Frame");

        label.setFont(new Font("Arial", Font.BOLD, 45)); 
        

        add(label);
        setTitle("Register");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH); 
        setLocationRelativeTo(null); 
        setVisible(true);
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                // Handle resizing if needed
                label.setBounds(570, 20, getWidth() - 100, 100);
            }
        });
    }
    
}
