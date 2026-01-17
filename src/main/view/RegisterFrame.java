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

        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon bgIcon = new ImageIcon("c:\\Users\\Ryan Que\\Downloads\\Email.png");
              
                g.drawImage(bgIcon.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };

        JLabel label = new JLabel("Sign Up");

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
                int w = backgroundPanel.getWidth();
                int h = backgroundPanel.getHeight();
                int centerX = w / 2;
                
                
                int centerY = (h / 2) - 100; 
                label.setLocation(centerX, centerY);
            }
        });
    }
    
}

