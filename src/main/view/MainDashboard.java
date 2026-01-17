package main.view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import main.model.User;

public class MainDashboard extends JFrame {
    private User user;

    public MainDashboard(User user) {
        this.user = user;
        setTitle("Dashboard");
        setExtendedState(JFrame.MAXIMIZED_BOTH); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainContainer = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon imageIcon = new ImageIcon("src\\main\\image\\Email (6).png");
                Image img = imageIcon.getImage();
                if (img != null) {
                    g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };
        mainContainer.setLayout(null);

     
        JLabel companyNameLabel = new JLabel("Company name");
        companyNameLabel.setFont(new Font("SansSerif", Font.BOLD, 50));
        companyNameLabel.setForeground(Color.WHITE);
        companyNameLabel.setBounds(80, 25, 400, 70); 
        mainContainer.add(companyNameLabel);

      // image button cilckable 

      // 1st image button
        ImageIcon staffIcon = new ImageIcon("src\\main\\image\\Email (1) (1).png"); 
        JLabel clickableImage = new JLabel(staffIcon);
        
      
        int imgW = staffIcon.getIconWidth();
        int imgH = staffIcon.getIconHeight();
        

        clickableImage.setBounds(0, 150, imgW, imgH); 
        
        clickableImage.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                // ACTION HERE when button clicked 

            }
            @Override
            public void mouseEntered(MouseEvent e) {
                clickableImage.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
        });
        
        mainContainer.add(clickableImage);
        mainContainer.setComponentZOrder(clickableImage, 0);

    
    // 2nd imagee button 
    ImageIcon secondIcon = new ImageIcon("src\\main\\image\\Email (2) (1).png"); 
    JLabel secondClickable = new JLabel(secondIcon);


     int imgW2 = secondIcon.getIconWidth();
     int imgH2 = secondIcon.getIconHeight();


    secondClickable.setBounds(0, 280, imgW2, imgH2); 


    secondClickable.addMouseListener(new MouseAdapter() {
    @Override
    public void mousePressed(MouseEvent e) {

        // ADD ACTION HEREEE
       
    } 
    @Override
    public void mouseEntered(MouseEvent e) {
      
        secondClickable.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
});

      mainContainer.add(secondClickable);
       mainContainer.setComponentZOrder(secondClickable, 0);

        // search barr
        JTextField searchField = new JTextField() {
            @Override
            protected void paintComponent(Graphics g) {
                if (!isOpaque()) {
                    g.setColor(new Color(0, 0, 0, 0));
                    g.fillRect(0, 0, getWidth(), getHeight());
                }
                super.paintComponent(g);
                if (getText().length() == 0) {
                    Graphics2D g2 = (Graphics2D) g.create();
                    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    g2.setColor(new Color(130, 130, 130)); 
                    g2.setFont(new Font("SansSerif", Font.ITALIC, 25));
                    FontMetrics fm = g2.getFontMetrics();
                    int y = (getHeight() + fm.getAscent() - fm.getDescent()) / 2;
                    g2.drawString("Search here", 45, y); 
                    g2.dispose();
                }
            }
        };

        searchField.setOpaque(false);
        searchField.setBorder(new EmptyBorder(0, 45, 0, 10)); 
        searchField.setForeground(Color.BLACK);
        searchField.setFont(new Font("SansSerif", Font.PLAIN, 25));
        searchField.setBounds(279, 145, 350, 30); 
        searchField.setCaretColor(new Color(0, 0, 0, 0)); 
        searchField.setFocusable(false);

        searchField.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                searchField.setFocusable(true);
                searchField.requestFocusInWindow();
                searchField.setCaretColor(Color.BLACK);
                searchField.getCaret().setBlinkRate(500); 
            }
        });

        searchField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if (searchField.getText().isEmpty()) {
                    searchField.setCaretColor(new Color(0, 0, 0, 0));
                    searchField.setFocusable(false);
                }
            }
        });

        // D2 lagay ang acction/logic kpag nag press si user ng ENTER !
        searchField.addActionListener(e -> System.out.println("Searching: " + searchField.getText()));

        mainContainer.add(searchField);
        
        mainContainer.setComponentZOrder(searchField, 0);

        this.setContentPane(mainContainer);
        this.revalidate();
        this.repaint();
        setVisible(true);
    }
}