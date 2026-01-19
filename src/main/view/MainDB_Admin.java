package main.view;

import javax.swing.*;

import main.model.User;

public class MainDB_Admin extends JFrame{
    User user;
    public MainDB_Admin(User user){
        this.user = user;
        setTitle("Admin Dashboard");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
