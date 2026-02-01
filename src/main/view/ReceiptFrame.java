package main.view;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.*;
import main.database.*;
import main.model.User;


public class ReceiptFrame extends JFrame {
    User user;
    JPanel canvas; 
    private double totalprice;
    private double cashgiven;
    private double change;
    private String paymentmethod;
    private List<String> orderItems;
    private List<Double> orderPrices;
    private JPanel detailsPanel;
    private JPanel receiptContent;



    public ReceiptFrame(User user, double totalprice, List<String> orderItems, List<Double> orderPrices) {

        this.user = user;
        this.totalprice = totalprice;
        this.cashgiven = cashgiven;
        this.change = change;
        this.paymentmethod = paymentmethod;
        this.orderItems = orderItems;
        this.orderPrices = orderPrices;
  
   setTitle("Receipt");
   setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


  this.setUndecorated(true); 
  this.setResizable(false);


 this.setExtendedState(JFrame.MAXIMIZED_BOTH);


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


this.setContentPane(mainContainer);
this.setVisible(true);

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
        detailsPanel = new JPanel(null);
        detailsPanel.setBackground(Color.CYAN);
        detailsPanel.setBounds(60, 132, 817, 646); 
        detailsPanel.setOpaque(false);

        JLabel receiptLabel = new JLabel("Receipt");
        receiptLabel.setFont(new Font("SansSerif", Font.PLAIN, 40));
        receiptLabel.setForeground(new Color(58, 83, 155));
        receiptLabel.setBounds(27, 10, 200, 50);
        detailsPanel.add(receiptLabel);

        JLabel receiptNumber = new JLabel("Receipt No.: (Pending)");
        receiptNumber.setFont(new Font("SansSerif", Font.PLAIN, 20));
        receiptNumber.setForeground(new Color(58, 83, 155));
        receiptNumber.setBounds(27, 60, 200, 30);
        detailsPanel.add(receiptNumber);


        JLabel lineSeparator = new JLabel("---------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        lineSeparator.setFont(new Font("SansSerif", Font.PLAIN, 20));
        lineSeparator.setForeground(new Color(58, 83, 155));
        lineSeparator.setBounds(10, 90, 817, 30);
        detailsPanel.add(lineSeparator);

        JLabel lineSeparator2 = new JLabel("---------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        lineSeparator2.setFont(new Font("SansSerif", Font.PLAIN, 20));
        lineSeparator2.setForeground(new Color(58, 83, 155));
        lineSeparator2.setBounds(10, 505, 817, 30);
        detailsPanel.add(lineSeparator2);


        JLabel total = new JLabel("Total Amount:                                                                                         " + String.format("%.2f", this.totalprice));
        total.setFont(new Font("SansSerif", Font.PLAIN, 20));
        total.setForeground(new Color(58, 83, 155));
        total.setBounds(10, 530, 817, 30);
        detailsPanel.add(total);

        JLabel lineSeparator3 = new JLabel("---------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        lineSeparator3.setFont(new Font("SansSerif", Font.PLAIN, 20));
        lineSeparator3.setForeground(new Color(58, 83, 155));
        lineSeparator3.setBounds(10, 550, 817, 30);
        detailsPanel.add(lineSeparator3);

        //receiptContent = new JPanel();
        //receiptContent.setBounds(0, 100, 817, 500);
        //receiptContent.setBackground(Color.LIGHT_GRAY);
        //detailsPanel.add(receiptContent);

        recieptcontent(orderItems, orderPrices); 




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
                ReceiptFrame.this.dispose(); 
                new DB_Staff(user);
        
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

        JLabel TotalPrice = new JLabel(String.format("%.2f", this.totalprice));
        TotalPrice.setFont(new Font("SansSerif", Font.BOLD, 30));
        TotalPrice.setBackground(Color.BLACK);
        TotalPrice.setBounds(300, 29, 343, 50);
        TPanel.add(TotalPrice);
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
        //JOptionPane.showMessageDialog(null, "This button is pressed", "Pay Action", JOptionPane.INFORMATION_MESSAGE);

        if (calcDisplay.getText().isEmpty() || calcDisplay.getText().equals("0")) {
            JOptionPane.showMessageDialog(null, "Please enter the cash given amount.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        double cashGiven;
        JPanel optionPanel = new JPanel(new GridLayout(3, 1, 5, 5));
        JComboBox<String> methodCombo = new JComboBox<>(new String[]{"Cash", "GCash", "Maya"});
        JCheckBox seniorCheck = new JCheckBox("Apply Senior/PWD Discount (20%)");
        
        optionPanel.add(new JLabel("Select Payment Method:"));
        optionPanel.add(methodCombo);
        optionPanel.add(seniorCheck);
        int result = JOptionPane.showConfirmDialog(null, optionPanel, "Payment Details", JOptionPane.OK_CANCEL_OPTION);
        if (result != JOptionPane.OK_OPTION) {
            return; 
        }
            if (seniorCheck.isSelected()) {
                ReceiptFrame.this.totalprice *= 0.8;
                total.setText("Total Amount: " + String.format("   %.2f", totalprice));
                // Update the big Total display on the right
                TotalPrice.setText(String.format("%.2f", totalprice));

                JOptionPane.showMessageDialog(null, "Senior Discount Applied!");
            }
        
        
        String paymentmethod = (String) methodCombo.getSelectedItem();

        try {
            cashGiven = Double.parseDouble(calcDisplay.getText());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Invalid cash amount entered.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (cashGiven < totalprice) {
            JOptionPane.showMessageDialog(null, "Insufficient cash given.", "Payment Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        double change = cashGiven - ReceiptFrame.this.totalprice;
        JOptionPane.showMessageDialog(null, "Change: ₱" + String.format("%.2f", change), "Payment Successful", JOptionPane.INFORMATION_MESSAGE);
        DatabaseManager.recordSale(user.getUserId(), totalprice, cashGiven, change, orderItems, orderPrices, paymentmethod);
        for (String itemName : orderItems) {
                        // Decrement stock by 1 for every instance in the list
                        DatabaseManager.updateProductStock(itemName, 1);
                    }
        ReceiptFrame.this.dispose(); 
        new DB_Staff(user);

        // --- CLEAR EVERYTHING AFTER SUCCESS ---
        calcDisplay.setText("0");
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
        JOptionPane.showMessageDialog(null, "PDF has been downloaded", "Print Action", JOptionPane.INFORMATION_MESSAGE);
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

    public void recieptcontent(List<String> items, List<Double> prices) {
    // 1. Setup the main container panel
    JPanel receiptContainer = new JPanel();
    receiptContainer.setLayout(new BoxLayout(receiptContainer, BoxLayout.Y_AXIS));
    receiptContainer.setBackground(Color.WHITE);
    receiptContainer.setOpaque(false);
    // Set position within detailsPanel (Panel 2)
    receiptContainer.setBounds(00, 120, 740, 400); 


    Map<String, Integer> counts = new HashMap<>();
    Map<String, Double> unitprice = new HashMap<>();
    for(int i = 0; i < items.size(); i++){
        counts.put(items.get(i), counts.getOrDefault(items.get(i), 0) + 1);
        unitprice.put(items.get(i), prices.get(i));
    }


    JPanel headerRow = new JPanel(new GridLayout(1, 4));
    headerRow.setOpaque(false);
    headerRow.setMaximumSize(new Dimension(740, 30));
    
    String[] cols = {"Qty", "Product", "Price", "Total Price"};
    for (String col : cols) {
        JLabel lbl = new JLabel(col, JLabel.CENTER);
        lbl.setFont(new Font("SansSerif", Font.BOLD, 16));
        lbl.setForeground(new Color(58, 83, 155)); // Matching your UI blue
        headerRow.add(lbl);
    }
    receiptContainer.add(headerRow);
    receiptContainer.add(Box.createVerticalStrut(10)); // Spacing


    for (String item : counts.keySet()) {
        int qty = counts.get(item);
        double price = unitprice.get(item);
        double lineTotal = qty * price;

        JPanel row = new JPanel(new GridLayout(1, 4));
        row.setOpaque(false);
        row.setMaximumSize(new Dimension(740, 40));

        row.add(new JLabel(String.valueOf(qty), JLabel.CENTER));
        row.add(new JLabel(item, JLabel.CENTER));
        row.add(new JLabel(String.format("%.0f", price), JLabel.CENTER));
        row.add(new JLabel(String.format("%.0f", lineTotal), JLabel.CENTER));

 
        for (Component c : row.getComponents()) {
            c.setFont(new Font("SansSerif", Font.PLAIN, 15));
        }
        
        receiptContainer.add(row);
    }


    detailsPanel.add(receiptContainer); 
}




    
}

