package main.view;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import main.database.*;
import main.model.*;

public class DB_Staff extends JFrame {

    User user;

   
    private List<Product> products = DatabaseManager.getAllProducts();
    private List<Product> foodProducts = DatabaseManager.getProductsByCategory("Food");
    private List<Product> DrinkProducts = DatabaseManager.getProductsByCategory("Drink");
    private List<String> OrderName = new ArrayList<>();
    private List<Double> OrderPrice = new ArrayList<>();
    private double totalprice;

    private JPanel plorJPanel;
    private JLabel totalValueLabel;


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
        header.setBackground(Color.BLUE); 
        header.setBounds(30, 0, 950, 90);
        header.setOpaque(false);


        canvas.add(header);

        JLabel companyName = new JLabel("Company name");
        companyName.setFont(new Font("SansSerif", Font.BOLD, 35));
        companyName.setForeground(new Color(255, 204, 0));
        companyName.setBounds(20, 20, 350, 50); 
        header.add(companyName);


        

        //Staff Logo (The Image)
        ImageIcon rawIcon = new ImageIcon("src/main/image/stafflogo.png"); 

        Image img = rawIcon.getImage();
        Image scaledImg = img.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        
        ImageIcon staffLogoIcon = new ImageIcon(scaledImg);

        JLabel staffLogo = new JLabel(staffLogoIcon);
        staffLogo.setBounds(800, 20, 50, 50);

        // DEBUG: This red border will show you exactly where the image SHOULD be
        staffLogo.setBorder(BorderFactory.createLineBorder(Color.RED)); 
        staffLogo.setCursor(new Cursor(Cursor.HAND_CURSOR));
        staffLogo.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
            UserDetailFrame userDetailFrame = new UserDetailFrame(user);
            userDetailFrame.setVisible(true);  
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            
                staffLogo.setCursor(new Cursor(Cursor.HAND_CURSOR));
            
                staffLogo.setText("<html><u>Staff</u></html>");
            }

            @Override
            public void mouseExited(MouseEvent e) {
            
                staffLogo.setText("Staff");
            }
        });

    

        header.add(staffLogo);

    //Staff Text
        JLabel staffText = new JLabel("Staff");
        staffText.setFont(new Font("SansSerif", Font.BOLD, 35));
        staffText.setForeground(new Color(255, 204, 0));
        staffText.setBounds(870, 20, 100, 50);
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
        JOptionPane.showMessageDialog(null, "Menu button pressed!", "Menu Button", JOptionPane.INFORMATION_MESSAGE);
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
        // Optional: Remove the .setText line if you don't want text appearing over your image
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
        JOptionPane.showMessageDialog(null, "Food button pressed!", "Menu Button", JOptionPane.INFORMATION_MESSAGE);
    }
    @Override
    public void mouseEntered(MouseEvent e) {
        foodiconLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        foodiconLabel.setText("<html><u>Staff</u></html>");
    }
    @Override
    public void mouseExited(MouseEvent e) { }
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
        JOptionPane.showMessageDialog(null, "Drink button pressed!", "Menu Button", JOptionPane.INFORMATION_MESSAGE);
    }
    @Override
    public void mouseEntered(MouseEvent e) {
        drinkiconLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        drinkiconLabel.setText("<html><u>Staff</u></html>");
    }
    @Override
    public void mouseExited(MouseEvent e) { }
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
        JOptionPane.showMessageDialog(null, "Meal button pressed!", "Menu Button", JOptionPane.INFORMATION_MESSAGE);
    }
    @Override
    public void mouseEntered(MouseEvent e) {
        mealiconLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        mealiconLabel.setText("<html><u>Staff</u></html>");
    }
    @Override
    public void mouseExited(MouseEvent e) { }
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
        JOptionPane.showMessageDialog(null, "Sales button pressed!", "Menu Button", JOptionPane.INFORMATION_MESSAGE);
    }
    @Override
    public void mouseEntered(MouseEvent e) {
        salesiconLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        salesiconLabel.setText("<html><u>Staff</u></html>");
    }
    @Override
    public void mouseExited(MouseEvent e) { }
});

        sidebar.add(menuiconLabel);
        sidebar.add(foodiconLabel);
        sidebar.add(drinkiconLabel);
        sidebar.add(mealiconLabel);
        sidebar.add(salesiconLabel);


        // --- PANEL 2: MENU CONTAINER (Magenta) ---
        // Fills the middle gap
        JPanel MenuContainer = new JPanel(null);
        MenuContainer.setBackground(new Color(200, 0, 200)); 
        MenuContainer.setBounds(240, 200, 700, 630);
        //MenuContainer.setOpaque(false);


      
      
       // --- PANEL 4: SCROLL AREA (Yellow) ---

        // Centered inside Magenta

        JPanel scrollJPanel = new JPanel(new BorderLayout());

        scrollJPanel.setLayout(new GridLayout(0, 3, 20, 20));


        JScrollPane scrollPane = new JScrollPane(scrollJPanel);

        scrollPane.setBackground(Color.YELLOW);

        scrollPane.setBounds(0, 0, 700, 630);

        scrollPane.setBorder(null);

        scrollPane.getViewport().setBackground(Color.WHITE);


        for (Product p : products) {


            scrollJPanel.add(createProductCard(p.getName(), p.getPrice()));

        }

        MenuContainer.add(scrollPane);
        canvas.add(MenuContainer); 



        // --- 5th PANEL: ORDER DETAILS (Green) ---
        JPanel orderJPanel = new JPanel(null);
        orderJPanel.setBackground(new Color(0, 255, 0, 150)); 
        orderJPanel.setBounds(1015, 25, 480, 200);
        orderJPanel.setOpaque(false);
        canvas.add(orderJPanel);

        JLabel orderDetails = new JLabel("Order Details");
        orderDetails.setForeground(Color.BLACK);
        orderDetails.setFont(new Font("SansSerif", Font.BOLD, 40));
        orderDetails.setBounds(115, 10, 400, 40);
        orderJPanel.add(orderDetails);


        JLabel currentOrder = new JLabel("Current Order");
        currentOrder.setForeground(Color.BLACK);
        currentOrder.setFont(new Font("SansSerif", Font.PLAIN, 20));
        currentOrder.setBounds(5, 120, 400, 50);
        orderJPanel.add(currentOrder);

        


        // clear button
        ImageIcon clearIcon = new ImageIcon("src/main/image/clear all (2).png");
        JLabel clearLabel = new JLabel(clearIcon);

        int barw = 150;
        int W = rawfoodIcon.getIconWidth();
        int H = rawfoodIcon.getIconHeight();
        int centerc = (barwidth - Width) / 2;

        clearLabel.setBounds(50, 90, 350, Height);
        clearLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));

        clearLabel.addMouseListener(new java.awt.event.MouseAdapter() {

            @Override

            public void mousePressed(MouseEvent e) {

                JOptionPane.showMessageDialog(null, "clear pressed!", "clear all Button", JOptionPane.INFORMATION_MESSAGE);
            }

            @Override

            public void mouseEntered(MouseEvent e) {
                clearLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
               

            }
            @Override

            public void mouseExited(MouseEvent e) { }
        });

        orderJPanel.add(clearLabel);

    

    // --- 6th PANEL: PLACE ORDER PANEL (Green) ---
        plorJPanel = new JPanel();
        plorJPanel.setLayout(new BoxLayout(plorJPanel, BoxLayout.Y_AXIS)); 
        plorJPanel.setBackground(Color.WHITE); 
        plorJPanel.setOpaque(true);

        JScrollPane orderScroll = new JScrollPane(plorJPanel);
        orderScroll.setBounds(1015, 220, 475, 460); 
        orderScroll.setBorder(BorderFactory.createEmptyBorder());
        orderScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        



        canvas.add(orderScroll);

         //place order button
       
    // 7th PANEL: ORDER DETAILS (Green) ---

    JPanel bottomorderJPanel = new JPanel(null);
    bottomorderJPanel.setBackground(Color.GREEN);
    bottomorderJPanel.setBounds(1015, 680, 480, 160);
    bottomorderJPanel.setOpaque(false);

    JLabel totalText = new JLabel("Total:");
    totalText.setForeground(Color.BLACK);
    totalText.setFont(new Font("SansSerif", Font.PLAIN, 30));
    totalText.setBounds(0, 0, 400, 50);
    
    totalprice = OrderPrice.stream().mapToDouble(Double::doubleValue).sum();
    totalValueLabel = new JLabel("₱ "+ totalprice); 
    totalValueLabel.setForeground(Color.BLACK);
    totalValueLabel.setFont(new Font("SansSerif", Font.BOLD, 30));
    totalValueLabel.setBounds(100, 0, 200, 50);




    ImageIcon rawPoIcon = new ImageIcon("src/main/image/place order (1).png");

    // 2. Scale it to fit the panel (e.g., 400 width, 120 height)
    Image poImg = rawPoIcon.getImage();
    Image scaledPoImg = poImg.getScaledInstance(147, 65, Image.SCALE_SMOOTH); 
    ImageIcon poIcon = new ImageIcon(scaledPoImg);

    // 3. Create the label with the scaled icon
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
                    double change = cash - totalSum;
                    JOptionPane.showMessageDialog(null, "Order Successful!\nChange: ₱" + String.format("%.2f", change), "Success", JOptionPane.INFORMATION_MESSAGE);
                    
                    // --- CLEAR EVERYTHING AFTER SUCCESS ---
                    OrderName.clear();
                    OrderPrice.clear();
                    plorJPanel.removeAll();
                    plorJPanel.revalidate();
                    plorJPanel.repaint();
                    totalValueLabel.setText("₱ 0.00");
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





    private JPanel createProductCard(String name, double price) {
        JPanel card = new JPanel();
       
        card.setPreferredSize(new Dimension(180, 110)); // Size of the blue box
        card.setBackground(new Color(71, 69, 122));   // Light blue color from your screenshot
        card.setLayout(new BorderLayout());
        card.setBorder(BorderFactory.createLineBorder(new Color(150, 150, 150), 1));

        JLabel nameLabel = new JLabel("<html><center>" + name + "<br>₱" + price + "</center></html>", SwingConstants.CENTER);
        nameLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        nameLabel.setForeground(Color.BLACK);
        card.add(nameLabel, BorderLayout.CENTER);

        card.setCursor(new Cursor(Cursor.HAND_CURSOR));
        card.addMouseListener(new MouseAdapter() {
        @Override
        public void mousePressed(MouseEvent e) {
            OrderName.add(name);
            OrderPrice.add(price);

  
            JPanel itemRow = new JPanel(new BorderLayout());
            itemRow.setMaximumSize(new Dimension(450, 50)); 
            itemRow.setBackground(Color.WHITE);
            itemRow.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));

  
            JLabel nameLbl = new JLabel("  " + name);
            nameLbl.setFont(new Font("SansSerif", Font.PLAIN, 16));
            
            JLabel priceLbl = new JLabel("₱ " + (int)price + "  ");
            priceLbl.setFont(new Font("SansSerif", Font.BOLD, 16));

            itemRow.add(nameLbl, BorderLayout.WEST);
            itemRow.add(priceLbl, BorderLayout.EAST);

            double currentTotal = OrderPrice.stream().mapToDouble(Double::doubleValue).sum();
            totalValueLabel.setText("₱ " + String.format("%.2f", currentTotal));


            // 3. Add to the 6th panel (plorJPanel) and refresh UI
            plorJPanel.add(itemRow);
            plorJPanel.revalidate();
            plorJPanel.repaint();
            
           
           
        }
        @Override
        public void mouseEntered(MouseEvent e) {
            card.setBackground(new Color(81, 79, 132)); 
        }
        @Override
        public void mouseExited(MouseEvent e) {
            card.setBackground(new Color(71, 69, 122)); 
        }

        
        });

        return card;
    }
        public static void main(String[] args) {
            User testUser = new User("rob", "hi", "raymundo", "Male", null, null, "staff", true, 00, 19);
            new DB_Staff(testUser);
        }
    }