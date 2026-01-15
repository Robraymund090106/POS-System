
package main.view;

import java.awt.*;
import javax.swing.*;
import main.model.User;

public class MainDashboard extends JFrame {
    private User user;

    public MainDashboard(User user) {
        this.user = user;

        setTitle("Dashboard");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Panel for the bg 
        JPanel mainContainer = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

               
                ImageIcon imageIcon = new ImageIcon("c:\\Users\\karin\\Downloads\\Email (2).png");
                Image img = imageIcon.getImage();
                
               
                g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
            }
        };

      

       
        this.setContentPane(mainContainer);

       

        setLocationRelativeTo(null); 
        setVisible(true);
    }
}