package main.view;

import java.awt.*;
import java.awt.event.*; 
import javax.swing.*;
import main.model.User;

public class MainDB_Admin extends JFrame {
    User user;
    JPanel listpanelitem;
    JPanel addmenuitem;
    JPanel addstaff;
    JPanel transactionpanel;
    JPanel salespanel;

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

        //
        //

        ///
        /// /
        /// 
        /// 
        /// 
        /// 
        /// 
        /// //
        /// /
        /// /
        /// ///
        /// 
        /// 

        //PANELS

        //ITEM LIST PANEL

        listpanelitem = new JPanel(null) {
        private Image listBg = new ImageIcon("src/main/image/itemlistbg.png").getImage();
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            // Draws the background to fit the panel perfectly
            g.drawImage(listBg, 0, 0, getWidth(), getHeight(), this);
        }
    };
        listpanelitem.setBackground(Color.CYAN);
        listpanelitem.setBounds(210, 118, 1290, 700);
        listpanelitem.setOpaque(false);
        listpanelitem.setVisible(true);
    
        
        canvas.add(listpanelitem);


        //ADD MENU PANEL
        addmenuitem = new JPanel();
        addmenuitem.setBackground(Color.RED);
        addmenuitem.setBounds(210, 118, 1290, 700);
        addmenuitem.setVisible(false);

        canvas.add(addmenuitem);

        //ADD STAFF PANEL

        addstaff = new JPanel();
        addstaff.setBackground(Color.ORANGE);
        addstaff.setBounds(210, 118, 1290, 700);
        addstaff.setVisible(false);
        canvas.add(addstaff);

        // TRANSACTION PANEL

        transactionpanel = new JPanel();
        transactionpanel.setBackground(Color.PINK);
        transactionpanel.setBounds(210, 118, 1290, 700);
        transactionpanel.setVisible(false);
        canvas.add(transactionpanel);

        // SALES PANEL

        salespanel = new JPanel();
        salespanel.setBackground(Color.BLUE);
        salespanel.setBounds(210, 118, 1290, 700);
        salespanel.setVisible(false);
        canvas.add(salespanel);

        //

        //
        //
        //
        ///
        /// /
        /// //
        /// //
        /// //
        /// 
        /// /
        /// 
        /// /
        /// ///
        /// 
        /// //
        /// /
        /// /
        /// 
        /// 
        /// 

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
                listpanelitem.setVisible(true);
                addmenuitem.setVisible(false);
                addstaff.setVisible(false);
                transactionpanel.setVisible(false);
                salespanel.setVisible(false);
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
                listpanelitem.setVisible(false);
                addmenuitem.setVisible(true);
                addstaff.setVisible(false);
                transactionpanel.setVisible(false);
                salespanel.setVisible(false);


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
                listpanelitem.setVisible(false);
                addmenuitem.setVisible(false);
                addstaff.setVisible(true);
                transactionpanel.setVisible(false);
                salespanel.setVisible(false);

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
                listpanelitem.setVisible(false);
                addmenuitem.setVisible(false);
                addstaff.setVisible(false);
                transactionpanel.setVisible(true);
                salespanel.setVisible(false);

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
                listpanelitem.setVisible(false);
                addmenuitem.setVisible(false);
                addstaff.setVisible(false);
                transactionpanel.setVisible(false);
                salespanel.setVisible(true);

            }
        });
        sidebar.add(salesiconLabel);



        

    //Search barr panell 

        


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