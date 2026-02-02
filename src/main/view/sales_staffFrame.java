package main.view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class sales_staffFrame extends JFrame {


    // for dummy
    static class User {
        private String username;
        private String role;
        
   

        public User() {
            this.username = "staff sales user";
            this.role = "Sales Staff";
        }

        public String getUsername() {
            return username;
        }

        public String getRole() {
            return role;
        }
    }

    private main.model.User user; 

    public sales_staffFrame(main.model.User user) {
        this.user = user; 
    

      

        setTitle("Sales Frame");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int CW = screenSize.width;
        int CH = screenSize.height;

        setSize(CW, CH);
        setLocationRelativeTo(null);

        final Image bgImage = new ImageIcon(
                "src/main/image/Admin Main Frame (1).png"
        ).getImage();

        // BG IMAGEEE 
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

        //  HEADER 

        JPanel header = new JPanel(null);
        header.setBounds(30, 32, 1500, 90);
        header.setOpaque(false);
        canvas.add(header);

        JLabel companyName = new JLabel("Company Name");
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
        JLabel staffText = new JLabel("STAFF");
        staffText.setFont(new Font("SansSerif", Font.BOLD, 26));
        staffText.setForeground(new Color(255, 204, 0));
        staffText.setBounds(1300, 25, 400, 50);
        staffText.setCursor(new Cursor(Cursor.HAND_CURSOR));
        staffText.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
              // UserDetailFrame userDetailFrame = new UserDetailFrame(user);
              // userDetailFrame.setVisible(true);
               new UserDetailFrame(user).setVisible(true);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                staffText.setCursor(new Cursor(Cursor.HAND_CURSOR));
                staffText.setText("<html><u>Staff</u></html>");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                staffText.setText("STAFF");
            }
        });
        header.add(staffText);
        header.setComponentZOrder(staffLogo, 0);


        header.add(staffText);

        mainContainer.add(canvas);
        setContentPane(mainContainer);
        setVisible(true);
////
//////

/////

/////
///

  //SALES DASHBOARD PANEL 

        JPanel dashpanel = new JPanel(null) {
            private Image sImage = new ImageIcon("src/main/image/salesdash.png").getImage();
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (sImage != null) {
                    g.drawImage(sImage, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };
        dashpanel.setBounds(196, 131, 1211, 804);
        dashpanel.setOpaque(false);
        dashpanel.setVisible(true); 

        // Dashboard Header Image
        JPanel head4 = new JPanel(null) {
            private Image listBg = new ImageIcon("src/main/image/dash_txt.png").getImage();
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (listBg != null) {
                    g.drawImage(listBg, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };
        head4.setBounds(20, 20, 300, 76);
        head4.setOpaque(false); 
        dashpanel.add(head4);

        // DASHBOARD LABELS
         JLabel tranlabel = new JLabel("Best Selling");
        tranlabel.setFont(new Font("Arial", Font.BOLD, 30));
        tranlabel.setForeground(Color.BLACK);
        tranlabel.setBounds(108 , 126, 300, 60);
        dashpanel.add(tranlabel);

        JLabel onlinestafflabel = new JLabel("Transactions Made");
        onlinestafflabel.setFont(new Font("Arial", Font.BOLD, 30));
        onlinestafflabel.setForeground(Color.BLACK);
        onlinestafflabel.setBounds(430, 126, 300, 60);
        dashpanel.add(onlinestafflabel);

        JLabel totalsaleslabel = new JLabel("Total Sales: ");
        totalsaleslabel.setFont(new Font("Arial", Font.BOLD, 34));
        totalsaleslabel.setForeground(Color.BLACK);
        totalsaleslabel.setBounds(799, 140, 300, 60);
        dashpanel.add(totalsaleslabel);

       

     
       
        canvas.add(dashpanel);
        mainContainer.add(canvas);
        setContentPane(mainContainer);

      
        setVisible(true);
    

   
   // PANEL 3: SIDEBAR

        JPanel sidebar = new JPanel(null);
        sidebar.setBounds(30, 130, 169, 800);
        sidebar.setForeground(Color.ORANGE);
        sidebar.setOpaque(false);
        canvas.add(sidebar); 

        // MAIN MENU BUTTON 
        ImageIcon menuIcon = new ImageIcon("src/main/image/menu (1).png");
        JLabel menuiconLabel = new JLabel(menuIcon);
        menuiconLabel.setBounds(20, 110, menuIcon.getIconWidth(), menuIcon.getIconHeight());
        menuiconLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        menuiconLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
             
           DB_Staff db_Staff = new DB_Staff(null);
          db_Staff.setVisible(true); 
          sales_staffFrame.this.dispose(); 



    }
});
        sidebar.add(menuiconLabel);

        // SNACK BTN 
        ImageIcon rawfoodIcon = new ImageIcon("src/main/image/burger (1).png");
        JLabel foodiconLabel = new JLabel(rawfoodIcon);
        foodiconLabel.setBounds(20, 230, rawfoodIcon.getIconWidth(), rawfoodIcon.getIconHeight());
        foodiconLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        foodiconLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
               
                 DB_Staff db_Staff = new DB_Staff(null);
              db_Staff.setVisible(true); 
              sales_staffFrame.this.dispose(); 

            }
        });
        sidebar.add(foodiconLabel);

        // DRINK BUTTON
        ImageIcon rawdrinkIcon = new ImageIcon("src/main/image/drink (1).png");
        JLabel drinkiconLabel = new JLabel(rawdrinkIcon);
        drinkiconLabel.setBounds(25, 350, rawdrinkIcon.getIconWidth(), rawdrinkIcon.getIconHeight());
        drinkiconLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        drinkiconLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                
                DB_Staff db_Staff = new DB_Staff(null);
               db_Staff.setVisible(true); 
               sales_staffFrame.this.dispose(); 

            }
        });
        sidebar.add(drinkiconLabel);

        // MEAL BTN
        ImageIcon rawMealIcon = new ImageIcon("src/main/image/meal (1).png");
        JLabel mealiconLabel = new JLabel(rawMealIcon);
        mealiconLabel.setBounds(20, 460, rawMealIcon.getIconWidth(), rawMealIcon.getIconHeight());
        mealiconLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        mealiconLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
            DB_Staff db_Staff = new DB_Staff(null);
               db_Staff.setVisible(true); 
               sales_staffFrame.this.dispose(); 
            }
        });
        sidebar.add(mealiconLabel);

        // DASH BUTTON  BTN ICON
        ImageIcon rawSalesIcon = new ImageIcon("src/main/image/saless (1).png");
        JLabel salesiconLabel = new JLabel(rawSalesIcon);
        salesiconLabel.setBounds(20, 590, rawSalesIcon.getIconWidth(), rawSalesIcon.getIconHeight());
        salesiconLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        salesiconLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                DB_Staff db_Staff = new DB_Staff(null);
               db_Staff.setVisible(true); 
               sales_staffFrame.this.dispose(); 
            }
        });
        sidebar.add(salesiconLabel); 

       
        mainContainer.add(canvas);
        setContentPane(mainContainer);
        setVisible(true);
    } 



    private void addDashboardLabel(JPanel panel, String text, int x, int y) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.BOLD, 20));
        label.setForeground(Color.BLACK);
        label.setBounds(x, y, 150, 30);
        panel.add(label);
    }
      
       


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                main.model.User dummy = null;
                new sales_staffFrame(dummy);
            }
        });
    }
}





