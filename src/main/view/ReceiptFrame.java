package main.view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;      
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;   
import javax.swing.ImageIcon;
import javax.swing.JButton;        
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SwingUtilities;
import main.model.User;


public class ReceiptFrame extends JFrame {
    User user;
    JPanel canvas; 

    public ReceiptFrame(User user) {

        this.user = user;
        setTitle("Receipt");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setUndecorated(false);
        this.setResizable(false);
        setVisible(true);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int CW = screenSize.width;
        int CH = screenSize.height;
        setSize(CW, CH);
        setLocationRelativeTo(null);

        final Image bgImage = new ImageIcon("src/main/image/Staff Place Order Frame  .png").getImage();

        
        JPanel mainContainer = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(bgImage, 0, 0, getWidth(), getHeight(), this);
            }
        };

     
        canvas = new JPanel(null);
        canvas.setPreferredSize(new Dimension(CW, CH));
        canvas.setOpaque(false); 

        //  PANELSSSSSS
        
      // --- PANEL 1: Header (Blue) ---
        JPanel header = new JPanel(null);
        header.setBackground(Color.BLUE); 
        header.setBounds(30, 30, 780, 90);
        header.setOpaque(false);
        
        JLabel companyName = new JLabel("Company name");
        companyName.setFont(new Font("SansSerif", Font.BOLD, 35));
        companyName.setForeground(new Color(58, 83, 155));
        companyName.setBounds(55, 29, 343, 50); 
        header.add(companyName);
        canvas.add(header);

        // PANEL 2: FOR RECEIPT 
        JPanel detailsPanel = new JPanel(null);
        detailsPanel.setBackground(Color.CYAN);
        detailsPanel.setBounds(60, 132, 817, 646); 
        detailsPanel.setOpaque(true);
        canvas.add(detailsPanel);

        //  PANEL 3: ORANGE // cancel order

      JPanel thinRightTop = new JPanel(null);
      thinRightTop.setBackground(Color.ORANGE); 
      thinRightTop.setBounds(900, 32, 600, 45); 
      thinRightTop.setOpaque(false); 


    JLabel CnclOr = new JLabel("Cancel Order");
    CnclOr.setFont(new Font("SansSerif", Font.BOLD, 20));
    CnclOr.setForeground(new Color(225, 100, 100)); 
    CnclOr.setBounds(329, 0, 343, 45); 

    CnclOr.setCursor(new Cursor(Cursor.HAND_CURSOR)); 

// ACTION
    CnclOr.addMouseListener(new java.awt.event.MouseAdapter() {
    @Override
    public void mouseClicked(java.awt.event.MouseEvent e) {
        
        JOptionPane.showMessageDialog(null, 
            "Cancel Order Initiated. Administrator authorization is required.", 
            "Void Order", 
            JOptionPane.INFORMATION_MESSAGE);

         // Admin Password 
        JPasswordField pf = new JPasswordField();
        int okCxl = JOptionPane.showConfirmDialog(null, pf, "Enter Admin Password", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (okCxl == JOptionPane.OK_OPTION) {
            String password = new String(pf.getPassword());
            if (password.equals("admin123")) { // ginamit ko pass nung unang admin usert natin 
                JOptionPane.showMessageDialog(null, "Order Successfully Canceled.");
        
            } else {
                JOptionPane.showMessageDialog(null, "Invalid Password", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }


   
    
});

   thinRightTop.add(CnclOr);
   canvas.add(thinRightTop);

        //  NEW PANEL 4: // FOR TOTAL PRICE OF ORDER ITEMS 
        JPanel TPanel = new JPanel(null);
        TPanel.setBackground(Color.MAGENTA); 
        TPanel.setBounds(920, 190, 500, 90); 
        TPanel.setOpaque(false);

        JLabel Total = new JLabel("Total:");
        Total.setFont(new Font("SansSerif", Font.BOLD, 30));
        Total.setBackground(Color.BLACK);
        Total.setBounds(55, 29, 343, 50); 
        TPanel.add(Total);
        canvas.add(TPanel);

      
        // --- PANEL 5: CALCU 
        JPanel middleRightPanel = new JPanel(null);

   middleRightPanel.setBackground(new Color(58, 83, 155)); 
   middleRightPanel.setBounds(943, 287, 420, 470); 
   middleRightPanel.setOpaque(true);

    //  CALCULATOR DISPLAY
  JLabel calcDisplay = new JLabel("0", JLabel.LEFT); 
  calcDisplay.setFont(new Font("SansSerif", Font.BOLD, 30));
  calcDisplay.setForeground(Color.WHITE); 


  calcDisplay.setBorder(BorderFactory.createCompoundBorder(
    BorderFactory.createLineBorder(Color.WHITE, 2),      
    BorderFactory.createEmptyBorder(0, 10, 0, 10)       
));

    calcDisplay.setBounds(15, 15, 320, 50);
    middleRightPanel.add(calcDisplay);

        //delete icon 
        ImageIcon deleteIcon = new ImageIcon("src/main/image/back_delete.png"); 
        Image scaledDelete = deleteIcon.getImage().getScaledInstance(40, 30, Image.SCALE_SMOOTH);
        JLabel backBtn = new JLabel(new ImageIcon(scaledDelete), JLabel.CENTER);
        
        backBtn.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
        backBtn.setBounds(345, 15, 60, 50);
        backBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        backBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String current = calcDisplay.getText();
                if (current.length() > 1) {
                    calcDisplay.setText(current.substring(0, current.length() - 1));
                } else {
                    calcDisplay.setText("0");
                }
            }
        });
        middleRightPanel.add(backBtn);

        
        String[] labels = {"7", "8", "9", "4", "5", "6", "1", "2", "3", ".", "0", "00"};
        int xStart = 15, yStart = 80;
        int btnW = 125, btnH = 85, gap = 12;

        for (int i = 0; i < labels.length; i++) {
            String text = labels[i];
            JButton btn = new JButton(text);
            btn.setFont(new Font("SansSerif", Font.BOLD, 22));
            btn.setFocusable(false);
            btn.setBackground(Color.WHITE);
            
            int row = i / 3;
            int col = i % 3;
            btn.setBounds(xStart + (col * (btnW + gap)), yStart + (row * (btnH + gap)), btnW, btnH);
            
            btn.addActionListener(e -> {
            
                if (calcDisplay.getText().equals("0")) {
                    calcDisplay.setText(text);
                } else {
                    calcDisplay.setText(calcDisplay.getText() + text);
                }
            });
            middleRightPanel.add(btn);
        }

        canvas.add(middleRightPanel);


       // PANEL 6: SMALL PANEL FOR PAY BUTTON 
           

  JPanel smallBottomRight = new JPanel(null);
  smallBottomRight.setBackground(Color.MAGENTA);
  smallBottomRight.setBounds(940, 782, 420, 120); 
  smallBottomRight.setOpaque(false);

  // IMAGE OF PAY BUTTON 
  ImageIcon payIcon = new ImageIcon("src/main/image/PAY (1).png"); 
  JLabel payBtnLabel = new JLabel(payIcon);

   // 2. POSITION THE IMAGE
  int payIconWidth = payIcon.getIconWidth();
  int payIconHeight = payIcon.getIconHeight();


payBtnLabel.setBounds((420 - payIconWidth) / 2, (120 - payIconHeight) / 2, payIconWidth, payIconHeight);

// 3. CLICK ACTION
    payBtnLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
    payBtnLabel.addMouseListener(new java.awt.event.MouseAdapter() {
    @Override
    public void mousePressed(java.awt.event.MouseEvent e) {
        JOptionPane.showMessageDialog(null, "This button is pressed", "Pay Action", JOptionPane.INFORMATION_MESSAGE);
    }
});

     smallBottomRight.add(payBtnLabel);
     canvas.add(smallBottomRight);




     // PANEL 7: SMALL PANEL FOR PRINT button
   JPanel printPanel = new JPanel(null);
   printPanel.setBackground(Color.MAGENTA);

  printPanel.setBounds(248, 794, 420, 120);
  printPanel.setOpaque(false);

  // Print Receipt Button
  ImageIcon printIcon = new ImageIcon("src/main/image/Print (1).png");
  JLabel printBtnLabel = new JLabel(printIcon);


   int iconWidth = printIcon.getIconWidth();
   int iconHeight = printIcon.getIconHeight();
   printBtnLabel.setBounds((420 - iconWidth) / 2, (120 - iconHeight) / 2, iconWidth, iconHeight);

     // CLICK ACTION HEREE
     printBtnLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
     printBtnLabel.addMouseListener(new java.awt.event.MouseAdapter() {
       @Override
        public void mousePressed(java.awt.event.MouseEvent e) {
        JOptionPane.showMessageDialog(null, "This button is pressed", "Print Action", JOptionPane.INFORMATION_MESSAGE);
     }
  });

    printPanel.add(printBtnLabel);
    canvas.add(printPanel);



        // ASSEMBLIES
        assembleLayers(mainContainer, canvas);

        this.setContentPane(mainContainer); 
        this.revalidate();
        this.repaint();
        
    } 

    // --- METHODS (Outside the Constructor)
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
        SwingUtilities.invokeLater(() -> {
            User testUser = new User("Name", "User", "ID", "Addr", "Phone", "Mail", "Role", true, 0, 0); 
            ReceiptFrame frame = new ReceiptFrame(testUser);
            frame.setVisible(true);
        });
    }
}


















































































