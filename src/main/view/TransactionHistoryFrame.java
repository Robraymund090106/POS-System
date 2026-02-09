package main.view;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import main.database.DatabaseManager;
import java.awt.*;
import java.text.MessageFormat;
import java.util.List;

public class TransactionHistoryFrame extends JFrame {
    
    public TransactionHistoryFrame(String period) {
        setTitle(period + " Transaction History");
        setSize(900, 800); // Slightly wider to fit more columns
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBackground(Color.WHITE);
        add(mainPanel);



    if(period.equals("Daily")){
            // 1. Updated title width to match the 800px frame
            JLabel titleLabel = new JLabel("DAILY TRANSACTION HISTORY", SwingConstants.CENTER);
            titleLabel.setFont(new Font("Monospaced", Font.BOLD, 22));
            titleLabel.setBounds(0, 20, 900, 40); // Increased width to 800
            Border dashedBorder = BorderFactory.createMatteBorder(1, 0, 1, 0, Color.BLACK);
            titleLabel.setBorder(dashedBorder);
            mainPanel.add(titleLabel);

            String[] columns = {"Staff", "Product", "Qty", "Total", "Mode", "Date"};
            DefaultTableModel model = new DefaultTableModel(columns, 0) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false; 
                }
            };

            JTable table = new JTable(model);
            styleTable(table);

            // --- Load the Data ---
            // Make sure this returns List<String[]> as we defined earlier
            List<String[]> reportData = DatabaseManager.getTransactionHistoryByPeriod("Daily");

            if (reportData.isEmpty()) {
                System.out.println("No data found for the daily period.");
            } else {
                for (String[] row : reportData) {
                    model.addRow(row);
                }
            }

            // 2. Updated ScrollPane Bounds to fit the 800px frame better
            JScrollPane scrollPane = new JScrollPane(table);
            scrollPane.setBounds(40, 80, 800, 550); // Widened and adjusted height
            scrollPane.setBorder(dashedBorder);
            scrollPane.getViewport().setBackground(Color.WHITE);
            mainPanel.add(scrollPane);

       

            // 4. Center the Save Button
            JButton printDailysales = new JButton("Save as PDF");
            printDailysales.setBounds(300, 700, 200, 40); // Adjusted X to center on 800px
            printDailysales.setFont(new Font("Arial", Font.BOLD, 14));
            printDailysales.setBackground(Color.BLACK);
            printDailysales.setForeground(Color.WHITE);
            mainPanel.add(printDailysales);

             printDailysales.addActionListener(e -> {

                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Save Daily Transactions as PDF");
                fileChooser.setSelectedFile(new java.io.File("Daily_Transactions.pdf"));

                int userSelection = fileChooser.showSaveDialog(null);

                if (userSelection == JFileChooser.APPROVE_OPTION) {
                    //JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Save Daily Transactions as PDF");
                
                // Set a default file name
                fileChooser.setSelectedFile(new java.io.File("Daily Transactions.pdf"));

                //int userSelection = fileChooser.showSaveDialog(null);

                if (userSelection == JFileChooser.APPROVE_OPTION) {
                    java.io.File fileToSave = fileChooser.getSelectedFile();
                    
                    try {
                        
                        MessageFormat headerr = new MessageFormat("NUCMS - Daily Transactions Report");
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

            JLabel titleLabel = new JLabel("WEEKLY TRANSACTION HISTORY", SwingConstants.CENTER);
            titleLabel.setFont(new Font("Monospaced", Font.BOLD, 22));
            titleLabel.setBounds(0, 20, 900, 40); // Increased width to 800
            Border dashedBorder = BorderFactory.createMatteBorder(1, 0, 1, 0, Color.BLACK);
            titleLabel.setBorder(dashedBorder);
            mainPanel.add(titleLabel);

            String[] columns = {"Staff", "Product", "Qty", "Total", "Mode", "Date"};
            DefaultTableModel model = new DefaultTableModel(columns, 0) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false; 
                }
            };

            JTable table = new JTable(model);
            styleTable(table);

            // --- Load the Data ---
            // Make sure this returns List<String[]> as we defined earlier
            List<String[]> reportData = DatabaseManager.getTransactionHistoryByPeriod("Weekly");

            if (reportData.isEmpty()) {
                System.out.println("No data found for the daily period.");
            } else {
                for (String[] row : reportData) {
                    model.addRow(row);
                }
            }

            // 2. Updated ScrollPane Bounds to fit the 800px frame better
            JScrollPane scrollPane = new JScrollPane(table);
            scrollPane.setBounds(40, 80, 800, 550); // Widened and adjusted height
            scrollPane.setBorder(dashedBorder);
            scrollPane.getViewport().setBackground(Color.WHITE);
            mainPanel.add(scrollPane);

       

            // 4. Center the Save Button
            JButton printDailysales = new JButton("Save as PDF");
            printDailysales.setBounds(300, 700, 200, 40); // Adjusted X to center on 800px
            printDailysales.setFont(new Font("Arial", Font.BOLD, 14));
            printDailysales.setBackground(Color.BLACK);
            printDailysales.setForeground(Color.WHITE);
            mainPanel.add(printDailysales);

             printDailysales.addActionListener(e -> {

                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Save WEEKLY Transactions as PDF");
                fileChooser.setSelectedFile(new java.io.File("Weekly_Transactions.pdf"));

                int userSelection = fileChooser.showSaveDialog(null);

                if (userSelection == JFileChooser.APPROVE_OPTION) {
                    //JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Save Weekly Transactions as PDF");
                
                // Set a default file name
                fileChooser.setSelectedFile(new java.io.File("Weekly Transactions.pdf"));

                //int userSelection = fileChooser.showSaveDialog(null);

                if (userSelection == JFileChooser.APPROVE_OPTION) {
                    java.io.File fileToSave = fileChooser.getSelectedFile();
                    
                    try {
                        
                        MessageFormat headerr = new MessageFormat("NUCMS - Weekly Transactions Report");
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
            JLabel titleLabel = new JLabel("MONTHLY TRANSACTION HISTORY", SwingConstants.CENTER);
            titleLabel.setFont(new Font("Monospaced", Font.BOLD, 22));
            titleLabel.setBounds(0, 20, 900, 40); // Increased width to 800
            Border dashedBorder = BorderFactory.createMatteBorder(1, 0, 1, 0, Color.BLACK);
            titleLabel.setBorder(dashedBorder);
            mainPanel.add(titleLabel);

            String[] columns = {"Staff", "Product", "Qty", "Total", "Mode", "Date"};
            DefaultTableModel model = new DefaultTableModel(columns, 0) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false; 
                }
            };

            JTable table = new JTable(model);
            styleTable(table);

            // --- Load the Data ---
            // Make sure this returns List<String[]> as we defined earlier
            List<String[]> reportData = DatabaseManager.getTransactionHistoryByPeriod("Monthly");

            if (reportData.isEmpty()) {
                System.out.println("No data found for the daily period.");
            } else {
                for (String[] row : reportData) {
                    model.addRow(row);
                }
            }

            // 2. Updated ScrollPane Bounds to fit the 800px frame better
            JScrollPane scrollPane = new JScrollPane(table);
            scrollPane.setBounds(40, 80, 800, 550); // Widened and adjusted height
            scrollPane.setBorder(dashedBorder);
            scrollPane.getViewport().setBackground(Color.WHITE);
            mainPanel.add(scrollPane);

       

            // 4. Center the Save Button
            JButton printDailysales = new JButton("Save as PDF");
            printDailysales.setBounds(300, 700, 200, 40); // Adjusted X to center on 800px
            printDailysales.setFont(new Font("Arial", Font.BOLD, 14));
            printDailysales.setBackground(Color.BLACK);
            printDailysales.setForeground(Color.WHITE);
            mainPanel.add(printDailysales);

             printDailysales.addActionListener(e -> {

                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Save Monthly Transactions as PDF");
                fileChooser.setSelectedFile(new java.io.File("Monthly_Transactions.pdf"));

                int userSelection = fileChooser.showSaveDialog(null);

                if (userSelection == JFileChooser.APPROVE_OPTION) {
                    //JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Save Monthly Transactions as PDF");
                
                // Set a default file name
                fileChooser.setSelectedFile(new java.io.File("Monthly Transactions.pdf"));

                //int userSelection = fileChooser.showSaveDialog(null);

                if (userSelection == JFileChooser.APPROVE_OPTION) {
                    java.io.File fileToSave = fileChooser.getSelectedFile();
                    
                    try {
                        
                        MessageFormat headerr = new MessageFormat("NUCMS - MonthlyTransactions Report");
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
    }

    private void styleTable(JTable table) {
        table.setRowHeight(45);
        table.setFont(new Font("Monospaced", Font.PLAIN, 10));
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

    public static void main(String[] args) {
        new TransactionHistoryFrame("Monthly");
    }

}