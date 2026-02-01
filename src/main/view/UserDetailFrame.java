package main.view;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import main.backend.*;
import main.model.*;

public class UserDetailFrame extends JFrame {

    private User user;

    public UserDetailFrame(User user) {
        this.user = user;
        
        Color modernNavy = new Color(15, 35, 80);
        
        setTitle("User Profile");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setResizable(false);
        
        JPanel sidebar = new JPanel();
        sidebar.setPreferredSize(new Dimension(280, 500));
        sidebar.setBackground(modernNavy); // <--- APPLIED HERE
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
        logoutBtn.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
        logoutBtn.setForeground(new Color(220, 100, 100)); // Soft red color
        
        logoutBtn.addActionListener(e -> handleLogout());

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
        mainContent.setBackground(modernNavy); // BG COLOR PARA DI MALITO

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

        privacyBtn.addActionListener(e -> {
            if (passwordValLabel.getText().contains("*")) {
                passwordValLabel.setText("Password: " + user.getPassword());
                privacyBtn.setText("Hide Privacy Details");
            } else {
                passwordValLabel.setText("Password: ********");
                privacyBtn.setText("Privacy Details");
            }
        });

        changePassBtn.addActionListener(e -> new ChangePasswordFrame(user));

        mainContent.add(detailCard);

        add(sidebar, BorderLayout.WEST);
        add(mainContent, BorderLayout.CENTER);
        setVisible(true);
    }

    private void handleLogout() {
        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to log out?", "Logout", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            for (Window window : Window.getWindows()) window.dispose();
            new LoginFrame();
        }
    }

    private JButton createStyledButton(String text) {
        JButton btn = new JButton(text);
        btn.setMaximumSize(new Dimension(220, 50)); 
        btn.setFont(new Font("Arial", Font.BOLD, 14));
        btn.setFocusPainted(false);
        btn.setOpaque(true); 
        btn.setContentAreaFilled(false); 
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        return btn;
    }
}