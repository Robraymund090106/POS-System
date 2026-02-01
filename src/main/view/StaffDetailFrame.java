package main.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.*;
public class StaffDetailFrame extends JFrame {


    

    public StaffDetailFrame(String staffName) {
        setTitle("Staff Profile");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setUndecorated(true); 
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        
        JPanel backgroundPanel = new JPanel();
        backgroundPanel.setLayout(null);
        backgroundPanel.setBackground(Color.WHITE);

        add(backgroundPanel);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;

        setVisible(true);

     
    }
}