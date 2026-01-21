package main.view;

import java.awt.Color;

import javax.swing.*;

public class DashboardPanel extends JPanel{
    JScrollPane scrollPane;
    JTable productTable;
    public DashboardPanel(){
        setBackground(Color.WHITE);
        setLayout(null);
        scrollPane = new JScrollPane(this);


    }

}
