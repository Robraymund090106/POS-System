import java.awt.*; 
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import main.model.User;

public class MainDB_Admin extends JFrame {
    User user;

    public MainDB_Admin(User user) {
        this.user = user;
        setTitle("Admin Dashboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
        this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        this.setUndecorated(false); 
        this.setResizable(false);   
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int CW = screenSize.width;
        int CH = screenSize.height;
        setSize(CW, CH); 
        setLocationRelativeTo(null);

        final Image bgImage = new ImageIcon("src/main/image/Admin Main Frame.png").getImage();

        // MAIN FRAME WITH BG
        JPanel mainContainer = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(bgImage, 0, 0, getWidth(), getHeight(), this);
            }
        };

        JPanel canvas = new JPanel(null);
        canvas.setPreferredSize(new Dimension(CW, CH));
        canvas.setOpaque(false);

        // --- PANEL 1: HEADER (Blue) ---
        JPanel header = new JPanel(null);
        header.setBackground(Color.BLUE);
        header.setBounds(30, 50, 999, 90);
        canvas.add(header);

        // Staff Logo
        ImageIcon rawIcon = new ImageIcon("src/main/image/stafflogo.png");
        Image img = rawIcon.getImage();
        Image scaledImg = img.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        ImageIcon staffLogoIcon = new ImageIcon(scaledImg);

        JLabel staffLogo = new JLabel(staffLogoIcon);
        staffLogo.setBounds(600, 20, 50, 50);
        staffLogo.setBorder(BorderFactory.createLineBorder(Color.RED));
        staffLogo.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        staffLogo.setHorizontalTextPosition(JLabel.CENTER);
        staffLogo.setVerticalTextPosition(JLabel.CENTER);

        staffLogo.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                staffLogo.setText("<html><u>Staff</u></html>");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                staffLogo.setText(""); 
            }
        });
        header.add(staffLogo);

        // Staff Text
        JLabel staffText = new JLabel("Staff");
        staffText.setFont(new Font("SansSerif", Font.PLAIN, 30));
        staffText.setForeground(new Color(255, 204, 0));
        staffText.setBounds(670, 20, 100, 50);
        staffText.setCursor(new Cursor(Cursor.HAND_CURSOR));
        staffText.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                staffText.setText("<html><u>Staff</u></html>");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                staffText.setText("Staff");
            }
        });
        header.add(staffText);

        // --- PANEL 3: SIDEBAR ---
        JPanel sidebar = new JPanel(null);
        sidebar.setBounds(30, 130, 169, 800);
        sidebar.setOpaque(false);
        canvas.add(sidebar);

        // MENU BUTTON
        ImageIcon menuIcon = new ImageIcon("src/main/image/menu (1).png");
        JLabel menuiconLabel = new JLabel(menuIcon);
        menuiconLabel.setBounds(20, 110, menuIcon.getIconWidth(), menuIcon.getIconHeight());
        menuiconLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        sidebar.add(menuiconLabel);

        // SNACK BUTTON
        ImageIcon rawfoodIcon = new ImageIcon("src/main/image/burger (1).png");
        JLabel foodiconLabel = new JLabel(rawfoodIcon);
        foodiconLabel.setBounds(20, 230, rawfoodIcon.getIconWidth(), rawfoodIcon.getIconHeight());
        foodiconLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        foodiconLabel.setHorizontalTextPosition(JLabel.CENTER);
        foodiconLabel.setVerticalTextPosition(JLabel.CENTER);
        foodiconLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                foodiconLabel.setText("<html><u>Staff</u></html>");
            }
            @Override
            public void mouseExited(MouseEvent e) {
                foodiconLabel.setText("");
            }
        });
        sidebar.add(foodiconLabel);

        // DRINK BUTTON
        ImageIcon rawdrinkIcon = new ImageIcon("src/main/image/drink (1).png");
        JLabel drinkiconLabel = new JLabel(rawdrinkIcon);
        drinkiconLabel.setBounds(25, 350, rawdrinkIcon.getIconWidth(), rawdrinkIcon.getIconHeight());
        drinkiconLabel.setHorizontalTextPosition(JLabel.CENTER);
        drinkiconLabel.setVerticalTextPosition(JLabel.CENTER);
        drinkiconLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                drinkiconLabel.setText("<html><u>Staff</u></html>");
            }
            @Override
            public void mouseExited(MouseEvent e) {
                drinkiconLabel.setText("");
            }
        });
        sidebar.add(drinkiconLabel);

        // MEAL ICON
        ImageIcon rawMealIcon = new ImageIcon("src/main/image/meal (1).png");
        JLabel mealiconLabel = new JLabel(rawMealIcon);
        mealiconLabel.setBounds(20, 460, rawMealIcon.getIconWidth(), rawMealIcon.getIconHeight());
        mealiconLabel.setHorizontalTextPosition(JLabel.CENTER);
        mealiconLabel.setVerticalTextPosition(JLabel.CENTER);
        mealiconLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                mealiconLabel.setText("<html><u>Staff</u></html>");
            }
            @Override
            public void mouseExited(MouseEvent e) {
                mealiconLabel.setText("");
            }
        });
        sidebar.add(mealiconLabel);

        // SALES ICON
        ImageIcon rawSalesIcon = new ImageIcon("src/main/image/saless (1).png");
        JLabel salesiconLabel = new JLabel(rawSalesIcon);
        salesiconLabel.setBounds(20, 590, rawSalesIcon.getIconWidth(), rawSalesIcon.getIconHeight());
        salesiconLabel.setHorizontalTextPosition(JLabel.CENTER);
        salesiconLabel.setVerticalTextPosition(JLabel.CENTER);
        salesiconLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                salesiconLabel.setText("<html><u>Staff</u></html>");
            }
            @Override
            public void mouseExited(MouseEvent e) {
                salesiconLabel.setText("");
            }
        });
        sidebar.add(salesiconLabel);

       
        mainContainer.add(canvas);
        this.add(mainContainer);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        User testUser = new User("yna", "hi", "rfvvh", "girl", null, null, "admin", true, 00, 19);
        new MainDB_Admin(testUser);
    }
}