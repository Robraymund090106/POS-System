package main.view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import main.Main;
import main.backend.*;
import main.database.*;
import main.model.*;

public class LoginFrame extends JFrame {

    public LoginFrame() {
        setTitle("POS System");
        
        setExtendedState(JFrame.MAXIMIZED_BOTH); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);

        // Full screen bg
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon bgIcon = new ImageIcon("c:\\Users\\karin\\Downloads\\Email (3).png");
              
                g.drawImage(bgIcon.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };

        backgroundPanel.setLayout(null); 
        this.setContentPane(backgroundPanel); 


        ImageIcon originalIcon = new ImageIcon("C:\\Users\\Ryan Que\\Downloads\\nu_logo.png");
        Image scaledImage = originalIcon.getImage().getScaledInstance(180, 180, Image.SCALE_SMOOTH);
        JLabel logoLabel = new JLabel(new ImageIcon(scaledImage));
        logoLabel.setSize(180, 180);
        backgroundPanel.add(logoLabel);

        JLabel title = new JLabel("POS System", SwingConstants.CENTER);
        title.setSize(500, 60);
        title.setFont(new Font("Arial", Font.BOLD, 45)); 
        backgroundPanel.add(title);

        JLabel subtitle = new JLabel("Welcome back! Please enter your details.", SwingConstants.CENTER);
        subtitle.setSize(500, 30);
        subtitle.setFont(new Font("Arial", Font.PLAIN, 18));
        backgroundPanel.add(subtitle);

        JLabel userLabel = new JLabel("Username");
        userLabel.setSize(400, 30);
        userLabel.setFont(new Font("Arial ", Font.BOLD, 20));
        backgroundPanel.add(userLabel);

        JTextField username = new JTextField();
        username.setSize(400, 50);
        username.setFont(new Font("Arial", Font.PLAIN, 18));
        username.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        backgroundPanel.add(username);
        username.setDocument(new UsernameLimit(30, username));

        JLabel passLabel = new JLabel("Password");
        passLabel.setSize(400, 30);
        passLabel.setFont(new Font("Arial ", Font.BOLD, 20));
        backgroundPanel.add(passLabel);

        JPasswordField password = new JPasswordField();
        password.setSize(400, 50);
        password.setFont(new Font("Arial ", Font.PLAIN, 18));
        password.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        backgroundPanel.add(password);
        password.setDocument(new PasswordLimit(20, password));

        JCheckBox showPass = new JCheckBox("Show Password");
        showPass.setSize(200, 30);
        showPass.setOpaque(false); 
        showPass.setFont(new Font("Arial", Font.PLAIN, 14));
        showPass.addActionListener(e -> {
            if (showPass.isSelected()) password.setEchoChar((char) 0);
            else password.setEchoChar('•');
        });
        backgroundPanel.add(showPass);

        JButton login = new JButton("Login");
        login.setSize(250, 60);
        login.setFont(new Font("Arial", Font.BOLD, 20));
        login.setBackground(Color.BLACK);
        login.setForeground(Color.WHITE);
        login.setFocusPainted(false);
        
        login.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String userText = username.getText();
                String passText = new String(password.getPassword());
                if (DatabaseManager.existsByUsername(userText)) {
                    if (DatabaseManager.checkPassword(userText, passText)) {
                        User loggedInUser = DatabaseManager.findByUsername(userText);
                        Main.currentUser = loggedInUser;
                        JOptionPane.showMessageDialog(null, "Login Successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                        dispose();
                        new MainDashboard(Main.currentUser);
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid password.", "Login Failed", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid username ", "Login Failed", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        backgroundPanel.add(login);

     
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int w = backgroundPanel.getWidth();
                int h = backgroundPanel.getHeight();
                int centerX = w / 2;
                
                
                int centerY = (h / 2) - 100; 

                // nu logo center position 
                logoLabel.setLocation(centerX - 90, centerY - 250);
                title.setLocation(centerX - 250, centerY - 60);
                subtitle.setLocation(centerX - 250, centerY - 10);
                
                userLabel.setLocation(centerX - 200, centerY + 40);
                username.setLocation(centerX - 200, centerY + 75);
                
                passLabel.setLocation(centerX - 200, centerY + 135);
                password.setLocation(centerX - 200, centerY + 170);
                
                showPass.setLocation(centerX - 200, centerY + 225);
                login.setLocation(centerX - 125, centerY + 270);
            }
        });

        setVisible(true);
    }
}
