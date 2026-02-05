package main.view;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;
import main.model.User;

public class FinalReceiptFrame extends JFrame {
    private ReceiptFrame parentFrame;
    
    public FinalReceiptFrame(ReceiptFrame parent, User user, int saleId, double total, double cash, double change, String method, List<String> items, List<Double> prices) {
        this.parentFrame = parent;
        setTitle("Official Receipt #" + saleId);
        setSize(450, 700);
        setLocationRelativeTo(null);
        setUndecorated(true); // Matches your sleek UI style
        
        JPanel paper = new JPanel();
        paper.setLayout(new BoxLayout(paper, BoxLayout.Y_AXIS));
        paper.setBackground(Color.WHITE);
        paper.setBorder(BorderFactory.createLineBorder(new Color(58, 83, 155), 2));

        // Header Section
        JLabel title = new JLabel("TRANSACTION COMPLETE");
        title.setFont(new Font("SansSerif", Font.BOLD, 22));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel idLabel = new JLabel("Receipt No: " + String.format("%05d", saleId));
        idLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
        idLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        paper.add(Box.createVerticalStrut(20));
        paper.add(title);
        paper.add(idLabel);
        paper.add(new JLabel("Cashier: " + user.getUsername()));
        paper.add(Box.createVerticalStrut(10));
        paper.add(new JLabel("------------------------------------------------------------"));

        // Content Section (Grouping items)
        Map<String, Integer> counts = new HashMap<>();
        Map<String, Double> unitprice = new HashMap<>();
        for(int i = 0; i < items.size(); i++){
            counts.put(items.get(i), counts.getOrDefault(items.get(i), 0) + 1);
            unitprice.put(items.get(i), prices.get(i));
        }

        for (String item : counts.keySet()) {
            int qty = counts.get(item);
            double price = unitprice.get(item);
            JLabel itemLabel = new JLabel(String.format("%d x %-15s ..... %.2f", qty, item, (qty * price)));
            itemLabel.setFont(new Font("Monospaced", Font.PLAIN, 14));
            itemLabel.setBorder(BorderFactory.createEmptyBorder(5, 20, 5, 20));
            paper.add(itemLabel);
        }

        // Summary Section
        paper.add(new JLabel("------------------------------------------------------------"));
        String summary = String.format("<html><div style='text-align: right; padding-right: 20px;'>" +
                "<b>Total: ₱%.2f</b><br>Cash: ₱%.2f<br>Change: ₱%.2f<br>Method: %s</div></html>", 
                total, cash, change, method);
        JLabel summaryLabel = new JLabel(summary);
        summaryLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
        paper.add(summaryLabel);

        // Buttons
        JPanel btnPanel = new JPanel();
        JButton closeBtn = new JButton("New Transaction");
        closeBtn.addActionListener(e -> {
            if (parentFrame != null) {
                parentFrame.dispose();
            }
            
            // 2. Dispose this receipt frame
            this.dispose(); 
            
            // 3. Return to the dashboard
            new DB_Staff(user);
        });
        btnPanel.add(closeBtn);

        add(paper, BorderLayout.CENTER);
        add(btnPanel, BorderLayout.SOUTH);
        setVisible(true);
    }
}