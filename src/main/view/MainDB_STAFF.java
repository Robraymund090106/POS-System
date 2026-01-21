package main.view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import main.database.DatabaseManager;
import main.model.*;

import java.util.ArrayList;
import java.util.List;



public class MainDB_STAFF extends JFrame {
    private User user;

    private List<Product> products = DatabaseManager.getAllProducts();
    private List<Product> foodProducts = DatabaseManager.getProductsByCategory("Food");
    private List<Product> DrinkProducts = DatabaseManager.getProductsByCategory("Drink");
    private List<String> OrderName = new ArrayList<>();
    private List<Double> OrderPrice = new ArrayList<>();


    public MainDB_STAFF (User user) {
        this.user = user;
        setTitle("Dashboard");
        setExtendedState(JFrame.MAXIMIZED_BOTH); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainContainer = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon imageIcon = new ImageIcon("src\\main\\image\\Email (13).png");
                Image img = imageIcon.getImage();
                if (img != null) {
                    g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };
        mainContainer.setLayout(null);

     
        JLabel companyNameLabel = new JLabel("Company name");
        companyNameLabel.setFont(new Font("SansSerif", Font.BOLD, 50));
        companyNameLabel.setForeground(Color.WHITE);
        companyNameLabel.setBounds(80, 40, 400, 70); 
        mainContainer.add(companyNameLabel);

        JLabel Odtails = new JLabel("Order Details");
        Odtails.setFont(new Font("SansSerif", Font.PLAIN, 40));
        Odtails.setForeground(Color.BLACK);
        Odtails.setBounds(960, 58, 500, 50); 
        mainContainer.add(Odtails);

        JLabel CstmOrd = new JLabel("Current Order: ");
       CstmOrd.setFont(new Font("SansSerif", Font.PLAIN, 29));
       CstmOrd.setForeground(Color.BLACK);
       CstmOrd.setBounds(960, 150, 500, 50); 
        mainContainer.add(CstmOrd);

         JLabel Total = new JLabel("Total: ");
      Total.setFont(new Font("SansSerif", Font.BOLD, 29));
      Total.setForeground(Color.BLACK);
      Total .setBounds(970, 640, 500, 50); 
        mainContainer.add(Total);


        
// image button cilckabless 

      // 1st IMAGE BUTTON
        ImageIcon staffIcon = new ImageIcon("src\\main\\image\\Email (4) (1).png"); 
        JLabel clickableImage = new JLabel(staffIcon);
        clickableImage.setHorizontalAlignment(JLabel.CENTER); 
      
        int imgW = staffIcon.getIconWidth();
        int imgH = staffIcon.getIconHeight();
        
        clickableImage.setBounds(20, 70, imgW, imgH); 
        
        clickableImage.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                clickableImage.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
        });
        
        mainContainer.add(clickableImage);
        mainContainer.setComponentZOrder(clickableImage, 0);

// 2nd IMAGE BUTTON
       ImageIcon secondIcon = new ImageIcon("src\\main\\image\\Email (7) (1).png"); 
       JLabel secondClickable = new JLabel(secondIcon);
       secondClickable.setHorizontalAlignment(JLabel.CENTER); 

       int imgW2 = secondIcon.getIconWidth();
       int imgH2 = secondIcon.getIconHeight();

       secondClickable.setBounds(20, 198, imgW2, imgH2); 

      
    mainContainer.add(secondClickable);
    mainContainer.setComponentZOrder(secondClickable, 0);

// 3rd IMAGE BUTTON
  ImageIcon thirdIcon = new ImageIcon("src\\main\\image\\Email (2).png");
  JLabel thirdClickable = new JLabel(thirdIcon);
  thirdClickable.setHorizontalAlignment(JLabel.CENTER); 
  int imgW3 = thirdIcon.getIconWidth();
  int imgH3 = thirdIcon.getIconHeight();

thirdClickable.setBounds(20, 139, imgW3, imgH3); 

thirdClickable.addMouseListener(new MouseAdapter() {
    @Override
    public void mousePressed(MouseEvent e) {
        // ACTION FOR 3rd BUTTON
    }
    @Override
    public void mouseEntered(MouseEvent e) {
        thirdClickable.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
});

   mainContainer.add(thirdClickable);
   mainContainer.setComponentZOrder(thirdClickable, 0);

//  4th IMAGE BUTTON 
  ImageIcon fourthIcon = new ImageIcon("src\\main\\image\\Email (10) (1).png");
  JLabel fourthClickable = new JLabel(fourthIcon);
  fourthClickable.setHorizontalAlignment(JLabel.CENTER); 
  int imgW4 = fourthIcon.getIconWidth();
  int imgH4 = fourthIcon.getIconHeight();

   fourthClickable.setBounds(20, 459, imgW4, imgH4); 

  fourthClickable.addMouseListener(new MouseAdapter() {
    @Override
    public void mousePressed(MouseEvent e) {
        // ACTION FOR 4th BUTTON
    }
    @Override
    public void mouseEntered(MouseEvent e) {
        fourthClickable.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
});

mainContainer.add(fourthClickable);
mainContainer.setComponentZOrder(fourthClickable, 0);

//  5th IMAGE BUTTON 
ImageIcon fifthIcon = new ImageIcon("src\\main\\image\\Email (9) (1).png"); 
JLabel fifthClickable = new JLabel(fifthIcon);
fifthClickable.setHorizontalAlignment(JLabel.CENTER); 

int imgW5 = fifthIcon.getIconWidth();
int imgH5 = fifthIcon.getIconHeight();

fifthClickable.setBounds(20, 590, imgW5, imgH5); 

fifthClickable.addMouseListener(new MouseAdapter() {
    @Override
    public void mousePressed(MouseEvent e) {
        // ACTION FOR 5th BUTTON
    }
    @Override
    public void mouseEntered(MouseEvent e) {
        fifthClickable.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
});

mainContainer.add(fifthClickable);
mainContainer.setComponentZOrder(fifthClickable, 0);



//  STAFF IMAGE BUTTON CLICKABLE 
ImageIcon sixthIcon = new ImageIcon("src\\main\\image\\Email (14) (1).png"); 
JLabel sixthClickable = new JLabel(sixthIcon);
sixthClickable.setHorizontalAlignment(JLabel.CENTER); 

int imgW6 = sixthIcon.getIconWidth();
int imgH6 = sixthIcon.getIconHeight();


sixthClickable.setBounds(0, 50, imgW6, imgH6); 

//  6th IMAGE BUTTON staff pic
sixthClickable.addMouseListener(new MouseAdapter() {
    @Override
    public void mousePressed(MouseEvent e) {
        // Action HEREE
    } 
    @Override
    public void mouseEntered(MouseEvent e) {
        sixthClickable.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
}); 
mainContainer.add(sixthClickable);
mainContainer.setComponentZOrder(sixthClickable, 0);

//  7th IMAGE BUTTON current order button 
ImageIcon seventhIcon = new ImageIcon("src\\main\\image\\Email (15) (1).png"); 
JLabel seventhClickable = new JLabel(seventhIcon);
seventhClickable.setHorizontalAlignment(JLabel.CENTER); 

int imgW7 = seventhIcon.getIconWidth();
int imgH7 = seventhIcon.getIconHeight();


seventhClickable.setBounds(290, 52, imgW7, imgH7); 

seventhClickable.addMouseListener(new MouseAdapter() {
    @Override
    public void mousePressed(MouseEvent e) {
      // ACTION HEREE
      
    }
    @Override
    public void mouseEntered(MouseEvent e) {
        seventhClickable.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
});

mainContainer.add(seventhClickable);
mainContainer.setComponentZOrder(seventhClickable, 0);


//  8th IMAGE BUTTON place order  button 
ImageIcon eightIcon = new ImageIcon("src\\main\\image\\Email (16) (1).png"); 
JLabel eightClickable = new JLabel(eightIcon);
eightClickable.setHorizontalAlignment(JLabel.CENTER); 

int imgW8 = seventhIcon.getIconWidth();
int imgH8 = seventhIcon.getIconHeight();


eightClickable.setBounds(300, 250, imgW8, imgH8); 

eightClickable.addMouseListener(new MouseAdapter() {
    @Override
    public void mousePressed(MouseEvent e) {
      // ACTION HEREE
    }
    @Override
    public void mouseEntered(MouseEvent e) {
        eightClickable.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
});






JPanel menuPanel = new JPanel();
menuPanel.setBackground(Color.WHITE);

menuPanel.setLayout(new GridLayout(0, 3, 20, 20)); 


JScrollPane scrollPane = new JScrollPane(menuPanel);
scrollPane.setBounds(280, 200, 650, 500); 
scrollPane.setBorder(null); 
scrollPane.getViewport().setBackground(Color.WHITE);

// 3. Add sample product cards (You can loop your database results here)
//int counter = 0;
for (Product p : products) {

    menuPanel.add(createProductCard(p.getName(), p.getPrice()));
    //counter++;
    //if(counter > 30){
        //break;
    //}
}

mainContainer.add(scrollPane);

mainContainer.add(eightClickable);
mainContainer.setComponentZOrder(eightClickable, 0);
mainContainer.setComponentZOrder(scrollPane, 0);

secondClickable.addMouseListener(new MouseAdapter() {
        @Override
        public void mousePressed(MouseEvent e) {
            menuPanel.removeAll(); // Clear current cards
        
        for (Product p : foodProducts) {
            menuPanel.add(createProductCard(p.getName(), p.getPrice()));
        }
        menuPanel.revalidate();
        menuPanel.repaint();

        } 

    @Override
    public void mouseEntered(MouseEvent e) {
        secondClickable.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
});





 
// STAFF TXT CLICKABLIE
JLabel textButton = new JLabel("Staff");
textButton.setFont(new Font("SansSerif", Font.BOLD, 40)); 
textButton.setForeground(Color.WHITE); 

textButton.setBounds(780, 67, 200, 50); 


textButton.addMouseListener(new MouseAdapter() {
    @Override
    public void mousePressed(MouseEvent e) {
        UserDetailFrame userDetailFrame = new UserDetailFrame(user);
        userDetailFrame.setVisible(true);
        // ACTION !!!

       
    }

    @Override
    public void mouseEntered(MouseEvent e) {
       
        textButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
       
        textButton.setText("<html><u>Staff</u></html>");
    }

    @Override
    public void mouseExited(MouseEvent e) {
      
        textButton.setText("Staff");
    }
});

mainContainer.add(textButton);

mainContainer.setComponentZOrder(textButton, 0);

        // search barr
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
        searchField.setBorder(new EmptyBorder(0, 45, 0, 10)); 
        searchField.setForeground(Color.BLACK);
        searchField.setFont(new Font("SansSerif", Font.PLAIN, 25));
        searchField.setBounds(279, 153, 350, 30); 
        searchField.setCaretColor(new Color(0, 0, 0, 0)); 
        searchField.setFocusable(false);

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

        // D2 lagay ang acction/logic kpag nag press si user ng ENTER !
        searchField.addActionListener(e -> System.out.println("Searching: " + searchField.getText()));

        mainContainer.add(searchField);
        
        mainContainer.setComponentZOrder(searchField, 0);

        this.setContentPane(mainContainer);
        this.revalidate();
        this.repaint();
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

    private JLabel createHotspot(int x, int y, int w, int h, MouseAdapter listener) {
    JLabel hotspot = new JLabel();
    hotspot.setBounds(x, y, w, h);
    hotspot.setCursor(new Cursor(Cursor.HAND_CURSOR));
    hotspot.addMouseListener(listener);
    
    
    hotspot.setBorder(BorderFactory.createLineBorder(Color.RED)); 
    
    return hotspot;
}
        
}