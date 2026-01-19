package main.view;

import javax.swing.JFrame;

import main.model.User;

public class ReceiptFrame extends JFrame{
    User user;

    
    public ReceiptFrame(User user) {
        this.user = user;
        setTitle("Receipt");
        setSize(400, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

}
