package main.view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class DBSales_Admin extends JFrame {
    private String username;

    public DBSales_Admin(String username) {
        this.username = username;

        setTitle("Dashboard Admin");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Background Image
        final Image bgImage = new ImageIcon("src/main/image/Admin Frame Sales Report.png").getImage();

        JPanel bgPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(bgImage, 0, 0, getWidth(), getHeight(), this);
            }
        };

        bgPanel.setLayout(null);
        add(bgPanel);

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);

        // --- Header Section ---
        JLabel cmpLabel = new JLabel("COMPANY");
        cmpLabel.setBounds(50, 20, 200, 40);
        cmpLabel.setFont(new Font("Arial", Font.BOLD, 30));
        cmpLabel.setForeground(new Color(218, 165, 32));
        bgPanel.add(cmpLabel);

        // Yellow Staff Logo (image_7cb5de.png)
        java.net.URL imgURL = DBSales_Admin.class.getResource("/main/image/stafflogo.png");
        if (imgURL != null) {
            ImageIcon rawIcon = new ImageIcon(imgURL);
            // Ginawa nating 40x40 para hindi masyadong malaki katabi ng text
            Image scaledImg = rawIcon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
            JLabel staffLogo = new JLabel(new ImageIcon(scaledImg));
            staffLogo.setBounds(1330, 25, 40, 40); // In-align natin sa Admin text
            staffLogo.setCursor(new Cursor(Cursor.HAND_CURSOR));
            bgPanel.add(staffLogo);
        }

        JLabel adLabel = new JLabel("Admin");
        adLabel.setBounds(1385, 30, 150, 30);
        adLabel.setFont(new Font("Arial", Font.BOLD, 25));
        adLabel.setForeground(new Color(218, 165, 32));
        adLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        bgPanel.add(adLabel);

        // --- Sidebar Icons (image_7d1795.png) ---
        
        // 1. Menu Icon - Ito ang magiging standard size natin
        ImageIcon menuIconRaw = new ImageIcon("src/main/image/menu (1).png");
        int targetW = menuIconRaw.getIconWidth();
        int targetH = menuIconRaw.getIconHeight();
        
        JLabel menuiconLabel = new JLabel(menuIconRaw);
        menuiconLabel.setBounds(50, 210, targetW, targetH);
        menuiconLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        bgPanel.add(menuiconLabel);

        // Iba pang icons (Add, Tao, Cycl, Sales) - Gagamit ng Scaled Instance
        bgPanel.add(createScaledLabel("src/main/image/add.png", 50, 310, targetW, targetH));
        bgPanel.add(createScaledLabel("src/main/image/tao.png", 50, 430, targetW, targetH));
        bgPanel.add(createScaledLabel("src/main/image/cycl.png", 50, 540, targetW, targetH));
        bgPanel.add(createScaledLabel("src/main/image/salesicon.png", 50, 650, targetW, targetH));

        // --- Dashboard Labels ---
        JLabel tranLabel = new JLabel("Transaction");
        tranLabel.setBounds(290, 200, 150, 30);
        tranLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        bgPanel.add(tranLabel);

        JLabel osLabel = new JLabel("Online Stuff");
        osLabel.setBounds(620, 200, 150, 30);
        osLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        bgPanel.add(osLabel);

        JLabel tsLabel = new JLabel("Total Stuff");
        tsLabel.setBounds(950, 210, 150, 30);
        tsLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        bgPanel.add(tsLabel);

           JLabel bssLabel = new JLabel("Best Selling");
        bssLabel.setBounds(290, 355, 150, 30);
        bssLabel.setFont(new Font("Arial", Font.BOLD, 20));
        bgPanel.add(bssLabel);

        JLabel bssLabel2 = new JLabel("Best Selling");
        bssLabel2.setBounds(625, 355, 150, 30);
        bssLabel2.setFont(new Font("Arial", Font.BOLD, 20));
        bgPanel.add(bssLabel2);

        setVisible(true);
    }
            

    // Helper method para sa scaling at consistent look
    private JLabel createScaledLabel(String path, int x, int y, int width, int height) {
        ImageIcon icon = new ImageIcon(path);
        Image img = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        JLabel label = new JLabel(new ImageIcon(img));
        label.setBounds(x, y, width, height);
        label.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return label;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new DBSales_Admin("admin"));
    }
}