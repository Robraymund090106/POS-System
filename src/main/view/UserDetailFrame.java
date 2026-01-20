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
        sidebar.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 2, new Color(255, 215, 0)));
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

        // Greeting
        String prefix = user.getgender().equalsIgnoreCase("Female") ? "Ms." : "Mr.";
        JLabel greeting = new JLabel("Hi, " + prefix + " " + user.getUsername());
        greeting.setFont(new Font("Arial", Font.PLAIN, 18));
        greeting.setForeground(Color.WHITE);
        greeting.setAlignmentX(Component.CENTER_ALIGNMENT);
        greeting.setBorder(new EmptyBorder(10, 0, 30, 0));

        // Buttons
        JButton privacyBtn = createStyledButton("Privacy Details");
        privacyBtn.setBackground(Color.GREEN);
        privacyBtn.setForeground(Color.WHITE);
        
        JButton changePassBtn = createStyledButton("Change Password");
        changePassBtn.setBackground(Color.GREEN);
        changePassBtn.setForeground(Color.WHITE);

        JButton logoutBtn = createStyledButton("Logout");
        logoutBtn.setBackground(Color.RED);
        logoutBtn.setForeground(Color.WHITE);
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
        goBack.setBounds(420, 10, 60, 20);
        goBack.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) { dispose(); }
        });
        mainContent.add(goBack);

        
        JLabel header = new JLabel("View Profile / ");
        header.setFont(new Font("Arial", Font.BOLD, 28));
        header.setForeground(Color.WHITE);
        header.setBounds(30, 40, 200, 40);
        mainContent.add(header);


        JPanel detailCard = new JPanel(new GridLayout(5, 1, 0, 10));
        detailCard.setBackground(Color.WHITE);
        detailCard.setBounds(50, 120, 350, 250);
        detailCard.setBorder(BorderFactory.createCompoundBorder(
           BorderFactory.createLineBorder(new Color(255, 215, 0), 2),
            new EmptyBorder(20, 20, 20, 20)
        ));

        detailCard.add(new JLabel("Name: " + user.getFullname()));
        detailCard.add(new JLabel("Gender: " + user.getgender()));
        detailCard.add(new JLabel("Birthday: " + user.getBday()));
        detailCard.add(new JLabel("Username: " + user.getUsername()));
        JLabel passwordValLabel = new JLabel("Password: ********");
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
        logoutIcon.setBounds(430, 400, 50, 50);
        logoutIcon.setCursor(new Cursor(Cursor.HAND_CURSOR));
        mainContent.add(logoutIcon);

        add(sidebar, BorderLayout.WEST);
        add(mainContent, BorderLayout.CENTER);
        setVisible(true);
    }

    private JButton createStyledButton(String text) {
        JButton btn = new JButton(text);
        btn.setMaximumSize(new Dimension(200, 45));
        btn.setFont(new Font("Arial", Font.BOLD, 14));
        btn.setBackground(Color.BLACK);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        return btn;
    }
    }
    


