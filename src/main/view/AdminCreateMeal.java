package main.view;

import java.awt.*;
import javax.swing.*;
import main.backend.UsernameLimit;

public class AdminCreateMeal extends JPanel {
    private Image bgImage;
    private JComboBox<String> CategCombo;

    public AdminCreateMeal() {

        bgImage = new ImageIcon("src/main/image/AdminCreateMealBTN.png").getImage();
        this.setLayout(null); 

        JLabel ItemLabel = new JLabel("Item Name");
        ItemLabel.setSize(150, 30);
        ItemLabel.setFont(new Font("Arial ", Font.BOLD, 20));
        ItemLabel.setLocation(300, 180);
        ItemLabel.setForeground(Color.BLACK);
        this.add(ItemLabel);

        JTextField Itemname = new JTextField();
        Itemname.setBounds(200, 275, 240, 48); 
        Itemname.setFont(new Font("Arial", Font.PLAIN, 18));
        Itemname.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        Itemname.setOpaque(false); 
        
        Itemname.setDocument(new UsernameLimit(30, Itemname));
        
        this.add(Itemname);

         JLabel ItemstockLabel = new JLabel("Item Stock");
        ItemstockLabel.setSize(150, 30);
        ItemstockLabel.setFont(new Font("Arial ", Font.BOLD, 20));
        ItemstockLabel.setLocation(300, 330);
        ItemstockLabel.setForeground(Color.BLACK);
        this.add(ItemstockLabel);

        JLabel ItempriceLabel = new JLabel("Item Price");
        ItempriceLabel.setSize(150, 30);
        ItempriceLabel.setFont(new Font("Arial ", Font.BOLD, 20));
        ItempriceLabel.setLocation(850, 180);
        ItempriceLabel.setForeground(Color.BLACK); 

        JLabel ItemCatLabel = new JLabel("Item Category");
        ItemCatLabel.setSize(150, 30);
        ItemCatLabel.setFont(new Font("Arial ", Font.BOLD, 20));
        ItemCatLabel.setLocation(850, 330);
        ItemCatLabel.setForeground(Color.BLACK); 
        this.add(ItemCatLabel);
        String[] ItemCateg = {
            "                   ",
        };
        CategCombo = new JComboBox<>(ItemCateg);
        CategCombo.setSize(170, 40);
        CategCombo.setFont(new Font("Arial", Font.PLAIN, 17));
        CategCombo.setLocation(850, 380);
        CategCombo.setForeground(Color.BLACK);
        this.add(CategCombo);
        this.add(ItempriceLabel);



    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(bgImage, 0, 0, getWidth(), getHeight(), this);
    }
    public static void main(String[] args) {
        JFrame frame = new JFrame("Admin Create Meal Preview");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        frame.setUndecorated(false); 
        frame.add(new AdminCreateMeal());
        
        frame.setVisible(true);
    }
}