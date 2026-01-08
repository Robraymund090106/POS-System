package pos.auth;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class User extends JFrame {

    public User() {
        setTitle("PILI KA !!");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(1, 2));

        add(createRolePanel("ADMIN", new Color(10, 30, 80)));
        add(createRolePanel("STAFF", new Color(212, 175, 55)));

        setVisible(true);
    }

    private JPanel createRolePanel(String role, Color bgColor) {
        JPanel panel = new JPanel();
        panel.setBackground(bgColor);
        panel.setLayout(new GridBagLayout());

        JLabel label = new JLabel(role);
        label.setFont(new Font("Arial", Font.BOLD, 40));
        label.setForeground(Color.WHITE);

        panel.add(label);

        panel.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();              
                new LoginFrame(role);   
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                panel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                panel.setCursor(Cursor.getDefaultCursor());
            }
        });

        return panel;
    }
}
