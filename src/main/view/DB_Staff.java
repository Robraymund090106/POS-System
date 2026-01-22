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
        header.setBounds(30, 50, 999, 90);


        canvas.add(header);

        

        //Staff Logo (The Image)
        ImageIcon rawIcon = new ImageIcon("src/main/image/stafflogo.png"); 

        Image img = rawIcon.getImage();
        Image scaledImg = img.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        
        ImageIcon staffLogoIcon = new ImageIcon(scaledImg);

        JLabel staffLogo = new JLabel(staffLogoIcon);
        staffLogo.setBounds(600, 20, 50, 50);

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
        staffText.setFont(new Font("SansSerif", Font.PLAIN, 30));
        staffText.setForeground(new Color(255, 204, 0));
        staffText.setBounds(670, 20, 100, 50);
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
        MenuContainer.setBounds(240, 240, 650, 630);

      
      
       // --- PANEL 4: SCROLL AREA (Yellow) ---

        // Centered inside Magenta

        JPanel scrollJPanel = new JPanel(new BorderLayout());

        scrollJPanel.setLayout(new GridLayout(0, 3, 20, 20));


        JScrollPane scrollPane = new JScrollPane(scrollJPanel);

        scrollPane.setBackground(Color.YELLOW);

        scrollPane.setBounds(25, 100, 600, 500);

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
        orderJPanel.setBounds(940, 2, 445, 380);
        orderJPanel.setOpaque(false);
        canvas.add(orderJPanel);

        // clear button
ImageIcon clearIcon = new ImageIcon("src/main/image/clear all (2).png");
JLabel clearLabel = new JLabel(clearIcon);

int barw = 150;
int W = rawfoodIcon.getIconWidth();
int H = rawfoodIcon.getIconHeight();
int centerc = (barwidth - Width) / 2;

clearLabel.setBounds(100, 230, 350, Height);
clearLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));

clearLabel.addMouseListener(new java.awt.event.MouseAdapter() {

    @Override

    public void mousePressed(MouseEvent e) {

        JOptionPane.showMessageDialog(null, "clear pressed!", "clear all Button", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override

    public void mouseEntered(MouseEvent e) {
        clearLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        clearLabel.setText("<html><u>Staff</u></html>");

    }
    @Override

    public void mouseExited(MouseEvent e) { }
});
    

    // --- 6th PANEL: PLACE ORDER PANEL (Magenta/Pink) ---
JPanel plorJPanel = new JPanel(null);
        plorJPanel.setBackground(new Color(0, 255, 0, 150)); 
        plorJPanel.setBounds(940, 2, 445, 380);
        canvas.add(plorJPanel);

        // place order button
ImageIcon poIcon = new ImageIcon("src/main/image/place order (1).png");
JLabel powLabel = new JLabel(poIcon);

int bw = 150;
int w = rawfoodIcon.getIconWidth();
int h = rawfoodIcon.getIconHeight();
int centere = (barwidth - Width) / 2;

powLabel.setBounds(100, 230, 350, Height);
powLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));

clearLabel.addMouseListener(new java.awt.event.MouseAdapter() {

    @Override

    public void mousePressed(MouseEvent e) {

        JOptionPane.showMessageDialog(null, "place order pressed!", "place all Button", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override

    public void mouseEntered(MouseEvent e) {
        powLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        powLabel.setText("<html><u>Staff</u></html>");

    }
    @Override

    public void mouseExited(MouseEvent e) { }
});

 plorJPanel.add(powLabel);
       
    }


    private JPanel createProductCard(String name, double price) {
        JPanel card = new JPanel();
       
        card.setPreferredSize(new Dimension(180, 110)); // Size of the blue box
        card.setBackground(new Color(102, 225, 225));   // Light blue color from your screenshot
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
                JOptionPane.showMessageDialog(null, name + " Added to Order", "Success", JOptionPane.INFORMATION_MESSAGE);
                
            }

            public void mouseEntered(MouseEvent e) {
            card.setBackground(new Color(80, 200, 200)); // Darker blue on hover
             }
        
            @Override
            public void mouseExited(MouseEvent e) {
                card.setBackground(new Color(102, 225, 225)); // Original color
            }
        });

        return card;
    }
        public static void main(String[] args) {
            User testUser = new User("rob", "hi", "raymundo", "Male", null, null, "staff", true, 00, 19);
            new DB_Staff(testUser);
        }
    }