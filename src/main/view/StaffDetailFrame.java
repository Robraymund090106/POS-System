package main.view;

import java.awt.Color;
import java.awt.Font;
import javax.swing.*;

import main.model.User;

import java.awt.Toolkit;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import main.database.DatabaseManager;
import main.model.User;

public class StaffDetailFrame extends JFrame {

    private JComboBox<String> positionCombo;
    

    public StaffDetailFrame(String staffName) {
        setTitle("Staff Profile");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setUndecorated(true); 
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        User user = DatabaseManager.getUserByUsername(staffName);
        String firstName = user.getFullname().split(" ")[0];
        String lastName = user.getFullname().split(" ")[1];
        
        JPanel backgroundPanel = new JPanel();
        backgroundPanel.setLayout(null);
        backgroundPanel.setBackground(Color.WHITE);

        add(backgroundPanel);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;

        JLabel title = new JLabel("User Details", SwingConstants.CENTER);
        title.setBounds(0, 0, screenWidth, 100); 
        title.setFont(new Font("Segoe UI", Font.BOLD, 35)); 
        title.setOpaque(true); 
        title.setBackground(new Color(65, 85, 160));    
        title.setForeground(new Color(255, 215, 0));  

        backgroundPanel.add(title);
        backgroundPanel.add(title);
        backgroundPanel.add(title);

        JLabel fnLabel = new JLabel("First Name");
        fnLabel.setBounds(320, 180, 150, 30); 
        fnLabel.setFont(new Font("Arial", Font.BOLD, 20));
        fnLabel.setForeground(Color.BLACK);
        backgroundPanel.add(fnLabel);

        JLabel fname = new JLabel(firstName);
        fname.setBounds(320, 210, 250, 50); 
        fname.setFont(new Font("Arial", Font.PLAIN, 18));
        fname.setBorder(BorderFactory.createLineBorder(new Color(65, 85, 160)));
        backgroundPanel.add(fname);

        JLabel lstLabel = new JLabel("Last Name");
        lstLabel.setBounds(640, 180, 150, 30); 
        lstLabel.setFont(new Font("Arial", Font.BOLD, 20));
        lstLabel.setForeground(Color.BLACK);
        backgroundPanel.add(lstLabel);

        JLabel lastname = new JLabel(lastName);
        lastname.setBounds(640, 210, 250, 50);
        lastname.setFont(new Font("Arial", Font.PLAIN, 18));
        lastname.setBorder(BorderFactory.createLineBorder(new Color(65, 85, 160)));
        backgroundPanel.add(lastname);

        JLabel fullnmLabel = new JLabel("Full Name");
        fullnmLabel.setBounds(950, 180, 150, 30); 
        fullnmLabel.setFont(new Font("Arial", Font.BOLD, 20));
        fullnmLabel.setForeground(Color.BLACK);
        backgroundPanel.add(fullnmLabel);
        
        JLabel fullname = new JLabel(user.getFullname());
        fullname.setBounds(950, 210, 250, 50);
        fullname.setFont(new Font("Arial", Font.PLAIN, 18));
        fullname.setBorder(BorderFactory.createLineBorder(new Color(65, 85, 160)));
        backgroundPanel.add(fullname);
        
        JLabel positionLabel = new JLabel("Position");
        positionLabel.setBounds(950, 330, 150, 30); 
        positionLabel.setFont(new Font("Arial", Font.BOLD, 20));
        positionLabel.setForeground(Color.BLACK);
        backgroundPanel.add(positionLabel);
       
        String[] position = {
            "Admin","Staff"
        };
        positionCombo = new JComboBox<>(position);
        positionCombo.setSize(105, 40);
        positionCombo.setFont(new Font("Arial", Font.PLAIN, 17));
        positionCombo.setBounds(950, 360, 250, 50); 
        positionCombo.setBorder(BorderFactory.createLineBorder(new Color(65, 85, 160)));
        backgroundPanel.add(positionCombo);

  
        JLabel genderLabel = new JLabel("Gender");
        genderLabel.setBounds(320, 450, 150, 30); 
        genderLabel.setFont(new Font("Arial", Font.BOLD, 20));
        genderLabel.setForeground(Color.BLACK);
        backgroundPanel.add(genderLabel);

        JLabel genderField = new JLabel(user.getgender()); 
        genderField.setBounds(320, 490, 250, 50);
        genderField.setFont(new Font("Arial", Font.PLAIN, 18));
        genderField.setBorder(BorderFactory.createLineBorder(new Color(65, 85, 160)));
        backgroundPanel.add(genderField);

        JLabel usnLabel = new JLabel("Username");
        usnLabel.setBounds(320, 330, 150, 30); 
        usnLabel.setFont(new Font("Arial", Font.BOLD, 20));
        usnLabel.setForeground(Color.BLACK);
        backgroundPanel.add(usnLabel);

        JLabel usnfield = new JLabel(user.getUsername());
        usnfield.setBounds(320, 360, 250, 50);
        usnfield.setFont(new Font("Arial", Font.PLAIN, 18));
        usnfield.setBorder(BorderFactory.createLineBorder(new Color(65, 85, 160)));
        backgroundPanel.add(usnfield);

        JLabel bdayLabel = new JLabel("Birthday");
        bdayLabel.setBounds(640, 330, 150, 30);
        bdayLabel.setFont(new Font("Arial ", Font.BOLD, 20));
        bdayLabel.setForeground(Color.BLACK);
        backgroundPanel.add(bdayLabel);

        JLabel bdayField = new JLabel(user.getBday());
        bdayField.setBounds(640, 360, 250, 50);
        bdayField.setFont(new Font("Arial", Font.PLAIN, 18));
        bdayField.setBorder(BorderFactory.createLineBorder(new Color(65, 85, 160)));
        backgroundPanel.add(bdayField);

        JLabel statsLabel = new JLabel("Status");
        statsLabel.setBounds(640, 450, 150, 30);
        statsLabel.setFont(new Font("Arial ", Font.BOLD, 20));
        statsLabel.setForeground(Color.BLACK);
        backgroundPanel.add(statsLabel);

        String status = user.isActive() ? "Active" : "Inactive";

        JLabel statsField = new JLabel(status);
        statsField.setBounds(640, 490, 250, 50);
        statsField.setFont(new Font("Arial", Font.PLAIN, 18));
        statsField.setBorder(BorderFactory.createLineBorder(new Color(65, 85, 160)));
        backgroundPanel.add(statsField);

        JLabel passLabel = new JLabel("Password");
        passLabel.setBounds(950, 450, 150, 30); 
        passLabel.setFont(new Font("Arial", Font.BOLD, 20));
        passLabel.setForeground(Color.BLACK);
        backgroundPanel.add(passLabel);
        
        JPasswordField passField = new JPasswordField();
        passField.setBounds(950, 490, 250, 50);
        passField.setFont(new Font("Arial", Font.PLAIN, 18));
        passField.setBorder(BorderFactory.createLineBorder(new Color(65, 85, 160)));
        backgroundPanel.add(passField);
        
        JCheckBox cnshowPass = new JCheckBox("Show Password");
        cnshowPass.setSize(200, 30);
        cnshowPass.setBounds(950, 550, 200, 30);
        cnshowPass.setOpaque(false);
        cnshowPass.setFont(new Font("Arial", Font.PLAIN, 14));
        cnshowPass.setForeground(Color.BLACK);

        cnshowPass.addActionListener(e -> {
            if (cnshowPass.isSelected()) {
                passField.setEchoChar((char) 0);
            } else {
                passField.setEchoChar('•');
            }
        });
        backgroundPanel.add(cnshowPass);

        JButton cnfm = new JButton("Confirm Changes");
        cnfm.setBounds(840, 640, 240, 60);
        cnfm.setFont(new Font("Arial", Font.BOLD, 20));
        cnfm.setBackground(new Color(165, 215, 155));
        cnfm.setForeground(Color.WHITE);
        cnfm.setFocusPainted(false);
        backgroundPanel.add(cnfm);

        cnfm.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               

                int option = JOptionPane.showConfirmDialog(
                    null, 
                    "Are you sure you want to apply these changes?", 
                    "Confirm Changes", 
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE
                );
                if (option != JOptionPane.YES_OPTION) {
                    return; 
                }
                JOptionPane.showMessageDialog(null, "Changes applied successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

                String selectedPosition = (String) positionCombo.getSelectedItem();
                selectedPosition.toUpperCase();
                
                String newPassword = new String(passField.getPassword());
                user.setRole(selectedPosition);
                DatabaseManager.updateUserRole(user.getUserId(), selectedPosition);
            }
        });
          

        JButton bck = new JButton("Back");
        bck.setBounds(500, 640, 150, 60);
        bck.setBackground(new Color(220, 100, 100));
        bck.setForeground(Color.WHITE);
        bck.setFont(new Font("Arial", Font.BOLD, 20));
        bck.setFocusPainted(false);
        
        bck.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int option = JOptionPane.showConfirmDialog(
                    null, 
                    "Are you sure you want to exit?", 
                    "Confirm Exit", 
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE
                );
                
                if (option == JOptionPane.YES_OPTION) {
                    dispose();
                }
            }
        });

        backgroundPanel.add(bck);

        setVisible(true);

    }
}