package main.view;

import java.awt.*;
import java.awt.event.*;
import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
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
    JComboBox<String> CategCombo, MeasCombo;

    private List<Product> allproducts;
    private List<User> allusers;

    public MainDB_Admin(User user) {
        this.user = user;
       
   setTitle("Admin Dashboard");
   setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


   this.setUndecorated(true); 
   this.setResizable(false); 


   this.setExtendedState(JFrame.MAXIMIZED_BOTH); 


 allproducts = DatabaseManager.getAllProducts();
 allusers = DatabaseManager.getAllUsers();


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

        // #region ITEM LIST SETUP

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
        listpanelitem.setBounds(199, 139, 1208, 700);
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

        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        table.setRowSorter(sorter);
     
        JScrollPane scrollPane = new JScrollPane(table);
     
        scrollPane.setBounds(50, 169, 1030, 480); 
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
        
        if (query.trim().length() == 0) {
            // If search is empty, show everything
            sorter.setRowFilter(null);
        } else {
            // Filter by the first column (Item Name). 
            // Use "(?i)" for case-insensitive searching
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + query, 0));
        }
    }
});

    JButton itemListPDF = new JButton("Export PDF");
    itemListPDF.setFont(new Font("Arial", Font.BOLD, 14));
    itemListPDF.setBackground(new Color(165, 215, 155));
    itemListPDF.setForeground(Color.WHITE);
    itemListPDF.setBounds(350, 20, 120, 40);
    itemListPDF.setFocusPainted(false);
    itemListPDF.setCursor(new Cursor(Cursor.HAND_CURSOR));
    itemListPDF.addActionListener(e -> {

        JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Save Item List as PDF");
            fileChooser.setSelectedFile(new java.io.File("ItemList.pdf"));

            int userSelection = fileChooser.showSaveDialog(null);

            if (userSelection == JFileChooser.APPROVE_OPTION) {
                //JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Save Item List as PDF");
            
            // Set a default file name
            fileChooser.setSelectedFile(new java.io.File("ItemList.pdf"));

            //int userSelection = fileChooser.showSaveDialog(null);

            if (userSelection == JFileChooser.APPROVE_OPTION) {
                java.io.File fileToSave = fileChooser.getSelectedFile();
                
                try {
                    
                    MessageFormat headerr = new MessageFormat("NUCMS - Item List");
                    MessageFormat footer = new MessageFormat("Page {0,number,integer}");
                    
                    boolean complete = table.print(JTable.PrintMode.FIT_WIDTH, headerr, footer);
                    
                    if (complete) {
                        JOptionPane.showMessageDialog(null, "Report generated successfully!");
                    }
                    
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error generating report: " + ex.getMessage());
                }
            }
            }
    });
        
    listpanelitem.add(itemListPDF);


   sbarr.add(searchField1);

   // #endregion

        // #region ADD MENU SETUP

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
       
        addmenuitem.setBounds(197, 129, 1210, 807);
        //addmenuitem.setBounds(197, 15, 1210, 789);
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

  sbar.setBounds(800, 500, 387, 69);
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
        Itemstock.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                // If the character isn't a digit, a period, or a backspace/delete...
                if (!Character.isDigit(c) && c != '.' && c != KeyEvent.VK_BACK_SPACE) {
                    e.consume(); // Destroy the event so the character doesn't appear
                }

                // Prevent multiple decimal points
                if (c == '.' && Itemstock.getText().contains(".")) {
                    e.consume();
                }
            }
        });

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

        Itemprice.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                // If the character isn't a digit, a period, or a backspace/delete...
                if (!Character.isDigit(c) && c != '.' && c != KeyEvent.VK_BACK_SPACE) {
                    e.consume(); // Destroy the event so the character doesn't appear
                }

                // Prevent multiple decimal points
                if (c == '.' && Itemprice.getText().contains(".")) {
                    e.consume();
                }
            }
        });

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
        cnfm.setCursor(new Cursor(Cursor.HAND_CURSOR));

         JLabel ItemMeasLabel = new JLabel("Item Measurement");
        ItemMeasLabel.setFont(new Font("Arial", Font.BOLD, 20));
        ItemMeasLabel.setForeground(Color.BLACK);
        ItemMeasLabel.setBounds(410, 330, 200, 30); 
        addmenuitem.add(ItemMeasLabel); 

        String[] ItemMeas = {"Per Piece", "Per Pack"}; 
        MeasCombo = new JComboBox<>(ItemMeas);
        MeasCombo.setFont(new Font("Arial", Font.PLAIN, 17));
        MeasCombo.setBounds(410, 360, 250, 48);
        MeasCombo.setForeground(Color.BLACK);
        addmenuitem.add(MeasCombo);
        
        

        String[] columnname = {"", "", "", ""};
        Object[][] datalist = {};

        DefaultTableModel modelbottom = new DefaultTableModel(datalist, columnname) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Makes the table read-only
            }
        };

        for(Product p : allproducts){
            Object[] rowData = {p.getName(), p.getPrice(), p.getCategory(), p.getStock()};
            modelbottom.addRow(rowData);
        }

        JTable tablebottom = new JTable(modelbottom);
        tablebottom.setRowHeight(30);
        tablebottom.setFont(new Font("SansSerif", Font.PLAIN, 18));
        tablebottom.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 18));
        tablebottom.getTableHeader().setBackground(Color.WHITE);
        tablebottom.getTableHeader().setForeground(Color.WHITE);

        TableRowSorter<DefaultTableModel> sorter2 = new TableRowSorter<>(modelbottom);
        tablebottom.setRowSorter(sorter2);
     
        JScrollPane scrollPanebottom = new JScrollPane(tablebottom);
        scrollPanebottom.setBounds(133, 564, 645, 200);
        //scrollPanebottom.setBorder(BorderFactory.createEmptyBorder()); 
        addmenuitem.add(scrollPanebottom);

        addmenuitem.add(cnfm);

        searchField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String query = searchField.getText().toLowerCase();
        
                if (query.trim().length() == 0) {
                    // If search is empty, show everything
                    sorter2.setRowFilter(null);
                } else {
                    // Filter by the first column (Item Name). 
                    // Use "(?i)" for case-insensitive searching
                    sorter2.setRowFilter(RowFilter.regexFilter("(?i)" + query, 0));
                }
            }
        });

        JButton btnDelete = new JButton("Delete Selected");
        btnDelete.setBounds(780, 700, 150, 40); // Position it next to your scrollPanebottom
        btnDelete.setBackground(new Color(255, 102, 102)); // Reddish
        btnDelete.setForeground(Color.WHITE);
        btnDelete.setFont(new Font("Arial", Font.BOLD, 14));
        addmenuitem.add(btnDelete);
        cnfm.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                String name = Itemname.getText();
                String priceText = Itemprice.getText();
                String stockText = Itemstock.getText();
                String category = (String) CategCombo.getSelectedItem();
                if (name.isEmpty() || priceText.isEmpty() || stockText.isEmpty() || category.isEmpty()) {
                    JOptionPane.showMessageDialog(addmenuitem, "Please fill in all fields.", "Input Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                double price;
                int stock;

                price = Double.parseDouble(priceText);
                stock = Integer.parseInt(stockText);
                DatabaseManager.addProduct(new Product(0, name, price, stock, category, ""));
                JOptionPane.showMessageDialog(addmenuitem, "Product added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                Itemname.setText("");
                Itemprice.setText("");
                Itemstock.setText("");
                CategCombo.setSelectedIndex(0);

                // Update the table model
                Object[] rowData = {name, price, category, stock};
                modelbottom.addRow(rowData);

                model.addRow(rowData);
            


                
            }
                
        });
        btnDelete.addActionListener(e -> {
        int selectedRow = tablebottom.getSelectedRow();
        
        if (selectedRow != -1) {
            // Convert sorter index to model index (important because of your search bar!)
            int modelRow = tablebottom.convertRowIndexToModel(selectedRow);
            String itemName = (String) modelbottom.getValueAt(modelRow, 0);
            int modelrow = table.convertRowIndexToModel(selectedRow);

            int confirm = JOptionPane.showConfirmDialog(null, 
                "Are you sure you want to delete " + itemName + "?", "Delete", JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                DatabaseManager.deleteProduct(itemName);
                modelbottom.removeRow(modelRow);
                JOptionPane.showMessageDialog(null, "Item Deleted");
                model.removeRow(modelrow);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Please select a row first!");
        }
    });

      // for add stock 

       JButton btnAdd = new JButton("Add Stock");
        btnAdd.setBounds(780, 650, 150, 40); 
        btnAdd.setBackground(new Color(165, 215, 155)); 
        btnAdd.setForeground(Color.WHITE);
        btnAdd.setFont(new Font("Arial", Font.BOLD, 14));
        addmenuitem.add(btnAdd);

        btnAdd.addMouseListener(new java.awt.event.MouseAdapter() {
           
        @Override
    public void mousePressed(MouseEvent e) {
        String name = tablebottom.getValueAt(tablebottom.getSelectedRow(), 0).toString();
        
        if (tablebottom.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(addmenuitem, "Please select an item first.", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String inputAmount = JOptionPane.showInputDialog(
            addmenuitem, 
            "Enter amount of stock to add for " + name + ":", 
            "Add Stock - NUCMS", 
            JOptionPane.QUESTION_MESSAGE
        );

        if (inputAmount != null && !inputAmount.trim().isEmpty()) {
            try {
                int additionalStock = Integer.parseInt(inputAmount.trim());
                
                if (additionalStock <= 0) {
                    JOptionPane.showMessageDialog(addmenuitem, "Please enter a positive number.", "Input Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // 1. Update Database
                DatabaseManager.addstock(name, additionalStock);
                
                // 2. Update UI Tables (modelbottom and model)
                // We loop through the model to find the item by name and update its stock column
                boolean found = false;
                for (int i = 0; i < modelbottom.getRowCount(); i++) {
                    if (modelbottom.getValueAt(i, 0).equals(name)) {
                        int currentStock = (int) modelbottom.getValueAt(i, 3);
                        modelbottom.setValueAt(currentStock + additionalStock, i, 3);
                        found = true;
                        break;
                    }
                }
                
                // Also update the main 'model' if it exists in this scope
                for (int i = 0; i < model.getRowCount(); i++) {
                    if (model.getValueAt(i, 0).equals(name)) {
                        int currentStock = (int) model.getValueAt(i, 3);
                        model.setValueAt(currentStock + additionalStock, i, 3);
                        break;
                    }
                }

                JOptionPane.showMessageDialog(addmenuitem, "Successfully added " + additionalStock + " to " + name + ".");
                
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(addmenuitem, "Invalid input. Please enter a whole number.", "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
                
        });
      

     
        canvas.add(addmenuitem);





        //ADD STAFF PANEL

        //#endregion
        //#region ADD STAFF SETUP

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
        addstaff.setBounds(198, 134, 1207, 780);
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

  
   sbarrr.add(searchField2);

        JLabel usernamelLabel = new JLabel("Username");
        usernamelLabel.setFont(new Font("Arial", Font.BOLD, 23));
        usernamelLabel.setForeground(Color.WHITE);
        usernamelLabel.setBounds(101, 140, 150, 30);
        addstaff.add(usernamelLabel); 

        JLabel firstnamelLabel = new JLabel("First Name");
        firstnamelLabel.setFont(new Font("Arial", Font.BOLD, 23));
        firstnamelLabel.setForeground(Color.WHITE);
        firstnamelLabel.setBounds(318, 140, 150, 30);
        addstaff.add(firstnamelLabel); 

        JLabel lastnamelLabel = new JLabel("Last Name");
        lastnamelLabel.setFont(new Font("Arial", Font.BOLD, 23));
        lastnamelLabel.setForeground(Color.WHITE);
        lastnamelLabel.setBounds(530, 140, 150, 30);
        addstaff.add(lastnamelLabel); 

        JLabel genderlLabel = new JLabel("Gender");
        genderlLabel.setFont(new Font("Arial", Font.BOLD, 23));
        genderlLabel.setForeground(Color.WHITE);
        genderlLabel.setBounds(753, 140, 150, 30);
        addstaff.add(genderlLabel); 

        JLabel statuslLabel = new JLabel("Status");
        statuslLabel.setFont(new Font("Arial", Font.BOLD, 23));
        statuslLabel.setForeground(Color.WHITE);
        statuslLabel.setBounds(972, 140, 150, 30);
        addstaff.add(statuslLabel); 

        //JButton bck = new JButton("Delete");
       //bck.setSize(100, 40);
        //bck.setBackground(new Color(220, 120, 100));
        //bck.setForeground(Color.WHITE);
        //bck.setFont(new Font("Arial", Font.BOLD, 15));
        //bck.setBounds(1115, 184, 100, 40);
       // bck.setFocusPainted(false);
        //addstaff.add(bck);
        
        //JButton ok = new JButton("Confirm");
        //ok.setSize(400, 40);
        //ok.setBackground(new Color(165, 215, 155));
        //ok.setForeground(Color.WHITE);
        //ok.setFont(new Font("Arial", Font.BOLD, 15));
        //ok.setBounds(990, 184, 100, 40);
        //ok.setFocusPainted(false);
        //addstaff.add(ok);

        JButton changeStatus = new JButton("Change Status");
        changeStatus.setSize(150, 40);
        changeStatus.setBackground(new Color(165, 215, 155));
        changeStatus.setForeground(Color.WHITE);
        changeStatus.setFont(new Font("Arial", Font.BOLD, 15));
        changeStatus.setBounds(990, 600, 150, 40);
        changeStatus.setFocusPainted(false);

        JButton viewDetails = new JButton("View Details");
        viewDetails.setSize(150, 40);
        viewDetails.setBackground(new Color(165, 215, 155));
        viewDetails.setForeground(Color.WHITE);
        viewDetails.setFont(new Font("Arial", Font.BOLD, 15));
        viewDetails.setBounds(820, 600, 150, 40);
        viewDetails.setFocusPainted(false);



        addstaff.add(viewDetails);



        addstaff.add(changeStatus);

        String[] usercolumnname = {"Username", "First Name", "Last Name", "Gender","Status"};
        Object[][] userdatalist = {};

        DefaultTableModel usermodel = new DefaultTableModel(userdatalist,usercolumnname) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Makes the table read-only
            }
        };

        JTable usertable = new JTable(usermodel);
        usertable.setRowHeight(30);

        usertable.setFont(new Font("SansSerif", Font.PLAIN, 18));

        usertable.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 18));

        
        //usertable.getTableHeader().setBackground(Color.WHITE);
        //usertable.getTableHeader().setForeground(Color.WHITE);

        usertable.getTableHeader().setBackground(Color.WHITE); // Background is White
        usertable.getTableHeader().setForeground(Color.BLACK); // Change this to BLACK so you can see it
        for(User u : allusers){
            if(!u.isAdmin()){
            String fullname = u.getFullname();
            String FirstName = fullname.split(" ")[0];
            String LastName = fullname.split(" ")[1];
            String status = u.isActive() ? "Active" : "Inactive";
            Object[] rowData = {u.getUsername(), FirstName, LastName, u.getgender(), status};
            usermodel.addRow(rowData);
        }
        }

        TableRowSorter<DefaultTableModel> userSorter = new TableRowSorter<>(usermodel);
        usertable.setRowSorter(userSorter);
        
        JScrollPane userscrollPane = new JScrollPane(usertable);
        userscrollPane.setBounds(40, 190, 1070, 400);
        addstaff.add(userscrollPane);

        changeStatus.addActionListener(e -> {
            int selectedRow = usertable.getSelectedRow();

            if (selectedRow >= 0) {
                int modelRow = usertable.convertRowIndexToModel(selectedRow);
                String username = (String) usermodel.getValueAt(modelRow, 0);
                User selectedUser = DatabaseManager.getUserByUsername(username);
                String status = (String) usermodel.getValueAt(modelRow, 4);
                int confirm = JOptionPane.showConfirmDialog(
            null, 
                "Would you like to change " + username + "'s status to " + 
                (status.equalsIgnoreCase("Inactive") ? "Active" : "Inactive") + "?",
                "Confirm Status Change",
                JOptionPane.YES_NO_OPTION
                );

                if (confirm != JOptionPane.YES_OPTION) {
                    return; // User cancelled the action
                }

                String confirmPassword = JOptionPane.showInputDialog(
                    null,
                    "Enter your admin password to confirm:",
                    "Admin Confirmation",
                    JOptionPane.PLAIN_MESSAGE
                );
                if (confirmPassword == null) {
                    return; // User cancelled the action
                }
                if (!confirmPassword.equals(user.getPassword())) {
                    JOptionPane.showMessageDialog(null, "Incorrect admin password.");
                    return;
                }

                if (status.equals("Active")) {
                    if (DatabaseManager.deactivateUser(username)) {
                        usermodel.setValueAt("Inactive", modelRow, 4); // Status is column 4
                        JOptionPane.showMessageDialog(null, username + " has been deactivated.");
                    }
                } else {
                    if (DatabaseManager.activateUser(username)) {
                        usermodel.setValueAt("Active", modelRow, 4); // Status is column 4
                        JOptionPane.showMessageDialog(null, username + " has been activated.");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Please select a user to change status.");
            }
                
        });

        viewDetails.addActionListener(e -> {
            int selectedRow = usertable.getSelectedRow();

            if (selectedRow >= 0) {
                int modelRow = usertable.convertRowIndexToModel(selectedRow);
                String username = (String) usermodel.getValueAt(modelRow, 0);
                User selectedUser = DatabaseManager.getUserByUsername(username);

                new StaffDetailFrame(username);
                

            } else {
                JOptionPane.showMessageDialog(null, "Please select a user to view details.");
            }


                
        });

        searchField2.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String query = searchField2.getText().toLowerCase();
                if (query.trim().length() == 0) {
                    // If search is empty, show everything
                    userSorter.setRowFilter(null);
                    

                } else {
                    // Filter by the first column (Username). 
                    // Use "(?i)" for case-insensitive searching
                    userSorter.setRowFilter(RowFilter.regexFilter("(?i)" + query, 0));
                }

            }
        });

        JButton printstafftable = new JButton("Print");
        printstafftable.setBounds(50, 600, 150, 40);
        printstafftable.setBackground(new Color(165, 215, 155));
        printstafftable.setForeground(Color.WHITE);
        printstafftable.setFont(new Font("Arial", Font.BOLD, 15));
        printstafftable.setFocusPainted(false);
        printstafftable.addActionListener(e -> {
            try {
                usertable.print();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error printing: " + ex.getMessage());
            }
        });

        JButton downloadstaffpdf = new JButton("Download PDF");
        downloadstaffpdf.setBounds(250, 600, 150, 40);
        downloadstaffpdf.setBackground(new Color(165, 215, 155));
        downloadstaffpdf.setForeground(Color.WHITE);
        downloadstaffpdf.setFont(new Font("Arial", Font.BOLD, 15));
        downloadstaffpdf.setFocusPainted(false);
        downloadstaffpdf.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Save Staff List as PDF");
            fileChooser.setSelectedFile(new java.io.File("StaffList.pdf"));

            int userSelection = fileChooser.showSaveDialog(null);

            if (userSelection == JFileChooser.APPROVE_OPTION) {
                //JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Save Staff Report as PDF");
            
            // Set a default file name
            fileChooser.setSelectedFile(new java.io.File("Staff_Report.pdf"));

            //int userSelection = fileChooser.showSaveDialog(null);

            if (userSelection == JFileChooser.APPROVE_OPTION) {
                java.io.File fileToSave = fileChooser.getSelectedFile();
                
                try {
                    
                    MessageFormat headerr = new MessageFormat("NUCMS - Staff Management Report");
                    MessageFormat footer = new MessageFormat("Page {0,number,integer}");
                    
                    boolean complete = usertable.print(JTable.PrintMode.FIT_WIDTH, headerr, footer);
                    
                    if (complete) {
                        JOptionPane.showMessageDialog(null, "Report generated successfully!");
                    }
                    
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error generating report: " + ex.getMessage());
                }
            }
            }
    });
        
        addstaff.add(downloadstaffpdf);
        addstaff.add(printstafftable);



        


        canvas.add(addstaff);


    //#endregion

    //#region Transaction PANEL

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
        transactionpanel.setBounds(200, 134, 1207, 780);
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

        String[] transactioncolumnname = {"", "", "", "", "", ""};
        Object[][] transactiondatalist = {};

        DefaultTableModel transactionmodel = new DefaultTableModel(transactiondatalist, transactioncolumnname) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Makes the table read-only
            }
        };


       // 2. Fetch the data from the database
        List<Object[]> allTransactions = DatabaseManager.getFullSalesReport();

        // 3. Populate the model with the transaction data
        for (Object[] row : allTransactions) {
            transactionmodel.addRow(row);
        }

        JTable transactiontable = new JTable(transactionmodel);
        transactiontable.setRowHeight(30);
        transactiontable.setFont(new Font("SansSerif", Font.PLAIN, 18));
        transactiontable.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 18));
        transactiontable.getTableHeader().setBackground(Color.WHITE);
        transactiontable.getTableHeader().setForeground(Color.WHITE);
        TableRowSorter<DefaultTableModel> sorter3 = new TableRowSorter<>(transactionmodel);
        transactiontable.setRowSorter(sorter3);
     
        JScrollPane transscrollPane = new JScrollPane(transactiontable);
        transscrollPane.setBounds(50, 190, 1030, 480);
        //scrollPanebottom.setBorder(BorderFactory.createEmptyBorder()); 
        transactionpanel.add(transscrollPane);

  


    



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
            //int y = (getHeight() + fm.getAscent() - fm.getDescent()) / 2;
            g2.drawString("Search here", 15,43); 
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
        usernamelLabel1.setBounds(70, 137, 150, 30);
        transactionpanel.add(usernamelLabel1); 

        JLabel firstnamelLabel1 = new JLabel("Product");
        firstnamelLabel1.setFont(new Font("Arial", Font.BOLD, 23));
        firstnamelLabel1.setForeground(Color.WHITE);
        firstnamelLabel1.setBounds(219, 137, 150, 30);
        transactionpanel.add(firstnamelLabel1); 

        JLabel lastnamelLabel1 = new JLabel("Qty");
        lastnamelLabel1.setFont(new Font("Arial", Font.BOLD, 23));
        lastnamelLabel1.setForeground(Color.WHITE);
        lastnamelLabel1.setBounds(398, 137, 150, 30);
        transactionpanel.add(lastnamelLabel1); 

        JLabel genderlLabel1 = new JLabel("Price");
        genderlLabel1.setFont(new Font("Arial", Font.BOLD, 23));
        genderlLabel1.setForeground(Color.WHITE);
        genderlLabel1.setBounds(560, 137, 150, 30);
        transactionpanel.add(genderlLabel1); 

           JLabel tot = new JLabel("Total");
        tot.setFont(new Font("Arial", Font.BOLD, 23));
        tot.setForeground(Color.WHITE);
        tot.setBounds(723, 137, 137, 30);
        transactionpanel.add(tot); 


          JLabel date = new JLabel("Date/Time");
        date.setFont(new Font("Arial", Font.BOLD, 23));
        date.setForeground(Color.WHITE);
        date.setBounds(889, 137, 150, 30);
        transactionpanel.add(date); 

        JButton downloadtransactionasPDF = new JButton("Download PDF");
        downloadtransactionasPDF.setBounds(50, 700, 150, 40);
        downloadtransactionasPDF.setBackground(new Color(165, 215, 155));
        downloadtransactionasPDF.setForeground(Color.WHITE);
        
        downloadtransactionasPDF.setFont(new Font("Arial", Font.BOLD, 15));
        downloadtransactionasPDF.setFocusPainted(false);

        downloadtransactionasPDF.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Save Transactions as PDF");
            fileChooser.setSelectedFile(new java.io.File("Transactions.pdf"));

            int userSelection = fileChooser.showSaveDialog(null);

            if (userSelection == JFileChooser.APPROVE_OPTION) {
                java.io.File fileToSave = fileChooser.getSelectedFile();
                
                try {
                    
                    MessageFormat headerr = new MessageFormat("NUCMS - Transaction Report");
                    MessageFormat footer = new MessageFormat("Page {0,number,integer}");
                    
                    boolean complete = transactiontable.print(JTable.PrintMode.FIT_WIDTH, headerr, footer);
                    
                    if (complete) {
                        JOptionPane.showMessageDialog(null, "Report generated successfully!");
                    }
                    
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error generating report: " + ex.getMessage());
                }
            }
        });
        transactionpanel.add(downloadtransactionasPDF);





        canvas.add(transactionpanel);
        // END OF TRANSACTION PANEL
        // START OF SALES PANEL
        ////#endregion
//#region SALES PANEL
        // SALES PANEL
       salespanel = new JPanel(null) {
        private Image sImage = new ImageIcon("src/main/image/salesrep.png").getImage();

        @Override
         protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (sImage != null) {
            g.drawImage(sImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
};
        salespanel.setBounds(196, 129, 1211, 807);
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

        JLabel tranlabel = new JLabel("Active Staff");
        tranlabel.setFont(new Font("Arial", Font.BOLD, 25));
        tranlabel.setForeground(Color.BLACK);
        tranlabel.setBounds(85, 126, 300, 30);
        salespanel.add(tranlabel);

        JLabel totalActivestaff = new JLabel(String.valueOf(DatabaseManager.numberofactivestaff()));
        totalActivestaff.setFont(new Font("Arial", Font.BOLD, 25));
        totalActivestaff.setForeground(Color.BLACK);
        totalActivestaff.setBounds(85, 166, 300, 30);
        salespanel.add(totalActivestaff);

        JButton viewsalesreport = new JButton("View Report");
        viewsalesreport.setSize(100, 50);
        viewsalesreport.setBackground(new Color(165, 215, 155));
        viewsalesreport.setForeground(Color.WHITE);
        viewsalesreport.setFont(new Font("Arial", Font.BOLD, 10));
        viewsalesreport.setBounds(220, 230, 100, 30);
        viewsalesreport.setFocusPainted(false);
        salespanel.add(viewsalesreport);

        viewsalesreport.addActionListener(e -> {
            new StaffSalesreport();
        });
            





        JLabel onlinestafflabel = new JLabel("Inactive Staff");
        onlinestafflabel.setFont(new Font("Arial", Font.BOLD, 25));
        onlinestafflabel.setForeground(Color.BLACK);
        onlinestafflabel.setBounds(398, 126, 300, 30);
        salespanel.add(onlinestafflabel);

        JLabel totalinactivestaff = new JLabel(String.valueOf(DatabaseManager.numberofinactivestaff()));
        totalinactivestaff.setFont(new Font("Arial", Font.BOLD, 25));
        totalinactivestaff.setForeground(Color.BLACK);
        totalinactivestaff.setBounds(398, 166, 300, 30);
        salespanel.add(totalinactivestaff);

        JLabel totalsaleslabel = new JLabel("Best Selling");
        totalsaleslabel.setFont(new Font("Arial", Font.BOLD, 30));
        totalsaleslabel.setForeground(Color.BLACK);
        totalsaleslabel.setBounds(781, 113, 300, 70);
        salespanel.add(totalsaleslabel);

        JPanel bestsellingpanel = new JPanel();
        bestsellingpanel.setLayout(new BoxLayout(bestsellingpanel, BoxLayout.Y_AXIS));
        //bestsellingpanel.setBackground(Color.WHITE);
        bestsellingpanel.setOpaque(false);

        JScrollPane scrollPanebestselling = new JScrollPane(bestsellingpanel);
        scrollPanebestselling.setBounds(725, 200, 280, 1000); // Matches your layout
        scrollPanebestselling.setBorder(null); // Optional: makes it look cleaner
        scrollPanebestselling.setOpaque(false);
        scrollPanebestselling.getViewport().setOpaque(false);

        // Fetch the Top 10
        Map<String, Integer> top10 = DatabaseManager.getTop10Bestsellers();

        int rank = 1;
for (Map.Entry<String, Integer> entry : top10.entrySet()) {
    JPanel row = new JPanel(new BorderLayout());
    
    // --- STEP 1: Increase Row Height ---
    // Increased height from 100 (which was likely being squashed) to a consistent 60
    row.setPreferredSize(new Dimension(280, 30)); 
    row.setMaximumSize(new Dimension(280, 30)); 
    
    row.setBackground(Color.WHITE);
    row.setOpaque(false);
    
    // Bottom border for separation
    row.setBorder(BorderFactory.createCompoundBorder(
        BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY),
        BorderFactory.createEmptyBorder(5, 10, 5, 10) // Added padding: top, left, bottom, right
    ));

    // --- STEP 2: Larger Fonts for Visibility ---
    String rankText = (rank == 1) ? "🏆 " + rank : String.valueOf(rank);
    JLabel nameLabel = new JLabel(rankText + ". " + entry.getKey());
    
    // Make the font larger (Size 18 or 20)
    nameLabel.setFont(new Font("SansSerif", rank <= 3 ? Font.BOLD : Font.PLAIN, 18));
    
    // Styling the Quantity
    JLabel qtyLabel = new JLabel(entry.getValue() + " sold");
    qtyLabel.setFont(new Font("SansSerif", Font.BOLD, 16)); // Increased from 14
    qtyLabel.setForeground(new Color(34, 139, 34)); 

    row.add(nameLabel, BorderLayout.WEST);
    row.add(qtyLabel, BorderLayout.EAST);
    
    // Add spacing between rows
    bestsellingpanel.add(row);
    bestsellingpanel.add(Box.createRigidArea(new Dimension(0, 10))); // Extra 10px gap
    
    rank++;
}
        bestsellingpanel.add(Box.createVerticalGlue()); 
        
        salespanel.add(scrollPanebestselling);

        // Pushes items to the top

        


        JLabel bestsellinglabel = new JLabel("Total Sales");
        bestsellinglabel.setFont(new Font("Arial", Font.BOLD, 26));
        bestsellinglabel.setForeground(Color.BLACK);
        bestsellinglabel.setBounds(140, 317, 300, 30);
        salespanel.add(bestsellinglabel);

        JLabel totaldailysales = new JLabel("Today's Sales: " + String.valueOf(DatabaseManager.getTotalSalesbyPeriod("day")));
        totaldailysales.setFont(new Font("Arial", Font.BOLD, 25));
        totaldailysales.setForeground(Color.BLACK);
        totaldailysales.setBounds(70, 375, 300, 30);
        salespanel.add(totaldailysales);

        JButton viewDailySales = new JButton("View");
        viewDailySales.setSize(100, 50);
        viewDailySales.setBackground(new Color(165, 215, 155));
        viewDailySales.setForeground(Color.WHITE);
        viewDailySales.setFont(new Font("Arial", Font.BOLD, 15));
        viewDailySales.setBounds(220, 430, 100, 30);
        viewDailySales.setFocusPainted(false);
        viewDailySales.addActionListener(e -> {
            new salesReportByPeriodFrame("Today");
        });
        salespanel.add(viewDailySales);

        JLabel totalweeklysales = new JLabel("Weekly Sales: " + String.valueOf(DatabaseManager.getTotalSalesbyPeriod("week")));
        totalweeklysales.setFont(new Font("Arial", Font.BOLD, 25));
        totalweeklysales.setForeground(Color.BLACK);
        totalweeklysales.setBounds(70, 475, 300, 30);
        salespanel.add(totalweeklysales);

        JButton viewWeeklySales = new JButton("View");
        viewWeeklySales.setSize(100, 50);
        viewWeeklySales.setBackground(new Color(165, 215, 155));
        viewWeeklySales.setForeground(Color.WHITE);
        viewWeeklySales.setFont(new Font("Arial", Font.BOLD, 15));
        viewWeeklySales.setBounds(220, 530, 100, 30);
        viewWeeklySales.setFocusPainted(false);
        viewWeeklySales.addActionListener(e -> {
            new salesReportByPeriodFrame("Weekly");
        });
        salespanel.add(viewWeeklySales);

        JLabel totalmonthlysales = new JLabel("Monthly Sales: " + String.valueOf(DatabaseManager.getTotalSalesbyPeriod("month")));
        totalmonthlysales.setFont(new Font("Arial", Font.BOLD, 25));
        totalmonthlysales.setForeground(Color.BLACK);
        totalmonthlysales.setBounds(70, 575, 300, 30);
        salespanel.add(totalmonthlysales);

        JButton viewMonthlySales = new JButton("View");
        viewMonthlySales.setSize(100, 50);
        viewMonthlySales.setBackground(new Color(165, 215, 155));
        viewMonthlySales.setForeground(Color.WHITE);
        viewMonthlySales.setFont(new Font("Arial", Font.BOLD, 15));
        viewMonthlySales.setBounds(220, 630, 100, 30);
        viewMonthlySales.setFocusPainted(false);
        viewMonthlySales.addActionListener(e -> {
            new salesReportByPeriodFrame("Monthly");
        });
        salespanel.add(viewMonthlySales);

        JLabel saleschartlabel = new JLabel("Transactions");
        saleschartlabel.setFont(new Font("Arial", Font.BOLD, 26));
        saleschartlabel.setForeground(Color.BLACK);
        saleschartlabel.setBounds(440, 317, 300, 30);
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