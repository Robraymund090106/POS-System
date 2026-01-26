package main.view;

import java.awt.*;
import java.awt.event.*; 
import javax.swing.*;
import main.model.User;

public class MainDB_Admin extends JFrame {
    User user;

    public MainDB_Admin(User user) {
        this.user = user;
        setTitle("Admin Dashboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        this.setUndecorated(false); 
        this.setResizable(false);   
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int CW = screenSize.width;
        int CH = screenSize.height;
        setSize(CW, CH); 
        setLocationRelativeTo(null);

        final Image bgImage = new ImageIcon("src/main/image/Admin Main Frame (1).png").getImage();

        // MAIN FRAME WITH BG
        JPanel mainContainer = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(bgImage, 0, 0, getWidth(), getHeight(), this);
            }
        };

        JPanel canvas = new JPanel(null);
        canvas.setPreferredSize(new Dimension(CW, CH));
        canvas.setOpaque(false);

        // --- PANEL 1: HEADER 
        JPanel header = new JPanel(null);
        header.setBackground(Color.BLUE);
        header.setBounds(30, 32, 1500, 90);
        header.setOpaque(false);
        canvas.add(header);

        //Company Name
        JLabel companyName = new JLabel("Company name");
        companyName.setFont(new Font("SansSerif", Font.BOLD, 35));
        companyName.setForeground(new Color(255, 204, 0));
        companyName.setBounds(20, 20, 350, 50); 
        header.add(companyName);

        // staff logo (image)
        ImageIcon rawIcon = new ImageIcon("src/main/image/stafflogo.png"); 
        Image img = rawIcon.getImage();
        Image scaledImg = img.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        ImageIcon staffLogoIcon = new ImageIcon(scaledImg);

        JLabel staffLogo = new JLabel("", staffLogoIcon, JLabel.LEFT);
        staffLogo.setBounds(1238, 25, 180, 50); 

        staffLogo.setHorizontalTextPosition(JLabel.RIGHT); 
        staffLogo.setIconTextGap(12); 
        staffLogo.setForeground(new Color(255, 204, 0)); 
        staffLogo.setFont(new Font("SansSerif", Font.BOLD, 22));
        staffLogo.setCursor(new Cursor(Cursor.HAND_CURSOR));

        staffLogo.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                 UserDetailFrame userDetailFrame = new UserDetailFrame(user);
                 userDetailFrame.setVisible(true);  
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                staffLogo.setForeground(new Color(255, 255, 150)); 
            }

            @Override
            public void mouseExited(MouseEvent e) {
                staffLogo.setForeground(new Color(255, 204, 0));
            }
        });
        header.add(staffLogo);

        //Staff Text
        JLabel staffText = new JLabel("ADMIN");
        staffText.setFont(new Font("SansSerif", Font.BOLD, 26));
        staffText.setForeground(new Color(255, 204, 0));
        staffText.setBounds(1300, 25, 400, 50);
        staffText.setCursor(new Cursor(Cursor.HAND_CURSOR));
        staffText.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                 UserDetailFrame userDetailFrame = new UserDetailFrame(user);
                 userDetailFrame.setVisible(true);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                staffText.setCursor(new Cursor(Cursor.HAND_CURSOR));
                staffText.setText("<html><u>Staff</u></html>");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                staffText.setText("ADMIN");
            }
        });
        header.add(staffText);
        header.setComponentZOrder(staffLogo, 0);

        //  PANEL 3: SIDEBAR
        JPanel sidebar = new JPanel(null);
        sidebar.setBounds(30, 130, 158, 800);
        sidebar.setBackground(Color.ORANGE);
        sidebar.setOpaque(false);
        canvas.add(sidebar);

        // LIST ITEM BUTTON
        ImageIcon menuIcon = new ImageIcon("src/main/image/ListBtn (1).png");
        JLabel menuiconLabel = new JLabel(menuIcon);
        menuiconLabel.setBounds(19, 103, menuIcon.getIconWidth(), menuIcon.getIconHeight());
        menuiconLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        menuiconLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                JOptionPane.showMessageDialog(null, "This button has been clicked");
            }
        });
        sidebar.add(menuiconLabel);

        // ADD,EDIT,REMOVE ITEM BUTTON
        ImageIcon rawfoodIcon = new ImageIcon("src/main/image/AddBtn (1).png");
        JLabel foodiconLabel = new JLabel(rawfoodIcon);
        foodiconLabel.setBounds(19, 230, rawfoodIcon.getIconWidth(), rawfoodIcon.getIconHeight());
        foodiconLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        foodiconLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                JOptionPane.showMessageDialog(null, "This button has been clicked");
            }
        });
        sidebar.add(foodiconLabel);

        // STAFF BUTTON
        ImageIcon rawdrinkIcon = new ImageIcon("src/main/image/StaffBtn (1).png");
        JLabel drinkiconLabel = new JLabel(rawdrinkIcon);
        drinkiconLabel.setBounds(19, 351, rawdrinkIcon.getIconWidth(), rawdrinkIcon.getIconHeight());
        drinkiconLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        drinkiconLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                JOptionPane.showMessageDialog(null, "This button has been clicked");
            }
        });
        sidebar.add(drinkiconLabel);

        // TRANSAC ICON
        ImageIcon rawMealIcon = new ImageIcon("src/main/image/Transac (1).png");
        JLabel mealiconLabel = new JLabel(rawMealIcon);
        mealiconLabel.setBounds(18, 470, rawMealIcon.getIconWidth(), rawMealIcon.getIconHeight());
        mealiconLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        mealiconLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                JOptionPane.showMessageDialog(null, "This button has been clicked");
            }
        });
        sidebar.add(mealiconLabel);

        // SALES BTN ICON
        ImageIcon rawSalesIcon = new ImageIcon("src/main/image/SalesBtn (1).png");
        JLabel salesiconLabel = new JLabel(rawSalesIcon);
        salesiconLabel.setBounds(24, 600, rawSalesIcon.getIconWidth(), rawSalesIcon.getIconHeight());
        salesiconLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        salesiconLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                JOptionPane.showMessageDialog(null, "This button has been clicked");
            }
        });
        sidebar.add(salesiconLabel);

        

    //Search barr panell 

        JPanel textPanel = new JPanel(new BorderLayout());
        textPanel.setBounds(830, 162, 550, 40); 
        textPanel.setBackground(Color.ORANGE);
        textPanel.setOpaque(false);

        JTextField searchField = new JTextField() {
            @Override
            protected void paintComponent(Graphics g) {
                if (!isOpaque()) {
                    g.setColor(new Color(0, 0, 0, 0));
                    g.fillRect(0, 0, getWidth(), getHeight());
                }
                super.paintComponent(g);
                if (getText().length() == 0) {
                    Graphics2D g2 = (Graphics2D) g.create();
                    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    g2.setColor(new Color(130, 130, 130)); 
                    g2.setFont(new Font("SansSerif", Font.ITALIC, 25));
                    FontMetrics fm = g2.getFontMetrics();
                    int y = (getHeight() + fm.getAscent() - fm.getDescent()) / 2;
                    g2.drawString("Search here", 45, y); 
                    g2.dispose();
                }
            }
        };

        searchField.setOpaque(false);
        searchField.setBorder(new javax.swing.border.EmptyBorder(0, 45, 0, 10)); 
        searchField.setForeground(Color.BLACK);
        searchField.setFont(new Font("SansSerif", Font.PLAIN, 20));
        searchField.setFocusable(false); 
        
       
        searchField.setCaretColor(new Color(0, 0, 0, 0)); 
        
        searchField.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                searchField.setFocusable(true);
                searchField.requestFocusInWindow();
                
               
                searchField.setCaretColor(Color.BLACK); 
                searchField.getCaret().setBlinkRate(500); 
            }
        });

        searchField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if (searchField.getText().isEmpty()) {
                  
                    searchField.setCaretColor(new Color(0, 0, 0, 0)); 
                    searchField.setFocusable(false);
                }
            }
        });

        searchField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                // Logic goes here
            }
        });
        
        textPanel.add(searchField, BorderLayout.CENTER);
        canvas.add(textPanel);


        // --- FINAL ASSEMBLY ---
        assembleLayers(mainContainer, canvas);

        this.setContentPane(mainContainer);
        this.setVisible(true);
    }

    private void assembleLayers(JPanel background, JPanel foreground) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        background.add(foreground, gbc);
    }

    public static void main(String[] args) {
        User testUser = new User("yna", "hi", "rfvvh", "girl", null, null, "admin", true, 0, 19);
        new MainDB_Admin(testUser);
    }
}