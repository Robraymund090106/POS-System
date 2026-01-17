package main.view;

import javax.swing.*;

import main.backend.PasswordLimit;
import main.backend.UsernameLimit;

import java.awt.*;
import java.awt.event.*;

public class RegisterFrame extends JFrame {

    private JLabel Username;
    private JTextField txtUsername;
    private JLabel Password;
    private JPasswordField txtPassword;
    private JLabel ConfirmPassword;
    private JPasswordField txtConfirmPassword;
    private JLabel Email;
    private JTextField txtEmail;

    private JLabel monthLabel, dayLabel, yearLabel;
    private JComboBox<String> monthCombo, dayCombo, yearCombo;

    private JLabel genderLabel;
    private JRadioButton maleCheckBox, femaleCheckBox;

    public RegisterFrame() {

        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon bgIcon = new ImageIcon("src\\main\\image\\Email.png");
                g.drawImage(bgIcon.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };

        backgroundPanel.setLayout(null);

        JLabel title = new JLabel("Sign Up", SwingConstants.CENTER);
        title.setSize(500, 60);
        title.setFont(new Font("Arial", Font.BOLD, 45));
        backgroundPanel.add(title);

        add(backgroundPanel);
        setTitle("Register");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setVisible(true);

        // First Name
        JLabel fnLabel = new JLabel("First Name");
        fnLabel.setSize(150, 30);
        fnLabel.setFont(new Font("Arial ", Font.BOLD, 20));
        backgroundPanel.add(fnLabel);

        JTextField fname = new JTextField();
        fname.setSize(200, 50);
        fname.setFont(new Font("Arial", Font.PLAIN, 18));
        fname.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        backgroundPanel.add(fname);
        fname.setDocument(new UsernameLimit(30, fname));

        // Last Name
        JLabel lnLabel = new JLabel("Last Name");
        lnLabel.setSize(150, 30);
        lnLabel.setFont(new Font("Arial ", Font.BOLD, 20));
        backgroundPanel.add(lnLabel);

        JTextField lname = new JTextField();
        lname.setSize(200, 50);
        lname.setFont(new Font("Arial", Font.PLAIN, 18));
        lname.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        backgroundPanel.add(lname);
        lname.setDocument(new UsernameLimit(30, lname));

        // Username
        JLabel userLabel = new JLabel("Username");
        userLabel.setSize(150, 30);
        userLabel.setFont(new Font("Arial ", Font.BOLD, 20));
        backgroundPanel.add(userLabel);

        JTextField username = new JTextField();
        username.setSize(200, 50);
        username.setFont(new Font("Arial", Font.PLAIN, 18));
        username.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        backgroundPanel.add(username);
        username.setDocument(new UsernameLimit(30, username));

        // Password
        JLabel psswordLabel = new JLabel("Password");
        psswordLabel.setSize(150, 30);
        psswordLabel.setFont(new Font("Arial ", Font.BOLD, 20));
        backgroundPanel.add(psswordLabel);

        JPasswordField psswordField = new JPasswordField();
        psswordField.setSize(200, 50);
        psswordField.setFont(new Font("Arial", Font.PLAIN, 18));
        psswordField.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        backgroundPanel.add(psswordField);
        psswordField.setDocument(new UsernameLimit(30, psswordField));

        char defaultEcho = psswordField.getEchoChar();

        // Confirm Password
        JLabel cnpsswordLabel = new JLabel("Confirm Password");
        cnpsswordLabel.setSize(200, 30);
        cnpsswordLabel.setFont(new Font("Arial ", Font.BOLD, 18));
        backgroundPanel.add(cnpsswordLabel);

        JPasswordField cnpassword = new JPasswordField();
        cnpassword.setSize(200, 50);
        cnpassword.setFont(new Font("Arial ", Font.PLAIN, 18));
        cnpassword.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        backgroundPanel.add(cnpassword);
        cnpassword.setDocument(new PasswordLimit(20, cnpassword));

        JCheckBox cnshowPass = new JCheckBox("Show Password");
        cnshowPass.setSize(200, 30);
        cnshowPass.setOpaque(false);
        cnshowPass.setFont(new Font("Arial", Font.PLAIN, 14));

        cnshowPass.addActionListener(e -> {
            if (cnshowPass.isSelected()) {
                psswordField.setEchoChar((char) 0);
                cnpassword.setEchoChar((char) 0);
            } else {
                psswordField.setEchoChar(defaultEcho);
                cnpassword.setEchoChar(defaultEcho);
            }
        });
        backgroundPanel.add(cnshowPass);

        // Birthday
        JLabel bdayLabel = new JLabel("Birthday");
        bdayLabel.setSize(200, 30);
        bdayLabel.setFont(new Font("Arial ", Font.BOLD, 18));
        backgroundPanel.add(bdayLabel);

        monthLabel = new JLabel("Month");
        monthLabel.setSize(80, 30);
        monthLabel.setFont(new Font("Arial ", Font.BOLD, 18));
        backgroundPanel.add(monthLabel);

        String[] months = {
            "January","February","March","April","May","June",
            "July","August","September","October","November","December"
        };
        monthCombo = new JComboBox<>(months);
        monthCombo.setSize(105, 40);
        monthCombo.setFont(new Font("Arial", Font.PLAIN, 17));
        backgroundPanel.add(monthCombo);

        dayLabel = new JLabel("Day");
        dayLabel.setSize(40, 30);
        dayLabel.setFont(new Font("Arial ", Font.BOLD, 18));
        backgroundPanel.add(dayLabel);

        String[] days = new String[31];
        for (int i = 1; i <= 31; i++) days[i - 1] = String.valueOf(i);

        dayCombo = new JComboBox<>(days);
        dayCombo.setSize(50, 40);
        dayCombo.setFont(new Font("Arial", Font.PLAIN, 16));
        dayCombo.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        backgroundPanel.add(dayCombo);

        yearLabel = new JLabel("Year");
        yearLabel.setSize(60, 30);
        yearLabel.setFont(new Font("Arial ", Font.BOLD, 18));
        backgroundPanel.add(yearLabel);

        String[] years = new String[100];
        int currentYear = 2026;
        for (int i = 0; i < 100; i++) years[i] = String.valueOf(currentYear - i);

        yearCombo = new JComboBox<>(years);
        yearCombo.setSize(80, 40);
        yearCombo.setFont(new Font("Arial", Font.PLAIN, 18));
        yearCombo.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        backgroundPanel.add(yearCombo);


        genderLabel = new JLabel("Gender");
        genderLabel.setSize(100, 30);
        genderLabel.setFont(new Font("Arial ", Font.BOLD, 20));
        backgroundPanel.add(genderLabel);

        maleCheckBox = new JRadioButton("Male");
        maleCheckBox.setSize(100, 40);
        maleCheckBox.setFont(new Font("Arial", Font.PLAIN, 18));
        maleCheckBox.setOpaque(false);
        backgroundPanel.add(maleCheckBox);

        femaleCheckBox = new JRadioButton("Female");
        femaleCheckBox.setSize(100, 40);
        femaleCheckBox.setFont(new Font("Arial", Font.PLAIN, 18));
        femaleCheckBox.setOpaque(false);
        backgroundPanel.add(femaleCheckBox);

        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(maleCheckBox);
        genderGroup.add(femaleCheckBox);

         JButton cnfm = new JButton("Confirm");
        cnfm.setSize(150, 60);
        cnfm.setFont(new Font("Arial", Font.BOLD, 20));
        cnfm.setBackground(Color.BLACK);
        cnfm.setForeground(Color.WHITE);
        cnfm.setFocusPainted(false);
        
        JButton bck = new JButton("Back");
        bck.setSize(150, 60);
        bck.setFont(new Font("Arial", Font.BOLD, 20));
        bck.setFocusPainted(false);
        backgroundPanel.add(cnfm);
        backgroundPanel.add(bck); 
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int w = backgroundPanel.getWidth();
                int h = backgroundPanel.getHeight();
                int centerX = w / 2;
                int centerY = (h / 2) - 100;

               title.setLocation(centerX - 240, centerY - 250);

                fnLabel.setLocation(centerX - 350, centerY - 100);
                fname.setLocation(centerX - 350, centerY - 50);

                lnLabel.setLocation(centerX - 350, centerY + 10);
                lname.setLocation(centerX - 350, centerY + 60);

                userLabel.setLocation(centerX + 150, centerY - 100);
                username.setLocation(centerX + 150, centerY - 50);

                psswordLabel.setLocation(centerX + 150, centerY + 10);
                psswordField.setLocation(centerX + 150, centerY + 60);

                // Birthday combo boxes
                bdayLabel.setLocation(centerX - 350, centerY + 110);

                monthLabel.setLocation(centerX - 330, centerY + 140);
                monthCombo.setLocation(centerX - 363, centerY + 170);

                dayLabel.setLocation(centerX - 260, centerY + 140);
                dayCombo.setLocation(centerX - 257, centerY + 170);

                yearLabel.setLocation(centerX - 220, centerY + 140);
                yearCombo.setLocation(centerX - 210, centerY + 170);

                // Gender checkboxes positioned below birthday
                genderLabel.setLocation(centerX - 350, centerY + 220);
                maleCheckBox.setLocation(centerX - 280, centerY + 220);
                femaleCheckBox.setLocation(centerX - 220, centerY + 220);
                
                cnpsswordLabel.setLocation(centerX + 150, centerY + 110);
                cnpassword.setLocation(centerX + 150, centerY + 160);
                cnshowPass.setLocation(centerX + 150, centerY + 220);


                cnfm.setLocation(centerX - 5, centerY + 330);
                bck.setLocation(centerX - 165, centerY + 330);
            }
        });
    }
}
