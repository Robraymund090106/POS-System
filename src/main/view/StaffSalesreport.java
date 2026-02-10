package main.view;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import main.database.DatabaseManager;

public class StaffSalesreport extends JFrame {
    public StaffSalesreport() {
        setTitle("Staff Sales Report Info");
        setSize(600, 800);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBackground(Color.WHITE);

        // --- 1. Top Header Label ---
        JLabel titleLabel = new JLabel("STAFF SALES DAILY REPORT ", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Monospaced", Font.BOLD, 22));
        titleLabel.setBounds(0, 20, 600, 40);
        Border dashedBorder = BorderFactory.createMatteBorder(1, 0, 1, 0, Color.BLACK);
        titleLabel.setBorder(dashedBorder);
        mainPanel.add(titleLabel);

        // --- 2. Table and Model Setup ---
      

        


        String[] columns = {"STAFF", "STATUS", "SALES REPORT"};
        DefaultTableModel model = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; 
            }
        };

        JTable table = new JTable(model);
        styleTable(table);

        // --- FIXED DATA LOADING LOGIC ---
        List<Object[]> reportData = DatabaseManager.getStaffReportInfo();

        for (Object[] row : reportData) {
            String staffName = row[0].toString();
            int userId = DatabaseManager.getuseridonusername(staffName);
            
            // Fetch transactions for today
            List<String[]> staffTransactions = DatabaseManager.getDetailedTransactionHistoryByPeriod(userId, "Daily");

            // If they have 0 sales, force status to PENDING regardless of submission
            if (staffTransactions == null || staffTransactions.isEmpty()) {
                row[2] = "PENDING"; 
            }

            model.addRow(row);
        }

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 80, 545, 600); 
        scrollPane.setBorder(dashedBorder); 
        scrollPane.getViewport().setBackground(Color.WHITE);
        mainPanel.add(scrollPane);

 
        JButton viewReportBtn = new JButton("VIEW REPORT");
        viewReportBtn.setBounds(200, 700, 200, 40);
        viewReportBtn.setFont(new Font("Arial", Font.BOLD, 14));
        viewReportBtn.setBackground(Color.BLACK);
        viewReportBtn.setForeground(Color.WHITE);
        mainPanel.add(viewReportBtn);

  

        viewReportBtn.addActionListener(e -> {
        
           int selectedRow = table.getSelectedRow();

    if (selectedRow != -1) {
        String selectedStaff = table.getValueAt(selectedRow, 0).toString();
        int selectedUserId = DatabaseManager.getuseridonusername(selectedStaff);
        String reportStatus = table.getValueAt(selectedRow, 2).toString();

        if (reportStatus.contains("PENDING")) {
            JOptionPane.showMessageDialog(this, selectedStaff + " hasn't submitted a report yet.");
            return;
        }

        mainPanel.removeAll(); 

        // Setup the Detail Panel
        JPanel salereport = new JPanel(new BorderLayout());
        salereport.setBounds(20, 80, 545, 600);
        salereport.setBackground(Color.WHITE);
        salereport.setBorder(dashedBorder);

        JLabel nameLabel = new JLabel(selectedStaff.toUpperCase() + "'S SALES REPORT", SwingConstants.CENTER);
        nameLabel.setFont(new Font("Monospaced", Font.BOLD, 18));
        nameLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        salereport.add(nameLabel, BorderLayout.NORTH);

        // Table Setup
        String[] salecolumns = {"Product Name", "Qty", "Timestamp"};
        DefaultTableModel salemodel = new DefaultTableModel(salecolumns, 0);
        JTable saletable = new JTable(salemodel);
        styleTable(saletable);

        List<String[]> staffdata = DatabaseManager.getDetailedTransactionHistoryByPeriod(selectedUserId, "Daily");
        for (String[] row : staffdata) {
            salemodel.addRow(row);
        }

        JScrollPane salescroll = new JScrollPane(saletable);
        salescroll.getViewport().setBackground(Color.WHITE);
        salereport.add(salescroll, BorderLayout.CENTER);

        

        // --- NEW: STAFF NOTES SECTION ---
        JPanel notesPanel = new JPanel(new BorderLayout());
        notesPanel.setBackground(new Color(245, 245, 245)); // Light gray background for contrast
        notesPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "STAFF NOTES"));

        String notesText = DatabaseManager.getStaffNotes(selectedUserId);
        JTextArea notesArea = new JTextArea(notesText);
        notesArea.setEditable(false);
        notesArea.setLineWrap(true);
        notesArea.setWrapStyleWord(true);
        notesArea.setFont(new Font("Monospaced", Font.ITALIC, 13));
        notesArea.setBackground(new Color(245, 245, 245));

        notesPanel.add(new JScrollPane(notesArea), BorderLayout.CENTER);
        notesPanel.setPreferredSize(new Dimension(545, 120)); // Set height for notes area

        salereport.add(notesPanel, BorderLayout.SOUTH); // Add notes to bottom of report

        
        // --------------------------------

        JButton backBtn = new JButton("BACK TO LIST");
        backBtn.setBounds(200, 700, 200, 40);
        backBtn.setBackground(Color.BLACK);
        backBtn.setForeground(Color.WHITE);
        backBtn.addActionListener(backEvt -> {
            this.dispose();
            new StaffSalesreport(); 
        });

        mainPanel.add(titleLabel);
        mainPanel.add(salereport);
        mainPanel.add(backBtn);

        mainPanel.revalidate();
        mainPanel.repaint();
    } else {
        JOptionPane.showMessageDialog(this, "Please select a staff member from the list.");
    }
        });

        add(mainPanel);
        setVisible(true);
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

    public static void main(String[] args) {
        new StaffSalesreport();
    }
}