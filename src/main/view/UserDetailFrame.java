package main.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.*;
import java.awt.*;
import main.model.*;
import main.backend.*;

public class UserDetailFrame extends JFrame {

    private User user;

    public UserDetailFrame(User user) {
        this.user = user;
        setTitle("User Profile");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        
        JPanel sidebar = new JPanel();
        sidebar.setPreferredSize(new Dimension(280, 500));
        sidebar.setBackground(new Color(0, 0, 128)); 
        // MAKAPAL NA SIDEBAR DIVIDER (Thickness: 5)
        sidebar.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 5, new Color(255, 215, 0)));
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));

        JLabel roleLabel = new JLabel("#" + user.getRole().toUpperCase());
        roleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        roleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        roleLabel.setForeground(Color.WHITE);
        roleLabel.setBorder(new EmptyBorder(20, 0, 10, 0));

        JLabel profileIcon = new JLabel("👤"); 
        profileIcon.setFont(new Font("Arial", Font.PLAIN, 100));
        profileIcon.setForeground(Color.WHITE);
        profileIcon.setAlignmentX(Component.CENTER_ALIGNMENT);

        String prefix = user.getgender().equalsIgnoreCase("Female") ? "Ms." : "Mr.";
        JLabel greeting = new JLabel("Hi, " + prefix + " " + user.getUsername());
        greeting.setFont(new Font("Arial", Font.PLAIN, 18));
        greeting.setForeground(Color.WHITE);
        greeting.setAlignmentX(Component.CENTER_ALIGNMENT);
        greeting.setBorder(new EmptyBorder(10, 0, 30, 0));

        
        JButton privacyBtn = createStyledButton("Privacy Details");
        privacyBtn.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
        privacyBtn.setForeground(Color.WHITE);
        
        JButton changePassBtn = createStyledButton("Change Password");
        changePassBtn.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
        changePassBtn.setForeground(Color.WHITE);

        JButton logoutBtn = createStyledButton("Logout");
        logoutBtn.setBorder(BorderFactory.createLineBorder(new Color(255, 120, 120), 1));
        logoutBtn.setForeground(new Color(255, 120, 120));
        logoutBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (Window window : Window.getWindows()) {
                    window.dispose(); 
                }
                new LoginFrame();
            }
        });

        sidebar.add(roleLabel);
        sidebar.add(profileIcon);
        sidebar.add(greeting);
        sidebar.add(Box.createVerticalStrut(10));
        sidebar.add(privacyBtn);
        sidebar.add(Box.createVerticalStrut(15));
        sidebar.add(changePassBtn);
        sidebar.add(Box.createVerticalStrut(15));
        sidebar.add(logoutBtn);

        JPanel mainContent = new JPanel(null);
        mainContent.setBackground(new Color(0, 0, 128)); 

        JLabel goBack = new JLabel("Go Back");
        goBack.setCursor(new Cursor(Cursor.HAND_CURSOR));
        goBack.setForeground(Color.WHITE);
        goBack.setBounds(420, 10, 60, 20);
        goBack.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) { dispose(); }
        });
        mainContent.add(goBack);

        JLabel header = new JLabel("View Profile / ");
        header.setFont(new Font("Arial", Font.BOLD, 28));
        header.setForeground(Color.WHITE);
        header.setBounds(30, 40, 250, 40);
        mainContent.add(header);

        JPanel detailCard = new JPanel(new GridLayout(5, 1, 0, 10));
        detailCard.setBackground(Color.WHITE);
        detailCard.setBounds(50, 120, 380, 250);

        detailCard.setBorder(BorderFactory.createCompoundBorder(
           BorderFactory.createLineBorder(new Color(255, 215, 0), 8),
            new EmptyBorder(20, 20, 20, 20)
        ));

        Font boldFont = new Font("Arial", Font.BOLD, 15);

        JLabel lblName = new JLabel("Name: " + user.getFullname());
        lblName.setFont(boldFont);
        
        JLabel lblGender = new JLabel("Gender: " + user.getgender());
        lblGender.setFont(boldFont);
        
        JLabel lblBday = new JLabel("Birthday: " + user.getBday());
        lblBday.setFont(boldFont);
        
        JLabel lblUser = new JLabel("Username: " + user.getUsername());
        lblUser.setFont(boldFont);
        
        JLabel passwordValLabel = new JLabel("Password: ********");
        passwordValLabel.setFont(boldFont);

        detailCard.add(lblName);
        detailCard.add(lblGender);
        detailCard.add(lblBday);
        detailCard.add(lblUser);
        detailCard.add(passwordValLabel);

        privacyBtn.addActionListener(new ActionListener() {
            private boolean isVisible = false;
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isVisible) {
                    passwordValLabel.setText("Password: " + user.getPassword());
                    privacyBtn.setText("Hide Privacy Details");
                    isVisible = true;
                } else {
                    passwordValLabel.setText("Password: ********");
                    privacyBtn.setText("Privacy Details");
                    isVisible = false;
                }
            }
        });

        changePassBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ChangePasswordFrame(user);
            }
        });

        mainContent.add(detailCard);

        JLabel logoutIcon = new JLabel("⍈");
        logoutIcon.setFont(new Font("Arial", Font.PLAIN, 40));
        logoutIcon.setForeground(Color.WHITE);
        logoutIcon.setBounds(430, 400, 50, 50);
        logoutIcon.setCursor(new Cursor(Cursor.HAND_CURSOR));
        mainContent.add(logoutIcon);

        add(sidebar, BorderLayout.WEST);
        add(mainContent, BorderLayout.CENTER);
        setVisible(true);
    }

    private JButton createStyledButton(String text) {
        JButton btn = new JButton(text);
        btn.setMaximumSize(new Dimension(220, 50)); 
        btn.setFont(new Font("Arial", Font.BOLD, 14));
        btn.setContentAreaFilled(false);
        btn.setFocusPainted(false);
        btn.setOpaque(false);
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        return btn;
    }
}