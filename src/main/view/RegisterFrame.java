package main.view;

import javax.swing.*;

public class RegisterFrame extends JFrame {
    public RegisterFrame() {
        JLabel label = new JLabel("Registration Frame");
        add(label);
        setTitle("Register");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); 
        setVisible(true);
    }
    
}
