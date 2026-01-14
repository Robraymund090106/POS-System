package main.view;

import javax.swing.*;
import main.model.User;

public class MainDashboard extends JFrame{
    private User user;
    public MainDashboard(User user) {

        this.user = user;

        JLabel welcomeLabel = new JLabel("Welcome, " + user.getUsername() + "!");
        setTitle("Main Dashboard");
        add(welcomeLabel);
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
