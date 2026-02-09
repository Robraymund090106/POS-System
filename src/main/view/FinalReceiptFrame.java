package main.view;

import java.awt.*;
import java.awt.print.*;
import java.util.*;
import java.util.List;
import javax.swing.*;
import main.model.User;

public class FinalReceiptFrame extends JFrame {
    private ReceiptFrame parentFrame;
    
    public FinalReceiptFrame(ReceiptFrame parent, User user, int saleId, double total, double cash, double change, String method, List<String> items, List<Double> prices) {
        this.parentFrame = parent;
        setTitle("Official Receipt #" + saleId);
        setSize(450, 700);
        setLocationRelativeTo(null);
        setUndecorated(true); 
        
        JPanel paper = new JPanel();
        paper.setLayout(new BoxLayout(paper, BoxLayout.Y_AXIS));
        paper.setBackground(Color.WHITE);
        paper.setBorder(BorderFactory.createLineBorder(new Color(58, 83, 155), 2));

        // Header Section
        JLabel title = new JLabel("TRANSACTION COMPLETE");
        title.setFont(new Font("SansSerif", Font.BOLD, 18));
        title.setBounds(0, 50, 900, 40);
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

        // --- REFERENCE NUMBER LOGIC ---
        String refRow = "";
        if (method.equalsIgnoreCase("GCash") || method.equalsIgnoreCase("Maya")) {
            // Generates a random 9-digit number
            long randomRef = (long) (Math.random() * 900_000_000L) + 100_000_000L;
            refRow = "<br>Ref Num: " + randomRef;
        }

        // Summary Section
        paper.add(new JLabel("------------------------------------------------------------"));
        String summary = String.format("<html><div style='text-align: right; padding-right: 20px;'>" +
                "<b>Total: ₱%.2f</b><br>Cash: ₱%.2f<br>Change: ₱%.2f<br>Method: %s%s</div></html>", 
                total, cash, change, method, refRow);
        
        JLabel summaryLabel = new JLabel(summary);
        summaryLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
        paper.add(summaryLabel);

        // Buttons
        JPanel btnPanel = new JPanel();
        JButton printBtn = new JButton("PRINT AS PDF");
        printBtn.setBackground(Color.BLACK);
        printBtn.setForeground(Color.WHITE);
        printBtn.addActionListener(e -> {
            PrinterJob job = PrinterJob.getPrinterJob();
            job.setJobName("Receipt_" + saleId);
            job.setPrintable(new Printable() {
                public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
                    if (pageIndex > 0) return Printable.NO_SUCH_PAGE;
                    Graphics2D g2 = (Graphics2D) graphics;
                    g2.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
                    double scaleX = pageFormat.getImageableWidth() / paper.getWidth();
                    double scaleY = pageFormat.getImageableHeight() / paper.getHeight();
                    double scale = Math.min(scaleX, scaleY);
                    g2.scale(scale, scale);
                    paper.printAll(g2);
                    return Printable.PAGE_EXISTS;
                }
            });
            boolean doPrint = job.printDialog();
            if (doPrint) {
                try {
                    job.print();
                } catch (PrinterException ex) {
                    JOptionPane.showMessageDialog(FinalReceiptFrame.this, "Print failed: " + ex.getMessage());
                }
            }
        });

        JButton closeBtn = new JButton("New Transaction");
        closeBtn.addActionListener(e -> {
            if (parentFrame != null) {
                parentFrame.dispose();
            }
            this.dispose(); 
            new DB_Staff(user);
        });
        btnPanel.add(printBtn);
        btnPanel.add(closeBtn);

        add(paper, BorderLayout.CENTER);
        add(btnPanel, BorderLayout.SOUTH);
        setVisible(true);
    }
}