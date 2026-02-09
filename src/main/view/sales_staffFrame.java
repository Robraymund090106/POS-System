package main.view;

import java.awt.*;
import java.awt.event.*;
import java.util.Map;

import javax.swing.*;
import main.model.*;
import main.database.*;
import java.util.List;
public class sales_staffFrame extends JFrame {



    private User user; 
    private JPanel bestsellingpanel;
    private JPanel transPanel;
    private JLabel totalsaleslabel;


    public sales_staffFrame(User user) {
        this.user = user; 

        this.addWindowFocusListener(new WindowAdapter() {
    @Override
    public void windowGainedFocus(WindowEvent e) {
        refreshDashboard();
    }
});
    

      

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
        header.setBounds(30, 2, 1500, 150);
        header.setOpaque(false);
        canvas.add(header);

        JLabel companyName = new JLabel("N.U.C.M.S.");
        companyName.setFont(new Font("SansSerif", Font.BOLD, 50));
        companyName.setForeground(new Color(255, 204, 0));
        companyName.setBounds(12, 50, 350, 50);
        header.add(companyName);

      //logo
     ImageIcon originalIcon = new ImageIcon("src/main/image/logo.png");
     Image scaledImage = originalIcon.getImage().getScaledInstance(150, 100, Image.SCALE_SMOOTH);
     ImageIcon finalIcon = new ImageIcon(scaledImage);
     JLabel adjustableImage = new JLabel(finalIcon);
     adjustableImage.setBounds(289, 0, 147, 130);

       header.add(adjustableImage);

       // staff logo (image)
        ImageIcon rawIcon = new ImageIcon("src/main/image/stafflogo.png"); 
        Image img = rawIcon.getImage();
        Image scaledImg = img.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        ImageIcon staffLogoIcon = new ImageIcon(scaledImg);

        JLabel staffLogo = new JLabel("", staffLogoIcon, JLabel.LEFT);
        staffLogo.setBounds(1238, 60, 180, 50); 

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
        staffText.setBounds(1300, 60, 400, 50);
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
       // dashpanel.setBounds(196, 80, 1211, 804);

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

        JLabel totalsaleslabel = new JLabel("Total Sales");
        totalsaleslabel.setFont(new Font("Arial", Font.BOLD, 30));
        totalsaleslabel.setForeground(Color.BLACK);
        totalsaleslabel.setBounds(870, 126, 300, 60);
        dashpanel.add(totalsaleslabel);

        JPanel bestsellingpanel = new JPanel();
        bestsellingpanel.setLayout(new BoxLayout(bestsellingpanel, BoxLayout.Y_AXIS));
        //bestsellingpanel.setBackground(Color.WHITE);
        bestsellingpanel.setOpaque(false);

        JScrollPane scrollPane = new JScrollPane(bestsellingpanel);
        scrollPane.setBounds(50, 200, 280, 550); // Matches your layout
        scrollPane.setBorder(null); // Optional: makes it look cleaner
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);

        // Fetch the Top 10
        Map<String, Integer> top10 = DatabaseManager.getTop10Bestsellers();

        int rank = 1;
        for (Map.Entry<String, Integer> entry : top10.entrySet()) {
            JPanel row = new JPanel(new BorderLayout());
            row.setMaximumSize(new Dimension(280, 45));
            row.setBackground(Color.WHITE);
            row.setOpaque(false);
            row.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));

            // Styling the Rank
            String rankText = (rank == 1) ? "🏆 " + rank : String.valueOf(rank);
            JLabel nameLabel = new JLabel("  " + rankText + ". " + entry.getKey());
            nameLabel.setFont(new Font("SansSerif", rank == 1 ? Font.BOLD : Font.PLAIN, 16));
            
            // Styling the Quantity
            JLabel qtyLabel = new JLabel(entry.getValue() + " sold  ");
            qtyLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
            qtyLabel.setForeground(new Color(34, 139, 34)); // Forest Green

            row.add(nameLabel, BorderLayout.WEST);
            row.add(qtyLabel, BorderLayout.EAST);
            
            bestsellingpanel.add(row);
            rank++;
        }

    
        dashpanel.add(scrollPane);
        canvas.add(dashpanel);
        mainContainer.add(canvas);
        setContentPane(mainContainer);

        JPanel transPanel = new JPanel();
        transPanel.setLayout(new BoxLayout(transPanel, BoxLayout.Y_AXIS));
        transPanel.setBackground(Color.WHITE);
        transPanel.setOpaque(false);

 
        JScrollPane transScroll = new JScrollPane(transPanel);
        transScroll.setBounds(420, 180, 300, 550); // Aligned under "Transactions Made"
        transScroll.setBorder(null);
        transScroll.setOpaque(false);
        transScroll.getViewport().setOpaque(false);

        List<String[]> history = DatabaseManager.getDetailedTransactions(user.getUserId());

        for (String[] entry : history) {
    JPanel row = new JPanel(new BorderLayout());
    
    // This prevents the row from becoming shorter than 45 pixels
    row.setMinimumSize(new Dimension(280, 45)); 
    row.setPreferredSize(new Dimension(280, 45));
    row.setMaximumSize(new Dimension(Short.MAX_VALUE, 45)); 
    
    row.setOpaque(false);
    row.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));

    // Name and Qty
    JLabel itemLabel = new JLabel("<html><b>" + entry[0] + "</b> (x" + entry[1] + ")</html>");
    itemLabel.setFont(new Font("SansSerif", Font.PLAIN, 13));

    // Date and Time (e.g., 02-02 22:30)
    // We use substring(5) to skip the year "2026-" to save space
    String displayTime = entry[2].substring(5); 
    JLabel dateLabel = new JLabel(displayTime); 
    dateLabel.setFont(new Font("SansSerif", Font.PLAIN, 11));
    dateLabel.setForeground(Color.DARK_GRAY);

    row.add(itemLabel, BorderLayout.WEST);
    row.add(dateLabel, BorderLayout.EAST);

    transPanel.add(Box.createRigidArea(new Dimension(0, 5)));
    transPanel.add(row);
    
}



dashpanel.add(transScroll);

JLabel daysalesText = new JLabel("This Day's Sales: ₱" + DatabaseManager.getTotalSalesByPeriod(user.getUsername(), "day"));
daysalesText.setFont(new Font("SansSerif", Font.BOLD, 22));
daysalesText.setForeground(Color.BLACK);
daysalesText.setBounds(790, 200, 700, 50);
dashpanel.add(daysalesText);

JLabel weeksalesText = new JLabel("This Week's Sales: ₱" + DatabaseManager.getTotalSalesByPeriod(user.getUsername(), "week"));
weeksalesText.setFont(new Font("SansSerif", Font.BOLD, 22));
weeksalesText.setForeground(Color.BLACK);
weeksalesText.setBounds(790, 330, 700, 50);
dashpanel.add(weeksalesText);

JLabel monthsalesText = new JLabel("This Month's Sales: ₱" + DatabaseManager.getTotalSalesByPeriod(user.getUsername(), "month"));
monthsalesText.setFont(new Font("SansSerif", Font.BOLD, 22));
monthsalesText.setForeground(Color.BLACK);
monthsalesText.setBounds(790, 470, 700, 50);
dashpanel.add(monthsalesText);

JButton submitbutton = new JButton("Submit Sales");
submitbutton.setFont(new Font("Arial", Font.BOLD, 20));
submitbutton.setBackground(new Color(165, 215, 155));
submitbutton.setForeground(Color.WHITE);
submitbutton.setBounds(849, 600, 200, 50);

submitbutton.addActionListener(e -> {
    int warning = JOptionPane.showConfirmDialog(
        null, 
        "Submitting your report will end your shift. Do you still wish to continue?", 
        "End Shift Warning", 
        JOptionPane.YES_NO_OPTION, 
        JOptionPane.WARNING_MESSAGE
    );

    if (warning != JOptionPane.YES_OPTION) return;

    JTextArea notesArea = new JTextArea(5, 20);
    notesArea.setLineWrap(true);
    notesArea.setWrapStyleWord(true);
    JScrollPane scrollPane1 = new JScrollPane(notesArea);
    
    int result = JOptionPane.showConfirmDialog(null, scrollPane1, 
            "Enter Staff Report Notes", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

    if (result == JOptionPane.OK_OPTION) {
        String notes = notesArea.getText().trim();
        if (notes.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Submission failed: Notes cannot be empty.");
            return;
        }

        // 1. Submit the report and finalize shift in DB
        boolean success = DatabaseManager.submitStaffReport(user.getUserId(), notes);

        if (success) {
            // 2. Fetch the shift details we just closed to show the summary
            String[] shiftDetails = DatabaseManager.getLastClosedShiftDetails(user.getUserId());
            
            if (shiftDetails != null) {
                String summary = String.format(
                    "📊 SHIFT SUMMARY\n\n" +
                    "Start Time: %s\n" +
                    "End Time:   %s\n" +
                    "Total Time: %s",
                    shiftDetails[0], shiftDetails[1], shiftDetails[2]
                );
                
                JOptionPane.showMessageDialog(null, summary, "Shift Finalized", JOptionPane.INFORMATION_MESSAGE);
            }

            submitbutton.setEnabled(false);
            submitbutton.setBackground(Color.GRAY);
        } else {
            JOptionPane.showMessageDialog(null, "Error: No active shift found or database error.");
        }
    }
});
dashpanel.add(submitbutton);





      
        
    

   
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
             
           new DB_Staff(user);
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
               
                 new DB_Staff(user);
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
                
                new DB_Staff(user);
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
            new DB_Staff(user);
               dispose(); 
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
                new DB_Staff(user);
               dispose(); 
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

    public void refreshDashboard() {
    // 1. Clear existing components to avoid duplicates
    bestsellingpanel.removeAll();
    transPanel.removeAll();

    // 2. Re-fetch and re-populate Best Sellers
    Map<String, Integer> top10 = DatabaseManager.getTop10Bestsellers();
    int rank = 1;
    for (Map.Entry<String, Integer> entry : top10.entrySet()) {
        // ... (Insert your existing row creation logic here) ...
        rank++;
    }

    // 3. Re-fetch and re-populate Transactions
    List<String[]> history = DatabaseManager.getDetailedTransactions(user.getUserId());
    for (String[] entry : history) {
        // ... (Insert your existing row creation logic here) ...
    }
    
    // 4. Update the Total Sales Label
    //double totalSalesVal = DatabaseManager.getUserTotalSales(user.getUserId());
    //totalsaleslabel.setText("Total Sales: ₱" + String.format("%.2f", totalSalesVal));

    // 5. Tell Swing to redraw the UI
    bestsellingpanel.revalidate();
    bestsellingpanel.repaint();
    transPanel.revalidate();
    transPanel.repaint();
}
      
       


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                User testUser = new User("rob", "hi", "raymundo", "Male", null, null, "staff", true, 00, 19);
                new sales_staffFrame(testUser);
            }
        });
    }
}





