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
                ImageIcon bgIcon = new ImageIcon("src\\main\\image\\Login Frame.png");
               g.drawImage(bgIcon.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };

        backgroundPanel.setLayout(null); 
        this.setContentPane(backgroundPanel); 

        ImageIcon originalIcon = new ImageIcon("src\\main\\image\\nu_logo.png");
        Image scaledImage = originalIcon.getImage().getScaledInstance(180, 180, Image.SCALE_SMOOTH);
        JLabel logoLabel = new JLabel(new ImageIcon(scaledImage));
        logoLabel.setSize(180, 180);
        backgroundPanel.add(logoLabel);

        JLabel title = new JLabel("POS System", SwingConstants.CENTER);
        title.setSize(500, 60);
        title.setFont(new Font("Arial", Font.BOLD, 45)); 

        title.setForeground(new Color(255, 215, 0)); 
        backgroundPanel.add(title);

        JLabel subtitle = new JLabel("Welcome back! Please enter your details.", SwingConstants.CENTER);
        subtitle.setSize(500, 30);
        subtitle.setFont(new Font("Arial", Font.PLAIN, 18));
        subtitle.setForeground(Color.WHITE);
        backgroundPanel.add(subtitle);

        JLabel userLabel = new JLabel("Username");
        userLabel.setSize(400, 30);
        userLabel.setFont(new Font("Arial ", Font.BOLD, 20));
        userLabel.setForeground(Color.WHITE);
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
        passLabel.setForeground(Color.WHITE);
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
        showPass.setForeground(Color.WHITE);
        showPass.addActionListener(e -> {
            if (showPass.isSelected()) password.setEchoChar((char) 0);
            else password.setEchoChar('•');
        });
        backgroundPanel.add(showPass);

        JButton login = new JButton("Login");
        login.setSize(150, 60);
        login.setFont(new Font("Arial", Font.BOLD, 20));
        login.setBackground(Color.GREEN);
        login.setForeground(Color.WHITE);
        login.setFocusPainted(false);

        JButton sgnup = new JButton("Sign Up");
        sgnup.setSize(150, 60);
        sgnup.setFont(new Font("Arial", Font.BOLD, 20));
        sgnup.setBackground(Color.GREEN);
        sgnup.setForeground(Color.WHITE);
        sgnup.setFocusPainted(false);
        backgroundPanel.add(login);
        backgroundPanel.add(sgnup); 

        sgnup.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); 
                new RegisterFrame(); 
            }
        });

      login.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
        String userText = username.getText().trim(); // FIXED: lowercase + trim
        String passText = new String(password.getPassword()).trim();
        
        if (userText.isEmpty() && passText.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please input your details", "Login Failed", JOptionPane.ERROR_MESSAGE);
            username.setText("");
            password.setText("");
            return;
        }
        if (userText.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Username needed", "Login Failed", JOptionPane.ERROR_MESSAGE);
            username.setText("");
            return;
        }
        if (passText.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Password needed", "Login Failed", JOptionPane.ERROR_MESSAGE);
            password.setText("");
            return;
        }
        
        if (DatabaseManager.existsByUsername(userText)) {
            if (DatabaseManager.checkPassword(userText, passText)) {
                User loggedInUser = DatabaseManager.findByUsername(userText);

                if(!loggedInUser.isActive()) {
                    JOptionPane.showMessageDialog(null, "Your account is inactive. Please contact the administrator.", "Login Failed", JOptionPane.ERROR_MESSAGE);
                    password.setText("");
                    username.setText("");
                    return; 
                }
                Main.currentUser = loggedInUser;
                JOptionPane.showMessageDialog(null, "Login Successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                dispose();
                new testframe (Main.currentUser);
            } else {
                JOptionPane.showMessageDialog(null, "Wrong password", "Login Failed", JOptionPane.ERROR_MESSAGE);
                password.setText("");
            }
        } else {

            JOptionPane.showMessageDialog(null, "Username not Found", "Login Failed", JOptionPane.ERROR_MESSAGE);
            username.setText("");
            password.setText("");
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
                logoLabel.setLocation(centerX - 90, centerY - 220);
                title.setLocation(centerX - 250, centerY - 10);
                subtitle.setLocation(centerX - 250, centerY - -70);
                
                userLabel.setLocation(centerX - 200, centerY + 120);
                username.setLocation(centerX - 200, centerY + 150);
                
                passLabel.setLocation(centerX - 200, centerY + 210);
                password.setLocation(centerX - 200, centerY + 240);
                
                showPass.setLocation(centerX - 200, centerY + 290);
                login.setLocation(centerX - 5, centerY + 350);
                sgnup.setLocation(centerX - 165, centerY + 350);
            }
        });

        setVisible(true);
    }
}
