package main.view;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import main.database.*;
import main.model.*;

public class DB_Staff extends JFrame {

    private User user;

   
    private List<Product> products = DatabaseManager.getAllProducts();
    private List<Product> foodProducts = DatabaseManager.getProductsByCategory("Food");
    private List<Product> DrinkProducts = DatabaseManager.getProductsByCategory("Drink");
    private List<String> OrderName = new ArrayList<>();
    private List<Double> OrderPrice = new ArrayList<>();
    private double totalprice;

    private JPanel plorJPanel;
    private JLabel totalValueLabel;
    private JPanel scrollJPanel;
    private JScrollPane scrollPane;
    


    public DB_Staff(User user) {
        this.user = user;

        setTitle("Dashboard");
        setUndecorated(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //screen size maximized
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int CW = screenSize.width;
        int CH = screenSize.height;

      
        final Image bgImage = new ImageIcon("src/main/image/POS UI.png").getImage();
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

          

  // --- PANEL 1: HEADER (Blue) ---
        JPanel header = new JPanel(null);
        header.setBackground(Color.MAGENTA); 
        header.setBounds(30, 30, 950, 90);
        header.setOpaque(false);


        canvas.add(header);

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


staffLogo.setBounds(743, 30, 180, 50); 


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
        JLabel staffText = new JLabel("STAFF");
        staffText.setFont(new Font("SansSerif", Font.BOLD, 26));
        staffText.setForeground(new Color(255, 204, 0));
        staffText.setBounds(810, 30, 400, 50);
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
                
                    staffText.setText("Staff");
                }
            });

        header.add(staffText);
        header.setComponentZOrder(staffLogo, 0);

        // --- PANEL 3: SIDEBAR ---
        JPanel sidebar = new JPanel(null);
        sidebar.setBackground(Color.CYAN);
        sidebar.setBounds(30, 130, 169, 800); 
        sidebar.setOpaque(false);
        canvas.add(sidebar);

    // MENU BUTTON
    ImageIcon menuIcon = new ImageIcon("src/main/image/menu (1).png");
   JLabel menuiconLabel = new JLabel(menuIcon);

    int sidebarWidth = 200; 
    int imgWidth = menuIcon.getIconWidth();
    int imgHeight = menuIcon.getIconHeight();


   int centerX = (sidebarWidth - imgWidth) / 2;


  menuiconLabel.setBounds(20, 110, imgWidth, imgHeight);


    menuiconLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
    menuiconLabel.addMouseListener(new java.awt.event.MouseAdapter() {
    @Override
    public void mousePressed(MouseEvent e) {
        //JOptionPane.showMessageDialog(null, "Menu button pressed!", "Menu Button", JOptionPane.INFORMATION_MESSAGE);
        scrollJPanel.removeAll();
        for(Product p: products){
            scrollJPanel.add(createProductCard(p));
        }
        scrollJPanel.revalidate();
        scrollJPanel.repaint();



    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
        menuiconLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        menuiconLabel.setIcon(getDimmedIcon(menuIcon));
        // Optional: Remove the .setText line if you don't want text appearing over your image
    }
    @Override
    public void mouseExited(MouseEvent e) {
        menuiconLabel.setIcon(menuIcon);
    }
});


// SNACK BUTTON
ImageIcon rawfoodIcon = new ImageIcon("src/main/image/burger (1).png");
JLabel foodiconLabel = new JLabel(rawfoodIcon); 

int barwidth = 200; 
int Width = rawfoodIcon.getIconWidth();
int Height = rawfoodIcon.getIconHeight();
int centerb = (barwidth - Width) / 2;


foodiconLabel.setBounds(20, 230, Width, Height); 
foodiconLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
foodiconLabel.addMouseListener(new java.awt.event.MouseAdapter() {
    @Override
    public void mousePressed(MouseEvent e) {
        //JOptionPane.showMessageDialog(null, "Food button pressed!", "Menu Button", JOptionPane.INFORMATION_MESSAGE);
        scrollJPanel.removeAll();
        for (Product p : foodProducts) {
            scrollJPanel.add(createProductCard(p));
        }
        scrollJPanel.revalidate();
        scrollJPanel.repaint();

    }
    @Override
    public void mouseEntered(MouseEvent e) {
        foodiconLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        foodiconLabel.setIcon(getDimmedIcon(rawfoodIcon));
    }
    @Override
    public void mouseExited(MouseEvent e) {
        foodiconLabel.setIcon(rawfoodIcon);
     }
});

// DRINK
ImageIcon rawdrinkIcon = new ImageIcon("src/main/image/drink (1).png");
JLabel drinkiconLabel = new JLabel(rawdrinkIcon);

int dWidth = rawdrinkIcon.getIconWidth();
int dHeight = rawdrinkIcon.getIconHeight();
int dCenter = (200 - dWidth) / 2;

drinkiconLabel.setBounds(25, 350, dWidth, dHeight);
drinkiconLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
drinkiconLabel.addMouseListener(new java.awt.event.MouseAdapter() {
    @Override
    public void mousePressed(MouseEvent e) {
        //JOptionPane.showMessageDialog(null, "Drink button pressed!", "Menu Button", JOptionPane.INFORMATION_MESSAGE);
        scrollJPanel.removeAll();
        for (Product p : DrinkProducts) {
            scrollJPanel.add(createProductCard(p));
        }
        scrollJPanel.revalidate();
        scrollJPanel.repaint();
    }
    @Override
    public void mouseEntered(MouseEvent e) {
        drinkiconLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        drinkiconLabel.setIcon(getDimmedIcon(rawdrinkIcon));
    }
    @Override
    public void mouseExited(MouseEvent e) { 
        drinkiconLabel.setIcon(rawdrinkIcon);
    }
});

// MEAL ICON 
ImageIcon rawMealIcon = new ImageIcon("src/main/image/meal (1).png");
JLabel mealiconLabel = new JLabel(rawMealIcon);

int mWidth = rawMealIcon.getIconWidth();
int mHeight = rawMealIcon.getIconHeight();
int mCenter = (200 - mWidth) / 2;

mealiconLabel.setBounds(20, 460, mWidth, mHeight);
mealiconLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
mealiconLabel.addMouseListener(new java.awt.event.MouseAdapter() {
    @Override
    public void mousePressed(MouseEvent e) {
        //JOptionPane.showMessageDialog(null, "Meal button pressed!", "Menu Button", JOptionPane.INFORMATION_MESSAGE);
        scrollJPanel.removeAll();
        for (Product p : foodProducts) {
            scrollJPanel.add(createProductCard(p));
        }
        scrollJPanel.revalidate();
        scrollJPanel.repaint();
    }
    @Override
    public void mouseEntered(MouseEvent e) {
        mealiconLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        mealiconLabel.setIcon(getDimmedIcon(rawMealIcon));
    }
    @Override
    public void mouseExited(MouseEvent e) {
        mealiconLabel.setIcon(rawMealIcon);
     }
});

//SALES ICON 
ImageIcon rawSalesIcon = new ImageIcon("src/main/image/saless (1).png");
JLabel salesiconLabel = new JLabel(rawSalesIcon);

int sWidth = rawSalesIcon.getIconWidth();
int sHeight = rawSalesIcon.getIconHeight();
int sCenter = (200 - sWidth) / 2;

salesiconLabel.setBounds(20, 590, sWidth, sHeight);
salesiconLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
salesiconLabel.addMouseListener(new java.awt.event.MouseAdapter() {
    @Override
    public void mousePressed(MouseEvent e) {
        //JOptionPane.showMessageDialog(null, "Sales button pressed!", "Menu Button", JOptionPane.INFORMATION_MESSAGE);
        new sales_staffFrame();
        dispose();
    }
    @Override
    public void mouseEntered(MouseEvent e) {
        salesiconLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        salesiconLabel.setIcon(getDimmedIcon(rawSalesIcon));
    }
    @Override
    public void mouseExited(MouseEvent e) {
        salesiconLabel.setIcon(rawSalesIcon);
     }
});

        sidebar.add(menuiconLabel);
        sidebar.add(foodiconLabel);
        sidebar.add(drinkiconLabel);
        sidebar.add(mealiconLabel);
        sidebar.add(salesiconLabel);


     
        //  PANEL ?? BSTA SEARCH BAR PANEL XD
JPanel textPanel = new JPanel(new BorderLayout());
textPanel.setBounds(275, 162, 550, 40); 
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
            searchField.setCaretColor(new Color(0, 0, 0, 0)); // Hide caret again
            searchField.setFocusable(false);
        }
    }
});


searchField.addKeyListener(new KeyAdapter() {
    @Override
    public void keyReleased(KeyEvent e) {
        String query = searchField.getText().toLowerCase();
        scrollJPanel.removeAll();
        for (Product p : products) {
            if (p.getName().toLowerCase().contains(query)) {
                scrollJPanel.add(createProductCard(p));
            }
        }
        scrollJPanel.revalidate();
        scrollJPanel.repaint();
    }
});

   textPanel.add(searchField, BorderLayout.CENTER);
   add(textPanel); 


        // --- PANEL 2: MENU CONTAINER (Magenta) ---
        // Fills the middle gap
        JPanel MenuContainer = new JPanel(null);
        MenuContainer.setBackground(new Color(200, 0, 200)); 
        MenuContainer.setBounds(209, 239, 900, 700);
        MenuContainer.setOpaque(false);


      
      
       // --- PANEL 4: SCROLL AREA (Yellow) ---

        // Centered inside Magenta

     scrollJPanel = new JPanel(new BorderLayout());

        scrollJPanel.setLayout(new GridLayout(0, 3, 20, 20));
        scrollPane = new JScrollPane(scrollJPanel);

        scrollPane.setBackground(Color.YELLOW);
        scrollPane.setBounds(0, 0, 700, 630);
        scrollPane.setBorder(null);
        
        scrollPane.getViewport().setBackground(Color.WHITE);
        scrollPane.setOpaque(false);


        for (Product p : products) {


            scrollJPanel.add(createProductCard(p));

        }

        MenuContainer.add(scrollPane);
        canvas.add(MenuContainer); 



        // --- 5th PANEL: ORDER DETAILS 
        JPanel orderJPanel = new JPanel(null);
        orderJPanel.setBackground(new Color(0, 255, 0, 150)); 
        orderJPanel.setBounds(940, 25, 480, 200);
        orderJPanel.setOpaque(false);
        canvas.add(orderJPanel);

        JLabel orderDetails = new JLabel("Order Details");
        orderDetails.setForeground(Color.BLACK);
        orderDetails.setFont(new Font("SansSerif", Font.BOLD, 40));
        orderDetails.setBounds(110, 29, 400, 40);
        orderJPanel.add(orderDetails);


        JLabel currentOrder = new JLabel("Current Order:");
        currentOrder.setForeground(Color.BLACK);
        currentOrder.setFont(new Font("SansSerif", Font.PLAIN, 26));
        currentOrder.setBounds(50, 150, 500, 50);
        orderJPanel.add(currentOrder);

        


        // clear button
        ImageIcon clearIcon = new ImageIcon("src/main/image/clear all (2).png");
        JLabel clearLabel = new JLabel(clearIcon);

        int barw = 150;
        int W = rawfoodIcon.getIconWidth();
        int H = rawfoodIcon.getIconHeight();
        int centerc = (barwidth - Width) / 2;

        clearLabel.setBounds(140, 120, 350, Height);
        clearLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));

        clearLabel.addMouseListener(new java.awt.event.MouseAdapter() {

            @Override

            public void mousePressed(MouseEvent e) {

                int option =JOptionPane.showConfirmDialog(null, "Are you sure you want to clear all items?", "Confirm Clear All", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

                if (option == JOptionPane.YES_OPTION) {
                    JOptionPane.showMessageDialog(null, "Items deleted!", "clear all Button", JOptionPane.INFORMATION_MESSAGE);
                    OrderName.clear();
                    OrderPrice.clear();
                    plorJPanel.removeAll();
                    plorJPanel.revalidate();
                    plorJPanel.repaint();
                    totalValueLabel.setText("₱ 0.00");
                    totalprice = 0.0;

                }

               
            }

            @Override

            public void mouseEntered(MouseEvent e) {
                clearLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
                clearLabel.setIcon(getDimmedIcon(clearIcon));
               

            }
            @Override

            public void mouseExited(MouseEvent e) { 
                clearLabel.setIcon(clearIcon);
            }
        });

        orderJPanel.add(clearLabel);

    

    // --- 6th PANEL: PLACE ORDER PANEL (Green) ---
        plorJPanel = new JPanel();
        plorJPanel.setLayout(new BoxLayout(plorJPanel, BoxLayout.Y_AXIS)); 
        plorJPanel.setBackground(Color.WHITE);
        plorJPanel.setOpaque(true);


        JScrollPane orderScroll = new JScrollPane(plorJPanel);
        orderScroll.setBounds(962, 250, 430, 480); 
        orderScroll.setBorder(BorderFactory.createEmptyBorder());
        orderScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        



        canvas.add(orderScroll);

         //place order button
       
    // 7th PANEL: ORDER DETAILS (Green) ---

    JPanel bottomorderJPanel = new JPanel(null);
    bottomorderJPanel.setBackground(Color.GREEN);
    bottomorderJPanel.setBounds(940, 745, 480, 160);
    bottomorderJPanel.setOpaque(false);

    JLabel totalText = new JLabel("Total: ");
    totalText.setForeground(Color.BLACK);
    totalText.setFont(new Font("SansSerif", Font.PLAIN, 30));
    totalText.setBounds(30, 20, 400, 50);
    
    totalprice = OrderPrice.stream().mapToDouble(Double::doubleValue).sum();
    totalValueLabel = new JLabel("₱ "+ totalprice); 
    totalValueLabel.setForeground(Color.BLACK);
    totalValueLabel.setFont(new Font("SansSerif", Font.BOLD, 30));
    totalValueLabel.setBounds(120, 20, 200, 50);




   ImageIcon rawPoIcon = new ImageIcon("src/main/image/PlaceOrder (1).png");



    // 2. Scale it to fit the panel (e.g., 400 width, 120 height)

    Image poImg = rawPoIcon.getImage();

    Image scaledPoImg = poImg.getScaledInstance(147, 65, Image.SCALE_SMOOTH);

    ImageIcon poIcon = new ImageIcon(scaledPoImg);


    JLabel powLabel = new JLabel(poIcon);

    powLabel.setBounds(160, 85, 147, 65); // Center it within the 480x160 panel

    powLabel.setCursor(new Cursor(Cursor.HAND_CURSOR)); 

    // 4. Correct the listener logic
    powLabel.addMouseListener(new java.awt.event.MouseAdapter() {
        @Override
        public void mousePressed(MouseEvent e) {
            
            if (OrderName.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Order is empty!", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
            double totalSum = OrderPrice.stream().mapToDouble(Double::doubleValue).sum();
        
            StringBuilder orderDetails = new StringBuilder("Items to Finalize:\n");
            for (int i = 0; i < OrderName.size(); i++) {
                orderDetails.append("- ").append(OrderName.get(i)).append(" (₱").append(OrderPrice.get(i)).append(")\n");
            }
            orderDetails.append("\nTOTAL AMOUNT: ₱").append(String.format("%.2f", totalSum));

        // 3. Create the Finalize Dialog
            JTextField cashField = new JTextField();
            Object[] message = {
                orderDetails.toString(),
                "Enter Cash Amount:", cashField
            };

            int option = JOptionPane.showConfirmDialog(null, message, "Finalize Order", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        
            if(option == JOptionPane.OK_OPTION && !OrderName.isEmpty()) {
                try {
                double cash = Double.parseDouble(cashField.getText());
                if (cash < totalSum) {
                    JOptionPane.showMessageDialog(null, "Insufficient cash!", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    for (String itemName : OrderName) {
                        // Decrement stock by 1 for every instance in the list
                        DatabaseManager.updateProductStock(itemName, 1);
                    }
                    double change = cash - totalSum;
                    JOptionPane.showMessageDialog(null, "Order Successful!\nChange: ₱" + String.format("%.2f", change), "Success", JOptionPane.INFORMATION_MESSAGE);
                    
                    // --- CLEAR EVERYTHING AFTER SUCCESS ---
                    OrderName.clear();
                    OrderPrice.clear();
                    plorJPanel.removeAll();
                    plorJPanel.revalidate();
                    plorJPanel.repaint();
                    totalValueLabel.setText("₱ 0.00");

                    
                    new ReceiptFrame(user, totalSum, cash, change, "Cash",  OrderName,  OrderPrice);
                    dispose();
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Please enter a valid amount.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            }
            } else{

            }
        }
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            powLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }
    });
    canvas.add(bottomorderJPanel);
    bottomorderJPanel.add(powLabel);
    bottomorderJPanel.add(totalText);
    bottomorderJPanel.add(totalValueLabel);
    mainContainer.add(canvas);
    this.add(mainContainer);
    this.setVisible(true);

       
    }

    private JPanel createProductCard(Product product) {
        String name = product.getName();
        double price = product.getPrice();
        int stock = product.getStock();
        JPanel card = new JPanel();
       
        card.setPreferredSize(new Dimension(180, 110)); // Size of the blue box
        if(stock <=0){
            card.setBackground(Color.GRAY);
            JLabel soldOut = new JLabel("OUT OF STOCK", SwingConstants.CENTER);
            soldOut.setForeground(Color.RED);
            soldOut.setFont(new Font("SansSerif", Font.BOLD, 12));
            card.add(soldOut, BorderLayout.NORTH);   
        } else{
            card.setBackground(new Color(71, 69, 122)); 
            
            
        }  // Light blue color from your screenshot
        card.setLayout(new BorderLayout());
        card.setBorder(BorderFactory.createLineBorder(new Color(150, 150, 150), 1));

        JLabel nameLabel = new JLabel("<html><center>" + name +  "<br>₱" + price + "</center></html>", SwingConstants.CENTER);
        nameLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        if(stock <=0){nameLabel.setForeground(Color.DARK_GRAY);
        } else {nameLabel.setForeground(Color.YELLOW);}
 
        card.add(nameLabel, BorderLayout.CENTER);
        

        card.setCursor(new Cursor(Cursor.HAND_CURSOR));
        card.addMouseListener(new MouseAdapter() {
        @Override
        public void mousePressed(MouseEvent e) {
            if(stock <=0){
                JOptionPane.showMessageDialog(null, "Out of stock!", "Error", JOptionPane.ERROR_MESSAGE);
                OrderName.remove(OrderName.size() - 1);
                OrderPrice.remove(OrderPrice.size() - 1);
                return;
            } 

            long countInOrder = OrderName.stream()
                                     .filter(item -> item.equals(name))
                                     .count();
                                     

        // 2. Check if adding one more exceeds the database stock
        if (countInOrder >= stock) {
            JOptionPane.showMessageDialog(null, 
                "Cannot add more! Only " + stock + " items available in stock.", 
                "Stock Limit Reached", 
                JOptionPane.WARNING_MESSAGE);
            return; // Stop here; don't add to OrderName
        }
            OrderName.add(name);
            OrderPrice.add(price);
 
            JPanel itemRow = new JPanel(new BorderLayout());
            itemRow.setMaximumSize(new Dimension(450, 50)); 
            itemRow.setBackground(Color.WHITE);
            itemRow.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));

  
            JLabel nameLbl = new JLabel("  " + name + "  ");
            nameLbl.setFont(new Font("SansSerif", Font.PLAIN, 16));
            
            JLabel priceLbl = new JLabel("₱ " + (int)price + "  ");
            priceLbl.setFont(new Font("SansSerif", Font.BOLD, 16));

            JButton removeBtn = new JButton("X");
            removeBtn.setFont(new Font("SansSerif", Font.BOLD, 12));
            removeBtn.setForeground(Color.RED);
            removeBtn.setBorderPainted(false);
            removeBtn.setContentAreaFilled(false);
            removeBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
            removeBtn.addMouseListener(new MouseAdapter() {

                @Override
                public void mousePressed(MouseEvent e) {
                    int index = plorJPanel.getComponentZOrder(itemRow);
                    if (index >= 0 && index < OrderName.size()) {
                        OrderName.remove(index);
                        OrderPrice.remove(index);
                        plorJPanel.remove(itemRow);
                        plorJPanel.revalidate();
                        plorJPanel.repaint();

                        double currentTotal = OrderPrice.stream().mapToDouble(Double::doubleValue).sum();
                        totalValueLabel.setText("₱ " + String.format("%.2f", currentTotal));
                    }
                }
            });

            
            


            itemRow.add(nameLbl, BorderLayout.WEST);
            itemRow.add(priceLbl, BorderLayout.CENTER);
            itemRow.add(removeBtn, BorderLayout.EAST);

            

            double currentTotal = OrderPrice.stream().mapToDouble(Double::doubleValue).sum();
            totalValueLabel.setText("₱ " + String.format("%.2f", currentTotal));


            // 3. Add to the 6th panel (plorJPanel) and refresh UI
            plorJPanel.add(itemRow);
            plorJPanel.revalidate();
            plorJPanel.repaint();
            
           
           
        }
        @Override
        public void mouseEntered(MouseEvent e) {
            if(stock <=0){
                return;
            }
            card.setBackground(new Color(81, 79, 132)); 
        }
        @Override
        public void mouseExited(MouseEvent e) {
            if(stock <=0){
                return;
            }
            card.setBackground(new Color(71, 69, 122)); 
        }

        
        });

        return card;
    }

    private ImageIcon getDimmedIcon(ImageIcon icon) {

    Image img = icon.getImage();
    BufferedImage buffered = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);
    Graphics2D g2d = buffered.createGraphics();
    
    // Set opacity to 50%
    g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
    g2d.drawImage(img, 0, 0, null);
    g2d.dispose();
    
    return new ImageIcon(buffered);
}
        public static void main(String[] args) {
            User testUser = new User("rob", "hi", "raymundo", "Male", null, null, "staff", true, 00, 19);
            new DB_Staff(testUser);
        }
    } 