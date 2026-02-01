package main.view;

import javax.swing.*;

import main.backend.PasswordLimit;
import main.backend.UsernameLimit;
import main.backend.emailvalidator;
import main.database.DatabaseManager;
import main.model.User;

import java.awt.*;
import java.awt.event.*;

public class RegisterFrame extends JFrame {

    private JLabel monthLabel, dayLabel, yearLabel;
    private JComboBox<String> monthCombo, dayCombo, yearCombo;
    private JLabel genderLabel;
    private JRadioButton maleCheckBox, femaleCheckBox;
    private emailvalidator vl = new emailvalidator();

    public RegisterFrame() {

        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon bgIcon = new ImageIcon("src\\main\\image\\_Sign Up Frame.png");
                g.drawImage(bgIcon.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };

        backgroundPanel.setLayout(null);

        JLabel title = new JLabel("Sign Up", SwingConstants.CENTER);
        title.setSize(500, 60);
        title.setFont(new Font("Arial", Font.BOLD, 45));
        title.setForeground(new Color(255, 215, 0)); 
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
        fnLabel.setForeground(Color.WHITE);
        backgroundPanel.add(fnLabel);

        JTextField fname = new JTextField();
        fname.setSize(250, 50);
        fname.setFont(new Font("Arial", Font.PLAIN, 18));
        fname.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        backgroundPanel.add(fname);
        fname.setDocument(new UsernameLimit(30, fname));

        // Last Name
        JLabel lnLabel = new JLabel("Last Name");
        lnLabel.setSize(150, 30);
        lnLabel.setFont(new Font("Arial ", Font.BOLD, 20));
        lnLabel.setForeground(Color.WHITE);
        backgroundPanel.add(lnLabel);

        JTextField lname = new JTextField();
        lname.setSize(250, 50);
        lname.setFont(new Font("Arial", Font.PLAIN, 18));
        lname.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        backgroundPanel.add(lname);
        lname.setDocument(new UsernameLimit(30, lname));

        // Username
        JLabel userLabel = new JLabel("Username");
        userLabel.setSize(150, 30);
        userLabel.setFont(new Font("Arial ", Font.BOLD, 20));
        userLabel.setForeground(Color.WHITE);
        backgroundPanel.add(userLabel);

        JTextField username = new JTextField();
        username.setSize(250, 50);
        username.setFont(new Font("Arial", Font.PLAIN, 18));
        username.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        backgroundPanel.add(username);
        username.setDocument(new UsernameLimit(30, username));

        // Password
        JLabel psswordLabel = new JLabel("Password");
        psswordLabel.setSize(150, 30);
        psswordLabel.setFont(new Font("Arial ", Font.BOLD, 20));
        psswordLabel.setForeground(Color.WHITE);
        backgroundPanel.add(psswordLabel);

        JButton passwordInfoBtn = new JButton("?");
        passwordInfoBtn.setSize(60, 30);
        passwordInfoBtn.setFont(new Font("Arial", Font.BOLD, 16));
        passwordInfoBtn.setForeground(new Color(255, 215, 0));
        passwordInfoBtn.setFocusPainted(false);
        passwordInfoBtn.addActionListener(e -> {
            String requirements = "Password Requirements:\n\n" +
                                 "• 8-20 characters long\n" +
                                 "• At least 1 Uppercase letter (A-Z)\n" +
                                 "• At least 1 Lowercase letter (a-z)\n" +
                                 "• At least 1 Digit (0-9)\n" +
                                 "• At least 1 Special character (!@#$%^&*)-+)";
            JOptionPane.showMessageDialog(null, requirements, "Password Requirements", JOptionPane.INFORMATION_MESSAGE);
        });
        backgroundPanel.add(passwordInfoBtn);

        JPasswordField psswordField = new JPasswordField();
        psswordField.setSize(250, 50);
        psswordField.setFont(new Font("Arial", Font.PLAIN, 18));
        psswordField.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        backgroundPanel.add(psswordField);
        psswordField.setDocument(new UsernameLimit(30, psswordField));

        char defaultEcho = psswordField.getEchoChar();

        // Confirm Password
        JLabel cnpsswordLabel = new JLabel("Confirm Password");
        cnpsswordLabel.setSize(200, 30);
        cnpsswordLabel.setFont(new Font("Arial ", Font.BOLD, 18));
        cnpsswordLabel.setForeground(Color.WHITE);
        backgroundPanel.add(cnpsswordLabel);

        JPasswordField cnpassword = new JPasswordField();
        cnpassword.setSize(250, 50);
        cnpassword.setFont(new Font("Arial ", Font.PLAIN, 18));
        cnpassword.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        backgroundPanel.add(cnpassword);
        cnpassword.setDocument(new PasswordLimit(20, cnpassword));

        JCheckBox cnshowPass = new JCheckBox("Show Password");
        cnshowPass.setSize(200, 30);
        cnshowPass.setOpaque(false);
        cnshowPass.setFont(new Font("Arial", Font.PLAIN, 14));
        cnshowPass.setForeground(Color.WHITE);

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
        bdayLabel.setForeground(Color.WHITE);
        backgroundPanel.add(bdayLabel);

        monthLabel = new JLabel("Month");
        monthLabel.setSize(80, 30);
        monthLabel.setFont(new Font("Arial ", Font.BOLD, 18));
        monthLabel.setForeground(Color.WHITE);
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
        dayLabel.setForeground(Color.WHITE);
        backgroundPanel.add(dayLabel);

        String[] days = new String[31];
        for (int i = 1; i <= 31; i++) days[i - 1] = String.valueOf(i);

        dayCombo = new JComboBox<>(days);
        dayCombo.setSize(65, 40);
        dayCombo.setFont(new Font("Arial", Font.PLAIN, 16));
        dayCombo.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        backgroundPanel.add(dayCombo);

        yearLabel = new JLabel("Year");
        yearLabel.setSize(60, 30);
        yearLabel.setFont(new Font("Arial ", Font.BOLD, 18));
        yearLabel.setForeground(Color.WHITE);
        backgroundPanel.add(yearLabel);

        String[] years = new String[100];
        int currentYear = 2026;
        for (int i = 0; i < 100; i++) years[i] = String.valueOf(currentYear - i);

        yearCombo = new JComboBox<>(years);
        yearCombo.setSize(80, 40);
        yearCombo.setFont(new Font("Arial", Font.PLAIN, 18));
        yearCombo.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        backgroundPanel.add(yearCombo);

        genderLabel = new JLabel("Gender: ");
        genderLabel.setSize(100, 30);
        genderLabel.setFont(new Font("Arial ", Font.BOLD, 18));
        genderLabel.setForeground(Color.WHITE);
        backgroundPanel.add(genderLabel);

        maleCheckBox = new JRadioButton("Male");
        maleCheckBox.setSize(70, 40);
        maleCheckBox.setFont(new Font("Arial", Font.PLAIN, 18));
        maleCheckBox.setForeground(Color.WHITE);
        maleCheckBox.setOpaque(false);
        backgroundPanel.add(maleCheckBox);

        femaleCheckBox = new JRadioButton("Female");
        femaleCheckBox.setSize(90, 40);
        femaleCheckBox.setFont(new Font("Arial", Font.PLAIN, 18));
        femaleCheckBox.setForeground(Color.WHITE);
        femaleCheckBox.setOpaque(false);
        backgroundPanel.add(femaleCheckBox);

        JButton cnfm = new JButton("Confirm");
        cnfm.setBounds(750, 560, 150, 60);
        cnfm.setFont(new Font("Arial", Font.BOLD, 20));
        cnfm.setBackground(new Color(165, 215, 155));
        cnfm.setForeground(Color.WHITE);
        cnfm.setFocusPainted(false);
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(maleCheckBox);
        genderGroup.add(femaleCheckBox);

         
        cnfm.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                String firstName = fname.getText();
                String lastName = lname.getText();
                String user = username.getText();
                String password = new String(psswordField.getPassword());
                String confirmPassword = new String(cnpassword.getPassword());
                String month = (String) monthCombo.getSelectedItem();
                String email = "email@email.com";
                String day = (String) dayCombo.getSelectedItem();
                String year = (String) yearCombo.getSelectedItem();
                String gender = maleCheckBox.isSelected() ? "Male" : (femaleCheckBox.isSelected() ? "Female" : "");
                String birthday = month + " " + day + " " + year;

                if(emailvalidator.isValidUsername(user) && emailvalidator.isValidPassword(password) && emailvalidator.isValidName(firstName) && emailvalidator.isValidName(lastName) && emailvalidator.samePassword(password, confirmPassword)){
                    String fullName = firstName + " " + lastName;
                    User newUser = new User(user, password, fullName, gender, birthday, "email@email.com", "STAFF", false, 0, 0);
                    if (gender.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Please select a gender.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    
                    if(DatabaseManager.addUser(newUser)){
                        JOptionPane.showMessageDialog(null, "Registration successful! Wait for an Admin to activate your account", "Success", JOptionPane.INFORMATION_MESSAGE);
                        dispose();
                        new LoginFrame();
                    } else {
                        JOptionPane.showMessageDialog(null, "Registration failed! Username may already exist.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                
                } else if(!emailvalidator.isValidUsername(user) && emailvalidator.isValidPassword(password) && emailvalidator.isValidName(firstName) && emailvalidator.isValidName(lastName) && emailvalidator.samePassword(password, confirmPassword)){
                        JOptionPane.showMessageDialog(null, "Registration failed! Username is Invalid", "Error", JOptionPane.ERROR_MESSAGE);
                        username.setText("");

                } else if(emailvalidator.isValidUsername(user) && !emailvalidator.isValidPassword(password) && emailvalidator.isValidName(firstName) && emailvalidator.isValidName(lastName) && emailvalidator.samePassword(password, confirmPassword)){
                        JOptionPane.showMessageDialog(null, "Registration failed! Password is Invalid", "Error", JOptionPane.ERROR_MESSAGE);
                        psswordField.setText("");
                        cnpassword.setText("");

                } else if(emailvalidator.isValidUsername(user) && emailvalidator.isValidPassword(password) && !emailvalidator.isValidName(firstName) && emailvalidator.isValidName(lastName) && emailvalidator.samePassword(password, confirmPassword)){
                        JOptionPane.showMessageDialog(null, "Registration failed! First Name is Invalid", "Error", JOptionPane.ERROR_MESSAGE);
                        fname.setText("");

                } else if(emailvalidator.isValidUsername(user) && emailvalidator.isValidPassword(password) && emailvalidator.isValidName(firstName) && !emailvalidator.isValidName(lastName) && emailvalidator.samePassword(password, confirmPassword)){
                        JOptionPane.showMessageDialog(null, "Registration failed! Last Name is Invalid", "Error", JOptionPane.ERROR_MESSAGE);
                        lname.setText("");

                } else if(emailvalidator.isValidUsername(user) && emailvalidator.isValidPassword(password) && emailvalidator.isValidName(firstName) && emailvalidator.isValidName(lastName) && !emailvalidator.samePassword(password, confirmPassword)){
                        JOptionPane.showMessageDialog(null, "Registration failed! Passwords do not match", "Error", JOptionPane.ERROR_MESSAGE);
                        psswordField.setText("");
                        cnpassword.setText("");

                } else if(!emailvalidator.isValidUsername(user) && !emailvalidator.isValidPassword(password)){
                        JOptionPane.showMessageDialog(null, "Registration failed! Username and Password are Invalid", "Error", JOptionPane.ERROR_MESSAGE);
                        username.setText("");
                        psswordField.setText("");
                        cnpassword.setText("");

                } else if(!emailvalidator.isValidUsername(user) && !emailvalidator.isValidName(firstName)) {
                JOptionPane.showMessageDialog(null, "Invalid Username and First Name", "Error", JOptionPane.ERROR_MESSAGE);

                // 2. Username + Last Name
                } else if(!emailvalidator.isValidUsername(user) && !emailvalidator.isValidName(lastName)) {
                    JOptionPane.showMessageDialog(null, "Invalid Username and Last Name", "Error", JOptionPane.ERROR_MESSAGE);

                // 3. Username + Password Mismatch
                } else if(!emailvalidator.isValidUsername(user) && !emailvalidator.samePassword(password, confirmPassword)) {
                    JOptionPane.showMessageDialog(null, "Invalid Username and Passwords do not match", "Error", JOptionPane.ERROR_MESSAGE);

                // 4. Password + First Name
                } else if(!emailvalidator.isValidPassword(password) && !emailvalidator.isValidName(firstName)) {
                    JOptionPane.showMessageDialog(null, "Invalid Password and First Name", "Error", JOptionPane.ERROR_MESSAGE);

                // 5. Password + Last Name
                } else if(!emailvalidator.isValidPassword(password) && !emailvalidator.isValidName(lastName)) {
                    JOptionPane.showMessageDialog(null, "Invalid Password and Last Name", "Error", JOptionPane.ERROR_MESSAGE);

                // 6. Password + Password Mismatch (Technically password is invalid AND doesn't match)
                } else if(!emailvalidator.isValidPassword(password) && !emailvalidator.samePassword(password, confirmPassword)) {
                    JOptionPane.showMessageDialog(null, "Password format is invalid and they do not match", "Error", JOptionPane.ERROR_MESSAGE);

                // 7. First Name + Last Name
                } else if(!emailvalidator.isValidName(firstName) && !emailvalidator.isValidName(lastName)) {
                    JOptionPane.showMessageDialog(null, "Both First and Last Names are invalid", "Error", JOptionPane.ERROR_MESSAGE);

                // 8. First Name + Password Mismatch
                } else if(!emailvalidator.isValidName(firstName) && !emailvalidator.samePassword(password, confirmPassword)) {
                    JOptionPane.showMessageDialog(null, "Invalid First Name and Passwords do not match", "Error", JOptionPane.ERROR_MESSAGE);

                // 9. Last Name + Password Mismatch
                } else if(!emailvalidator.isValidName(lastName) && !emailvalidator.samePassword(password, confirmPassword)) {
                    JOptionPane.showMessageDialog(null, "Invalid Last Name and Passwords do not match", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else  {
                    JOptionPane.showMessageDialog(null, "Registration failed! Please check all fields.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    
        );
        
        
        JButton bck = new JButton("Back");
        bck.setSize(150, 60);
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
                    new LoginFrame();
                }
            }
        });
        
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

                fnLabel.setLocation(centerX - 350, centerY - 80);
                fname.setLocation(centerX - 350, centerY - 50);

                lnLabel.setLocation(centerX - 350, centerY + 30);
                lname.setLocation(centerX - 350, centerY + 60);

                userLabel.setLocation(centerX + 150, centerY - 80);
                username.setLocation(centerX + 150, centerY - 50);

                psswordLabel.setLocation(centerX + 150, centerY + 30);
                passwordInfoBtn.setLocation(centerX + 270, centerY + 25);
                psswordField.setLocation(centerX + 150, centerY + 60);

                // Birthday combo boxes
                bdayLabel.setLocation(centerX - 350, centerY + 110);

                monthLabel.setLocation(centerX - 350, centerY + 140);
                monthCombo.setLocation(centerX - 350, centerY + 170);

                dayLabel.setLocation(centerX - 244, centerY + 140);
                dayCombo.setLocation(centerX - 244, centerY + 170);

                yearLabel.setLocation(centerX - 180, centerY + 140);
                yearCombo.setLocation(centerX - 180, centerY + 170);

                // Gender checkboxes positioned below birthday
                genderLabel.setLocation(centerX - 350, centerY + 223);
                maleCheckBox.setLocation(centerX - 280, centerY + 220);
                femaleCheckBox.setLocation(centerX - 215, centerY + 220);
                
                cnpsswordLabel.setLocation(centerX + 150, centerY + 130);
                cnpassword.setLocation(centerX + 150, centerY + 160);
                cnshowPass.setLocation(centerX + 150, centerY + 210);

                cnfm.setLocation(centerX - - 60, centerY + 330);
                bck.setLocation(centerX - 165, centerY + 330);
            }
        });
    }
}
