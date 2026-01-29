package main.view;

import java.awt.*;
import java.awt.event.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import main.database.*;
import main.model.Product;
import main.model.User;

public class MainDB_Admin extends JFrame {
    User user;
    JPanel listpanelitem;
    JPanel addmenuitem;
    JPanel addstaff;
    JPanel transactionpanel;
    JPanel salespanel;
    JComboBox<String> CategCombo;
    private List<Product> allproducts;

    public MainDB_Admin(User user) {
        this.user = user;
       
   setTitle("Admin Dashboard");
   setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


   this.setUndecorated(true); 
   this.setResizable(false); 


   this.setExtendedState(JFrame.MAXIMIZED_BOTH); 


 allproducts = DatabaseManager.getAllProducts();

  Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
  int CW = screenSize.width;
  int CH = screenSize.height;

  setSize(CW, CH); 
  setLocationRelativeTo(null);
  this.setVisible(true);


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
        listpanelitem.setBackground(Color.MAGENTA);
        listpanelitem.setBounds(199, 140, 1210, 700);
        listpanelitem.setOpaque(false);
        listpanelitem.setVisible(true);

      
       JPanel head = new JPanel(null) {
    
    private Image listBg = new ImageIcon("src/main/image/itemlist_txt.png").getImage();

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (listBg != null) {
            g.drawImage(listBg, 0, 0, getWidth(), getHeight(), this);
        }
    }
};

    head.setBounds(20, 0, 300, 80);
    head.setOpaque(true); 
    listpanelitem.add(head);


        String[] columnNames = {"", "", "", ""};
        Object[][] data = {};

        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Makes the table read-only
            }
        };

        for(Product p : allproducts){
            Object[] rowData = {p.getName(), p.getPrice(), p.getCategory(), p.getStock()};
            model.addRow(rowData);
        }

        JTable table = new JTable(model);
       table.setRowHeight(40);
        table.setFont(new Font("SansSerif", Font.PLAIN, 24));
        table.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 24));
        table.getTableHeader().setBackground(Color.WHITE); 
        table.getTableHeader().setForeground(Color.WHITE);

     
        JScrollPane scrollPane = new JScrollPane(table);
     
        scrollPane.setBounds(30, 169, 1110, 700); 
        scrollPane.setBorder(BorderFactory.createEmptyBorder()); // Keeps it clean

        // 4. Add to the panel
        listpanelitem.add(scrollPane);

        canvas.add(listpanelitem);

          //searchbarr
          
           JPanel sbarr = new JPanel(null);
          sbarr.setBackground(Color.BLUE); 
          sbarr.setOpaque(false);
          sbarr.setBounds(897, 20, 380, 60);
          listpanelitem.add(sbarr);

JTextField searchField1 = new JTextField() {
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
            g2.setColor(new Color(180, 180, 180)); 
            g2.setFont(new Font("SansSerif", Font.ITALIC, 22));
            FontMetrics fm = g2.getFontMetrics();
            int y = (getHeight() + fm.getAscent() - fm.getDescent()) / 2;
            g2.drawString("Search here", 15, y); 
            g2.dispose();
        }
    }
};

   searchField1.setOpaque(false);
   searchField1.setBorder(new javax.swing.border.EmptyBorder(0, 15, 0, 10)); 
   searchField1.setForeground(Color.BLACK); 
   searchField1.setFont(new Font("SansSerif", Font.PLAIN, 18));
   searchField1.setFocusable(false); 
   searchField1.setBounds(0, 0, 380, 60); 

   searchField1.setCaretColor(new Color(0, 0, 0, 0)); 
   searchField1.getCaret().setVisible(false);

  searchField1.addMouseListener(new MouseAdapter() {
    @Override
    public void mousePressed(MouseEvent e) {
        searchField1.setFocusable(true);
        searchField1.requestFocusInWindow();
        searchField1.setCaretColor(Color.BLACK); 
        searchField1.getCaret().setVisible(true); 
        searchField1.getCaret().setBlinkRate(500); 
    }
});

   searchField1.addFocusListener(new FocusAdapter() {
    @Override
    public void focusLost(FocusEvent e) {
        if (searchField1.getText().isEmpty()) {
            searchField1.setCaretColor(new Color(0, 0, 0, 0)); 
            searchField1.getCaret().setVisible(false);
            searchField1.setFocusable(false);
        }
    }
});

  searchField1.addKeyListener(new KeyAdapter() {
    @Override
    public void keyReleased(KeyEvent e) {
        String query = searchField1.getText().toLowerCase();
    }
});

   sbarr.add(searchField1);

                

        //ADD MENU PANEL
        addmenuitem = new JPanel(null) {
        private Image addMenuBg = new ImageIcon("src/main/image/AddBtnBG.png").getImage();

        @Override
         protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (addMenuBg != null) {
            g.drawImage(addMenuBg, 0, 0, getWidth(), getHeight(), this);
        }
    }
};
       
        addmenuitem.setBounds(197, 125, 1210, 789);
        addmenuitem.setOpaque(false);
        addmenuitem.setVisible(false);

           JPanel head1 = new JPanel(null) {
    
    private Image listBg = new ImageIcon("src/main/image/meal_txt.png").getImage();

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (listBg != null) {
            g.drawImage(listBg, 0, 0, getWidth(), getHeight(), this);
        }
    }
};

    head1.setBounds(40, 20, 300, 60);
    head.setOpaque(true); 
    addmenuitem.add(head1);


    //search  panel 

           JPanel sbar = new JPanel(null) {
    private Image listBg = new ImageIcon("src/main/image/searchbar.png").getImage();

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (listBg != null) {
            
            g.drawImage(listBg, 0, 0, getWidth(), getHeight(), this);
        }
    }
};

  sbar.setBounds(800, 489, 387, 69);
  sbar.setOpaque(false); 
  addmenuitem.add(sbar);


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
            g2.setFont(new Font("SansSerif", Font.ITALIC, 18)); 
            
            FontMetrics fm = g2.getFontMetrics();
            int y = (getHeight() + fm.getAscent() - fm.getDescent()) / 2;
            
            
            g2.drawString("Search here", 80, y); 
            g2.dispose();
        }
    }
};


  searchField.setOpaque(false);

  searchField.setBorder(new javax.swing.border.EmptyBorder(0, 80, 0, 10)); 
  searchField.setForeground(Color.BLACK);
  searchField.setFont(new Font("SansSerif", Font.PLAIN, 18));
  searchField.setFocusable(false); 
  searchField.setBounds(0, 0, 387, 69); 


    searchField.setCaretColor(new Color(0, 0, 0, 0)); 
    searchField.getCaret().setVisible(false);       


   searchField.addMouseListener(new MouseAdapter() {
    @Override
    public void mousePressed(MouseEvent e) {
        searchField.setFocusable(true);
        searchField.requestFocusInWindow();
      
        searchField.setCaretColor(Color.BLACK); 
        searchField.getCaret().setVisible(true); 
        searchField.getCaret().setBlinkRate(500); 
    }
});


   searchField.addFocusListener(new FocusAdapter() {
    @Override
    public void focusLost(FocusEvent e) {
        if (searchField.getText().isEmpty()) {
            searchField.setCaretColor(new Color(0, 0, 0, 0)); 
            searchField.getCaret().setVisible(false);
            searchField.setFocusable(false);
        }
    }
});


  searchField.addKeyListener(new KeyAdapter() {
    @Override
    public void keyReleased(KeyEvent e) {
        
    }
});


   sbar.add(searchField);



      
        JLabel ItemLabel = new JLabel("Item Name");
        ItemLabel.setFont(new Font("Arial", Font.BOLD, 20));
        ItemLabel.setForeground(Color.BLACK);
        ItemLabel.setBounds(100, 100, 150, 30);
        addmenuitem.add(ItemLabel); 

        JTextField Itemname = new JTextField();
        Itemname.setBounds(100, 130, 300, 48); 
        Itemname.setFont(new Font("Arial", Font.PLAIN, 18));
        Itemname.setBorder(BorderFactory.createCompoundBorder(
        BorderFactory.createLineBorder(Color.BLUE, 1), 
        BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        addmenuitem.add(Itemname);
        Itemname.setOpaque(true);

        JLabel ItemstockLabel = new JLabel("Item Stock");
        ItemstockLabel.setFont(new Font("Arial", Font.BOLD, 20));
        ItemstockLabel.setForeground(Color.BLACK);
        ItemstockLabel.setBounds(100, 230, 150, 30);
        addmenuitem.add(ItemstockLabel);    

        JTextField Itemstock = new JTextField();
        Itemstock.setBounds(100, 260, 300, 48); 
        Itemstock.setFont(new Font("Arial", Font.PLAIN, 18));
        Itemstock.setBorder(BorderFactory.createCompoundBorder(
        BorderFactory.createLineBorder(Color.BLUE, 1), 
        BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        addmenuitem.add(Itemstock);
        Itemstock.setOpaque(true);

        JLabel ItempriceLabel = new JLabel("Item Price");
        ItempriceLabel.setFont(new Font("Arial", Font.BOLD, 20));
        ItempriceLabel.setForeground(Color.BLACK);
        ItempriceLabel.setBounds(670, 100, 150, 30);
        addmenuitem.add(ItempriceLabel); 

        JTextField Itemprice = new JTextField();
        Itemprice.setBounds(670, 130, 300, 48); 
        Itemprice.setFont(new Font("Arial", Font.PLAIN, 18));
        Itemprice.setBorder(BorderFactory.createCompoundBorder(
        BorderFactory.createLineBorder(Color.BLUE, 1), 
        BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        addmenuitem.add(Itemprice);
        Itemprice.setOpaque(true);

        JLabel ItemCatLabel = new JLabel("Item Category");
        ItemCatLabel.setFont(new Font("Arial", Font.BOLD, 20));
        ItemCatLabel.setForeground(Color.BLACK);
        ItemCatLabel.setBounds(670, 230, 200, 30); 
        addmenuitem.add(ItemCatLabel); 

        String[] ItemCateg = {"Meal", "Drink", "Snack"}; 
        CategCombo = new JComboBox<>(ItemCateg);
        CategCombo.setFont(new Font("Arial", Font.PLAIN, 17));
        CategCombo.setBounds(670, 260, 300, 48);
        CategCombo.setForeground(Color.BLACK);
        addmenuitem.add(CategCombo);
        
        JLabel ItemLabelbottom = new JLabel("Item Name");
        ItemLabelbottom.setFont(new Font("Arial", Font.BOLD, 20));
        ItemLabelbottom.setForeground(Color.WHITE);
        ItemLabelbottom.setBounds(162, 509, 150, 30);
        addmenuitem.add(ItemLabelbottom); 

        JLabel priceLabelbottom = new JLabel("Price");
        priceLabelbottom.setFont(new Font("Arial", Font.BOLD, 20));
        priceLabelbottom.setForeground(Color.WHITE);
        priceLabelbottom.setBounds(340, 509, 150, 30);
        addmenuitem.add(priceLabelbottom); 

        JLabel categLabelbottom = new JLabel("Category");
        categLabelbottom.setFont(new Font("Arial", Font.BOLD, 20));
        categLabelbottom.setForeground(Color.WHITE);
        categLabelbottom.setBounds(480, 509, 150, 30);
        addmenuitem.add(categLabelbottom); 

        JLabel stockLabelbottom = new JLabel("Stock");
        stockLabelbottom.setFont(new Font("Arial", Font.BOLD, 20));
        stockLabelbottom.setForeground(Color.WHITE);
        stockLabelbottom.setBounds(640, 509, 150, 30);
        addmenuitem.add(stockLabelbottom); 

        JButton cnfm = new JButton("Add");
        cnfm.setSize(150, 60);
        cnfm.setFont(new Font("Arial", Font.BOLD, 17));
        cnfm.setBackground(new Color(165, 215, 155));
        cnfm.setForeground(Color.WHITE);
        cnfm.setBounds(1000, 260, 100, 50);
        cnfm.setFocusPainted(false);
        addmenuitem.add(cnfm);

        //KULANG PA NG SCROLL BAR SA TABI NG MGA LABELS TO SHOW ADDED ITEMS - que

        //important part for adding panel to canvas
        canvas.add(addmenuitem);


        //ADD STAFF PANEL

        //kulang pa since nakita ko rin na may scrollbar, kapag gagawin ko baka mag ka conflict -que

            addstaff = new JPanel(null) {
        private Image addStaffBg = new ImageIcon("src/main/image/ActStaffLst.png").getImage();

        @Override
         protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (addStaffBg != null) {
            g.drawImage(addStaffBg, 0, 0, getWidth(), getHeight(), this);
        }
    }
};
        addstaff.setBounds(198, 130, 1207, 780);
        addstaff.setOpaque(false);
        addstaff.setVisible(false);


    JPanel head2 = new JPanel(null) {
    
    private Image listBg = new ImageIcon("src/main/image/staff_txt.png").getImage();

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (listBg != null) {
            g.drawImage(listBg, 0, 0, getWidth(), getHeight(), this);
        }
    }
};

    head2.setBounds(20, 26, 300, 60);
    head2.setOpaque(true); 
    addstaff.add(head2);


     //searchbarr
          
           JPanel sbarrr = new JPanel(null);
          sbarrr.setBackground(Color.BLUE); 
          sbarrr.setOpaque(false);
          sbarrr.setBounds(890, 28, 380, 60);
          addstaff.add(sbarrr);

   JTextField searchField2 = new JTextField() {
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
            g2.setColor(new Color(180, 180, 180)); 
            g2.setFont(new Font("SansSerif", Font.ITALIC, 22));
            FontMetrics fm = g2.getFontMetrics();
            int y = (getHeight() + fm.getAscent() - fm.getDescent()) / 2;
            g2.drawString("Search here", 15, y); 
            g2.dispose();
        }
    }
};

   searchField2.setOpaque(false);
   searchField2.setBorder(new javax.swing.border.EmptyBorder(0, 15, 0, 10)); 
   searchField2.setForeground(Color.BLACK); 
   searchField2.setFont(new Font("SansSerif", Font.PLAIN, 18));
   searchField2.setFocusable(false); 
   searchField2.setBounds(0, 0, 380, 60); 

   searchField2.setCaretColor(new Color(0, 0, 0, 0)); 
   searchField2.getCaret().setVisible(false);

  searchField2.addMouseListener(new MouseAdapter() {
    @Override
    public void mousePressed(MouseEvent e) {
        searchField2.setFocusable(true);
        searchField2.requestFocusInWindow();
        searchField2.setCaretColor(Color.BLACK); 
        searchField2.getCaret().setVisible(true); 
        searchField2.getCaret().setBlinkRate(500); 
    }
});

   searchField2.addFocusListener(new FocusAdapter() {
    @Override
    public void focusLost(FocusEvent e) {
        if (searchField2.getText().isEmpty()) {
            searchField2.setCaretColor(new Color(0, 0, 0, 0)); 
            searchField2.getCaret().setVisible(false);
            searchField2.setFocusable(false);
        }
    }
});

  searchField2.addKeyListener(new KeyAdapter() {
    @Override
    public void keyReleased(KeyEvent e) {
        String query = searchField1.getText().toLowerCase();
    }
});

   sbarrr.add(searchField2);

        JLabel usernamelLabel = new JLabel("Username");
        usernamelLabel.setFont(new Font("Arial", Font.BOLD, 23));
        usernamelLabel.setForeground(Color.WHITE);
        usernamelLabel.setBounds(117, 140, 150, 30);
        addstaff.add(usernamelLabel); 

        JLabel firstnamelLabel = new JLabel("First Name");
        firstnamelLabel.setFont(new Font("Arial", Font.BOLD, 23));
        firstnamelLabel.setForeground(Color.WHITE);
        firstnamelLabel.setBounds(350, 140, 150, 30);
        addstaff.add(firstnamelLabel); 

        JLabel lastnamelLabel = new JLabel("Last Name");
        lastnamelLabel.setFont(new Font("Arial", Font.BOLD, 23));
        lastnamelLabel.setForeground(Color.WHITE);
        lastnamelLabel.setBounds(620, 140, 150, 30);
        addstaff.add(lastnamelLabel); 

        JLabel genderlLabel = new JLabel("Gender");
        genderlLabel.setFont(new Font("Arial", Font.BOLD, 23));
        genderlLabel.setForeground(Color.WHITE);
        genderlLabel.setBounds(870, 140, 150, 30);
        addstaff.add(genderlLabel); 

        JButton bck = new JButton("Delete");
        bck.setSize(100, 40);
        bck.setBackground(new Color(220, 120, 100));
        bck.setForeground(Color.WHITE);
        bck.setFont(new Font("Arial", Font.BOLD, 15));
        bck.setBounds(1115, 184, 100, 40);
        bck.setFocusPainted(false);
        addstaff.add(bck);
        
        JButton ok = new JButton("Confirm");
        ok.setSize(400, 40);
        ok.setBackground(new Color(165, 215, 155));
        ok.setForeground(Color.WHITE);
        ok.setFont(new Font("Arial", Font.BOLD, 15));
        ok.setBounds(990, 184, 100, 40);
        ok.setFocusPainted(false);
        addstaff.add(ok);

        canvas.add(addstaff);

        // TRANSACTION PANEL

       transactionpanel = new JPanel(null) {
        private Image trImage = new ImageIcon("src/main/image/TranRecBTN.png").getImage();

        @Override
         protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (trImage != null) {
            g.drawImage(trImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
};
        transactionpanel.setBounds(200, 134, 1207, 700);
        transactionpanel.setOpaque(false);
        transactionpanel.setVisible(false);

          JPanel head3 = new JPanel(null) {
    
    private Image listBg = new ImageIcon("src/main/image/rec_txt.png").getImage();

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (listBg != null) {
            g.drawImage(listBg, 0, 0, getWidth(), getHeight(), this);
        }
    }
};

    head3.setBounds(20, 20, 360, 70);
    head3.setOpaque(true); 
    transactionpanel.add(head3);



      //searchbarr
          
           JPanel sbarrrr = new JPanel(null);
          sbarrrr.setBackground(Color.BLUE); 
          sbarrrr.setOpaque(false);
          sbarrrr.setBounds(890, 20, 380, 60);
          transactionpanel.add(sbarrrr);

   JTextField searchField3 = new JTextField() {
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
            g2.setColor(new Color(180, 180, 180)); 
            g2.setFont(new Font("SansSerif", Font.ITALIC, 22));
            FontMetrics fm = g2.getFontMetrics();
            int y = (getHeight() + fm.getAscent() - fm.getDescent()) / 2;
            g2.drawString("Search here", 15, y); 
            g2.dispose();
        }
    }
};

   searchField3.setOpaque(false);
   searchField3.setBorder(new javax.swing.border.EmptyBorder(0, 15, 0, 10)); 
   searchField3.setForeground(Color.BLACK); 
   searchField3.setFont(new Font("SansSerif", Font.PLAIN, 18));
   searchField3.setFocusable(false); 
   searchField3.setBounds(0, 0, 380, 60); 

   searchField3.setCaretColor(new Color(0, 0, 0, 0)); 
   searchField3.getCaret().setVisible(false);

  searchField3.addMouseListener(new MouseAdapter() {
    @Override
    public void mousePressed(MouseEvent e) {
        searchField3.setFocusable(true);
        searchField3.requestFocusInWindow();
        searchField3.setCaretColor(Color.BLACK); 
        searchField3.getCaret().setVisible(true); 
        searchField3.getCaret().setBlinkRate(500); 
    }
});

   searchField3.addFocusListener(new FocusAdapter() {
    @Override
    public void focusLost(FocusEvent e) {
        if (searchField3.getText().isEmpty()) {
            searchField3.setCaretColor(new Color(0, 0, 0, 0)); 
            searchField3.getCaret().setVisible(false);
            searchField3.setFocusable(false);
        }
    }
});

  searchField3.addKeyListener(new KeyAdapter() {
    @Override
    public void keyReleased(KeyEvent e) {
        String query = searchField1.getText().toLowerCase();
    }
});

   sbarrrr.add(searchField3);




         JLabel usernamelLabel1 = new JLabel("Staff");
        usernamelLabel1.setFont(new Font("Arial", Font.BOLD, 23));
        usernamelLabel1.setForeground(Color.WHITE);
        usernamelLabel1.setBounds(117, 125, 150, 30);
        transactionpanel.add(usernamelLabel1); 

        JLabel firstnamelLabel1 = new JLabel("Product");
        firstnamelLabel1.setFont(new Font("Arial", Font.BOLD, 23));
        firstnamelLabel1.setForeground(Color.WHITE);
        firstnamelLabel1.setBounds(350, 125, 150, 30);
        transactionpanel.add(firstnamelLabel1); 

        JLabel lastnamelLabel1 = new JLabel("Qty");
        lastnamelLabel1.setFont(new Font("Arial", Font.BOLD, 23));
        lastnamelLabel1.setForeground(Color.WHITE);
        lastnamelLabel1.setBounds(570, 125, 150, 30);
        transactionpanel.add(lastnamelLabel1); 

        JLabel genderlLabel1 = new JLabel("Price");
        genderlLabel1.setFont(new Font("Arial", Font.BOLD, 23));
        genderlLabel1.setForeground(Color.WHITE);
        genderlLabel1.setBounds(670, 125, 150, 30);
        transactionpanel.add(genderlLabel1); 

           JLabel tot = new JLabel("Total");
        tot.setFont(new Font("Arial", Font.BOLD, 23));
        tot.setForeground(Color.WHITE);
        tot.setBounds(800, 125, 150, 30);
        transactionpanel.add(tot); 


          JLabel date = new JLabel("Date");
        date.setFont(new Font("Arial", Font.BOLD, 23));
        date.setForeground(Color.WHITE);
        date.setBounds(980, 125, 150, 30);
        transactionpanel.add(date); 

        

        canvas.add(transactionpanel);

        // SALES PANEL
       salespanel = new JPanel(null) {
        private Image sImage = new ImageIcon("src/main/image/SalesRepBTN.png").getImage();

        @Override
         protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (sImage != null) {
            g.drawImage(sImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
};
        salespanel.setBounds(196, 134, 1211, 780);
        salespanel.setOpaque(false);
        salespanel.setVisible(false);

          JPanel head4 = new JPanel(null) {
    
    private Image listBg = new ImageIcon("src/main/image/sales_txt.png").getImage();

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (listBg != null) {
            g.drawImage(listBg, 0, 0, getWidth(), getHeight(), this);
        }
    }
};

    head4.setBounds(20, 20, 290, 60);
    head4.setOpaque(true); 
    salespanel.add(head4);

        JLabel tranlabel = new JLabel("Transaction");
        tranlabel.setFont(new Font("Arial", Font.BOLD, 20));
        tranlabel.setForeground(Color.BLACK);
        tranlabel.setBounds(85, 105, 150, 30);
        salespanel.add(tranlabel);

        JLabel onlinestafflabel = new JLabel("Online Staff");
        onlinestafflabel.setFont(new Font("Arial", Font.BOLD, 20));
        onlinestafflabel.setForeground(Color.BLACK);
        onlinestafflabel.setBounds(404, 105, 150, 30);
        salespanel.add(onlinestafflabel);

        JLabel totalsaleslabel = new JLabel("Total Sales");
        totalsaleslabel.setFont(new Font("Arial", Font.BOLD, 20));
        totalsaleslabel.setForeground(Color.BLACK);
        totalsaleslabel.setBounds(710, 105, 150, 30);
        salespanel.add(totalsaleslabel);

        JLabel bestsellinglabel = new JLabel("Best Selling");
        bestsellinglabel.setFont(new Font("Arial", Font.BOLD, 20));
        bestsellinglabel.setForeground(Color.BLACK);
        bestsellinglabel.setBounds(85, 300, 150, 30);
        salespanel.add(bestsellinglabel);

        JLabel saleschartlabel = new JLabel("Sales Chart");
        saleschartlabel.setFont(new Font("Arial", Font.BOLD, 20));
        saleschartlabel.setForeground(Color.BLACK);
        saleschartlabel.setBounds(628, 300, 150, 30);
        salespanel.add(saleschartlabel);
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
                //JOptionPane.showMessageDialog(null, "This button has been clicked");
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
                //JOptionPane.showMessageDialog(null, "This button has been clicked");
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
                //JOptionPane.showMessageDialog(null, "This button has been clicked");
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
                //JOptionPane.showMessageDialog(null, "This button has been clicked");
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
                //JOptionPane.showMessageDialog(null, "This button has been clicked");
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