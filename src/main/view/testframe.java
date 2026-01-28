package main.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.*;
import java.awt.*;
import java.util.ArrayList;

import main.model.*;
import main.database.*;
import main.view.*;
import java.util.List;


public class testframe extends JFrame{
    User user;
    private List<Product> products = DatabaseManager.getAllProducts();
    private List<Product> foodProducts = DatabaseManager.getProductsByCategory("Food");
    private List<Product> DrinkProducts = DatabaseManager.getProductsByCategory("Drink");
    private List<String> OrderName = new ArrayList<>();
    private List<Double> OrderPrice = new ArrayList<>();
    public testframe(User user){
        this.user = user;
        setTitle("Dashboard");
        setUndecorated(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 1. Root Container (Red Area)
        JPanel mainContainer = new JPanel(new GridBagLayout());
        mainContainer.setBackground(new Color(180, 0, 0)); 

        // 2. The Fixed Canvas (New 1400x788 Size)
        int CW = 1400; 
        int CH = 788;  
        
        JPanel canvas = new JPanel(null); 
        canvas.setPreferredSize(new Dimension(CW, CH));
        canvas.setBackground(Color.WHITE);

       // --- PANEL 1: HEADER (Blue) ---
        JPanel header = new JPanel(null);
        header.setBackground(Color.BLUE); 
        header.setBounds(30, 20, 780, 90);
        canvas.add(header);

        //Company Name
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
        staffText.setBounds(660, 20, 100, 50);
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
        sidebar.setBounds(30, 130, 120, 630); // Tall and slim
        canvas.add(sidebar);

        JLabel menuTitle = new JLabel("Menu", SwingConstants.CENTER);
        menuTitle.setFont(new Font("SansSerif", Font.BOLD, 28));
        menuTitle.setBounds(0, 10, 120, 40);
        sidebar.add(menuTitle);

        ImageIcon rawmenuIcon = new ImageIcon("src/main/image/menuicon.png");
        Image menuimg = rawmenuIcon.getImage();
  
        Image scaledmenuImg = menuimg.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        
        JLabel menuiconLabel = new JLabel(new ImageIcon(scaledmenuImg));
        

        menuiconLabel.setBounds(10, 60, 100, 100);
        menuiconLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        menuiconLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                JOptionPane.showMessageDialog(null, "Menu button pressed!", "Menu Button", JOptionPane.INFORMATION_MESSAGE);

            }

            @Override
            public void mouseEntered(MouseEvent e) {
            
                menuiconLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
            
                menuiconLabel.setText("<html><u>Staff</u></html>");
            }

            @Override
            public void mouseExited(MouseEvent e) {
            
            }
        });

        ImageIcon rawfoodIcon = new ImageIcon("src/main/image/foodicon.png");
        Image foodimg = rawfoodIcon.getImage();
  
        Image scaledfoodImg = foodimg.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        
        JLabel foodiconLabel = new JLabel(new ImageIcon(scaledfoodImg));
        

        foodiconLabel.setBounds(10, 155, 100, 100);
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
            public void mouseExited(MouseEvent e) {
            
            }
        });

        ImageIcon rawdrinkIcon = new ImageIcon("src/main/image/drinkicon.png");
        Image drinkimg = rawdrinkIcon.getImage();
  
        Image scaleddrinkImg = drinkimg.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        
        JLabel drinkiconLabel = new JLabel(new ImageIcon(scaleddrinkImg));
        

        drinkiconLabel.setBounds(10, 250, 100, 100);
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
            public void mouseExited(MouseEvent e) {
            
            }
        });

        ImageIcon rawMealIcon = new ImageIcon("src/main/image/mealicon.png");
        Image mealimg = rawMealIcon.getImage();
  
        Image scaledmealImg = mealimg.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        
        JLabel mealiconLabel = new JLabel(new ImageIcon(scaledmealImg));
        

        mealiconLabel.setBounds(10, 345, 100, 100);
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
            public void mouseExited(MouseEvent e) {
            
            }
        });

        ImageIcon rawSalesIcon = new ImageIcon("src/main/image/salesicon.png");
        Image salesimg = rawSalesIcon.getImage();
  
        Image scaledsalesImg = salesimg.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        
        JLabel salesiconLabel = new JLabel(new ImageIcon(scaledsalesImg));
        

        salesiconLabel.setBounds(10, 440, 100, 100);
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
            public void mouseExited(MouseEvent e) {
            
            }
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
        MenuContainer.setBounds(160, 130, 650, 630);

        JTextField searchField = new JTextField(" Search items...");
        searchField.setFont(new Font("SansSerif", Font.ITALIC, 16));
        searchField.setBounds(25, 30, 480, 40); // Positioned above the yellow scroll area
        searchField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200)),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));

        // Add a "Search" Button next to the field
        JButton searchButton = new JButton("Search");
        searchButton.setBounds(515, 30, 110, 40);
        searchButton.setBackground(new Color(255, 204, 0));
        searchButton.setFocusPainted(false);
        searchButton.setFont(new Font("SansSerif", Font.BOLD, 14));

        // Add them to the MenuContainer
        MenuContainer.add(searchField);
        MenuContainer.add(searchButton);
        
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
            //counter++;
            //if(counter > 30){
                //break;
            //}
        }


        MenuContainer.add(scrollPane);


        
        canvas.add(MenuContainer);

        // --- PANEL 5: ORDER DETAILS (Green) ---
        // Stretched to fill the right side of the 1400 canvas
        JPanel orderJPanel = new JPanel(null);
        orderJPanel.setBackground(Color.GREEN);
        orderJPanel.setBounds(830, 20, 540, 740); // Large right-hand panel
        canvas.add(orderJPanel);

        // Final assembly
        mainContainer.add(canvas);
        this.setContentPane(mainContainer);

    
        
        setVisible(true);
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
            new testframe(testUser);
        }
}
