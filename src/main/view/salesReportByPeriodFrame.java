package main.view;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import main.database.DatabaseManager;

import java.awt.*;
import java.text.MessageFormat;
import java.util.List;

public class salesReportByPeriodFrame extends JFrame {
    
    public salesReportByPeriodFrame(String period) {

        setTitle(period.substring(0, 1).toUpperCase() + period.substring(1) + " Sales Report");
        setSize(600, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);


        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setBounds(0, 0, 600, 800);
        add(mainPanel);

        if(period.equals("Today")){
            JLabel titleLabel = new JLabel("DAILY SALES REPORT ", SwingConstants.CENTER);
            titleLabel.setFont(new Font("Monospaced", Font.BOLD, 22));
            titleLabel.setBounds(0, 20, 600, 40);
            Border dashedBorder = BorderFactory.createMatteBorder(1, 0, 1, 0, Color.BLACK);
            titleLabel.setBorder(dashedBorder);
            mainPanel.add(titleLabel);

            String[] columns = {"Staff", "Daily Sales"};
            DefaultTableModel model = new DefaultTableModel(columns, 0) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false; 
                }
            };

            JTable table = new JTable(model);
            styleTable(table);

            // --- 1. Load the Data ---
            // We pass "Daily" to match the logic in your DatabaseManager
            List<Object[]> reportData = DatabaseManager.getSalesreportByPeriod("Daily");
            for (Object[] row : reportData) {
                model.addRow(row);
            }

            // --- 2. Add to ScrollPane and set Bounds ---
            // Without setBounds, the table remains 0x0 pixels in a null layout
            JScrollPane scrollPane = new JScrollPane(table);
            scrollPane.setBounds(20, 80, 545, 600); 
            scrollPane.setBorder(dashedBorder);
            scrollPane.getViewport().setBackground(Color.WHITE);

            double totalAmount = DatabaseManager.getTotalSalesbyPeriod("day");
            JPanel totalPanel = new JPanel(new BorderLayout());
            totalPanel.setBounds(20, 640, 545, 40); // Placed right below the table
            totalPanel.setBackground(Color.WHITE);
            totalPanel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.BLACK)); // Top line only

            JLabel totalLabel = new JLabel("DAILY SALES TOTAL: ");
            totalLabel.setFont(new Font("Monospaced", Font.BOLD, 18));

            JLabel totalValue = new JLabel("₱" + String.format("%.2f", totalAmount));
            totalValue.setFont(new Font("Monospaced", Font.BOLD, 18));
            totalValue.setForeground(new Color(0, 102, 0)); // Dark Green for money

            totalPanel.add(totalLabel, BorderLayout.WEST);
            totalPanel.add(totalValue, BorderLayout.EAST);

            mainPanel.add(totalPanel);
            mainPanel.add(scrollPane);

            JButton printDailysales = new JButton("Save as PDF");
            printDailysales.setBounds(200, 700, 200, 40);
            printDailysales.setFont(new Font("Arial", Font.BOLD, 14));
            printDailysales.setBackground(Color.BLACK);
            printDailysales.setForeground(Color.WHITE);
            mainPanel.add(printDailysales);

            printDailysales.addActionListener(e -> {

                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Save Daily Sales as PDF");
                fileChooser.setSelectedFile(new java.io.File("Daily_Sales_Report.pdf"));

                int userSelection = fileChooser.showSaveDialog(null);

                if (userSelection == JFileChooser.APPROVE_OPTION) {
                    //JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Save Daily Sales Report as PDF");
                
                // Set a default file name
                fileChooser.setSelectedFile(new java.io.File("Daily Sales Report.pdf"));

                //int userSelection = fileChooser.showSaveDialog(null);

                if (userSelection == JFileChooser.APPROVE_OPTION) {
                    java.io.File fileToSave = fileChooser.getSelectedFile();
                    
                    try {
                        
                        MessageFormat headerr = new MessageFormat("NUCMS - Daily Sales Report");
                        MessageFormat footer = new MessageFormat("Page {0,number,integer}");
                        
                        boolean complete = table.print(JTable.PrintMode.FIT_WIDTH, headerr, footer);
                        
                        if (complete) {
                            JOptionPane.showMessageDialog(null, "Report generated successfully!");
                        }
                        
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Error generating report: " + ex.getMessage());
                    }
                }
                }
            });


        } else if(period.equals("Weekly")){

            JLabel titleLabel = new JLabel("Weekly SALES REPORT ", SwingConstants.CENTER);
            titleLabel.setFont(new Font("Monospaced", Font.BOLD, 22));
            titleLabel.setBounds(0, 20, 600, 40);
            Border dashedBorder = BorderFactory.createMatteBorder(1, 0, 1, 0, Color.BLACK);
            titleLabel.setBorder(dashedBorder);
            mainPanel.add(titleLabel);

            String[] columns = {"Staff", "Weekly Sales"};
            DefaultTableModel model = new DefaultTableModel(columns, 0) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false; 
                }
            };

            JTable table = new JTable(model);
            styleTable(table);

            // --- 1. Load the Data ---
            // We pass "Daily" to match the logic in your DatabaseManager
            List<Object[]> reportData = DatabaseManager.getSalesreportByPeriod("Weekly");
            for (Object[] row : reportData) {
                model.addRow(row);
            }

            // --- 2. Add to ScrollPane and set Bounds ---
            // Without setBounds, the table remains 0x0 pixels in a null layout
            JScrollPane scrollPane = new JScrollPane(table);
            scrollPane.setBounds(20, 80, 545, 600); 
            scrollPane.setBorder(dashedBorder);
            scrollPane.getViewport().setBackground(Color.WHITE);

             double totalAmount = DatabaseManager.getTotalSalesbyPeriod("week");
            JPanel totalPanel = new JPanel(new BorderLayout());
            totalPanel.setBounds(20, 640, 545, 40); // Placed right below the table
            totalPanel.setBackground(Color.WHITE);
            totalPanel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.BLACK)); // Top line only

            JLabel totalLabel = new JLabel("WEEKLYSALES TOTAL: ");
            totalLabel.setFont(new Font("Monospaced", Font.BOLD, 18));

            JLabel totalValue = new JLabel("₱" + String.format("%.2f", totalAmount));
            totalValue.setFont(new Font("Monospaced", Font.BOLD, 18));
            totalValue.setForeground(new Color(0, 102, 0)); // Dark Green for money

            totalPanel.add(totalLabel, BorderLayout.WEST);
            totalPanel.add(totalValue, BorderLayout.EAST);

            mainPanel.add(totalPanel);

            
            mainPanel.add(scrollPane);

            JButton printDailysales = new JButton("Save as PDF");
            printDailysales.setBounds(200, 700, 200, 40);
            printDailysales.setFont(new Font("Arial", Font.BOLD, 14));
            printDailysales.setBackground(Color.BLACK);
            printDailysales.setForeground(Color.WHITE);
            mainPanel.add(printDailysales);

            printDailysales.addActionListener(e -> {

                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Save Weekly Sales as PDF");
                fileChooser.setSelectedFile(new java.io.File("Weekly_Sales_Report.pdf"));

                int userSelection = fileChooser.showSaveDialog(null);

                if (userSelection == JFileChooser.APPROVE_OPTION) {
                    //JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Save Weekly Sales Report as PDF");
                
                // Set a default file name
                fileChooser.setSelectedFile(new java.io.File("Weekly Sales Report.pdf"));

                //int userSelection = fileChooser.showSaveDialog(null);

                if (userSelection == JFileChooser.APPROVE_OPTION) {
                    java.io.File fileToSave = fileChooser.getSelectedFile();
                    
                    try {
                        
                        MessageFormat headerr = new MessageFormat("NUCMS - Weekly Sales Report");
                        MessageFormat footer = new MessageFormat("Page {0,number,integer}");
                        
                        boolean complete = table.print(JTable.PrintMode.FIT_WIDTH, headerr, footer);
                        
                        if (complete) {
                            JOptionPane.showMessageDialog(null, "Report generated successfully!");
                        }
                        
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Error generating report: " + ex.getMessage());
                    }
                }
                }
            });
            
        } else if (period.equals("Monthly")){

            JLabel titleLabel = new JLabel("Monthly SALES REPORT ", SwingConstants.CENTER);
            titleLabel.setFont(new Font("Monospaced", Font.BOLD, 22));
            titleLabel.setBounds(0, 20, 600, 40);
            Border dashedBorder = BorderFactory.createMatteBorder(1, 0, 1, 0, Color.BLACK);
            titleLabel.setBorder(dashedBorder);
            mainPanel.add(titleLabel);

            String[] columns = {"Staff", "Monthly Sales"};
            DefaultTableModel model = new DefaultTableModel(columns, 0) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false; 
                }
            };

            JTable table = new JTable(model);
            styleTable(table);

            // --- 1. Load the Data ---
            // We pass "Daily" to match the logic in your DatabaseManager
            List<Object[]> reportData = DatabaseManager.getSalesreportByPeriod("Monthly");
            for (Object[] row : reportData) {
                model.addRow(row);
            }

            // --- 2. Add to ScrollPane and set Bounds ---
            // Without setBounds, the table remains 0x0 pixels in a null layout
            JScrollPane scrollPane = new JScrollPane(table);
            scrollPane.setBounds(20, 80, 545, 600); 
            scrollPane.setBorder(dashedBorder);
            scrollPane.getViewport().setBackground(Color.WHITE);

             double totalAmount = DatabaseManager.getTotalSalesbyPeriod("month");

            JPanel totalPanel = new JPanel(new BorderLayout());
            totalPanel.setBounds(20, 640, 545, 40); // Placed right below the table
            totalPanel.setBackground(Color.WHITE);
            totalPanel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.BLACK)); // Top line only

            JLabel totalLabel = new JLabel("MONTHLY SALES TOTAL: ");
            totalLabel.setFont(new Font("Monospaced", Font.BOLD, 18));

            JLabel totalValue = new JLabel("₱" + String.format("%.2f", totalAmount));
            totalValue.setFont(new Font("Monospaced", Font.BOLD, 18));
            totalValue.setForeground(new Color(0, 102, 0)); // Dark Green for money

            totalPanel.add(totalLabel, BorderLayout.WEST);
            totalPanel.add(totalValue, BorderLayout.EAST);

            mainPanel.add(totalPanel);

            
            mainPanel.add(scrollPane);

            JButton printDailysales = new JButton("Save as PDF");
            printDailysales.setBounds(200, 700, 200, 40);
            printDailysales.setFont(new Font("Arial", Font.BOLD, 14));
            printDailysales.setBackground(Color.BLACK);
            printDailysales.setForeground(Color.WHITE);
            mainPanel.add(printDailysales);

            printDailysales.addActionListener(e -> {

                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Save Monthly Sales as PDF");
                fileChooser.setSelectedFile(new java.io.File("Monthly_Sales_Report.pdf"));

                int userSelection = fileChooser.showSaveDialog(null);

                if (userSelection == JFileChooser.APPROVE_OPTION) {
                    //JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Save Monthly Sales Report as PDF");
                
                // Set a default file name
                fileChooser.setSelectedFile(new java.io.File("Monthly Sales Report.pdf"));

                //int userSelection = fileChooser.showSaveDialog(null);

                if (userSelection == JFileChooser.APPROVE_OPTION) {
                    java.io.File fileToSave = fileChooser.getSelectedFile();
                    
                    try {
                        
                        MessageFormat headerr = new MessageFormat("NUCMS - Monthly Sales Report");
                        MessageFormat footer = new MessageFormat("Page {0,number,integer}");
                        
                        boolean complete = table.print(JTable.PrintMode.FIT_WIDTH, headerr, footer);
                        
                        if (complete) {
                            JOptionPane.showMessageDialog(null, "Report generated successfully!");
                        }
                        
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Error generating report: " + ex.getMessage());
                    }
                }
                }
            });
        }


        setVisible(true);


        


        // Implementation for displaying sales data for the specific period
    }

    public static void main(String[] args) {
        new salesReportByPeriodFrame("Weekly");
    }

    private void styleTable(JTable table) {
        table.setRowHeight(45);
        table.setFont(new Font("Monospaced", Font.PLAIN, 14));
        table.getTableHeader().setFont(new Font("Monospaced", Font.BOLD, 16));
        table.getTableHeader().setBackground(Color.WHITE);
        table.getTableHeader().setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
        table.setShowGrid(false);
        table.setIntercellSpacing(new Dimension(0, 0));

        // Centering text
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }
    

}
