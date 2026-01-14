package main.view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import main.backend.*;
import main.model.*;
import main.database.*;
import main.Main;


public class LoginFrame extends JFrame {

    public LoginFrame(){

        

        setTitle("POS System");
        setSize(450, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(173, 216, 230));
        setLayout(null);
        setResizable(false);

        //NU Logo Picture sa part nato

        ImageIcon originalIcon = new ImageIcon(
            "C:\\Users\\Ryan Que\\Downloads\\nu_logo.png"
        );

        Image scaledImage = originalIcon.getImage().getScaledInstance(
            150, 150, Image.SCALE_SMOOTH
        );

        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        JLabel logoLabel = new JLabel(scaledIcon);
        logoLabel.setBounds(150, 40, 150, 150);
        logoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(logoLabel);

        JLabel title  = new JLabel("POS System", SwingConstants.CENTER);
        title.setBounds(50, 210, 350, 50);
        title.setFont(new Font("Arial", Font.BOLD, 30));
        add(title);

        JLabel subtitle = new JLabel("Welcome back! Please enter your details.", SwingConstants.CENTER);
        subtitle.setBounds(50, 260, 350, 25);
        subtitle.setFont(new Font("Arial", Font.PLAIN, 14));
        add(subtitle);

        JLabel userLabel = new JLabel("Username");
        userLabel.setBounds(70, 290, 300, 25);
        userLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(userLabel);

        JTextField username = new JTextField();
        username.setBounds(70, 320, 300, 40);
        username.setFont(new Font("Arial", Font.PLAIN, 14));
        username.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        add(username);

        username.setDocument(new UsernameLimit(30, username));

        JLabel passLabel = new JLabel("Password");
        passLabel.setBounds(70, 380, 300, 25);
        passLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(passLabel);

        JPasswordField password = new JPasswordField();
        password.setBounds(70, 410, 300, 40);
        password.setFont(new Font("Arial", Font.PLAIN, 14));
        password.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        add(password);
        password.setDocument(new PasswordLimit(20, password));

        JCheckBox showPass = new JCheckBox("Show Password");
        showPass.setBounds(70, 460, 150, 25);
        showPass.setBackground(new Color(173, 216, 230));
        showPass.setFont(new Font("Arial", Font.PLAIN, 12));
        showPass.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (showPass.isSelected()) {
                    password.setEchoChar((char) 0);
                } else {
                    password.setEchoChar('•');
                }
            }
        });
        add(showPass);

        JButton login = new JButton("Login");
        login.setBounds(125, 500, 200, 45);
        login.setFont(new Font("Arial", Font.BOLD, 16));
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
        add(login);

        setVisible(true);
    }

}
