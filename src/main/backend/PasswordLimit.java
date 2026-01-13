package main.backend;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

public class PasswordLimit extends javax.swing.text.PlainDocument{
    private int limit;
    private JPasswordField passwordField;

    public PasswordLimit(int limit, JPasswordField passwordField) {
        this.limit = limit;
        this.passwordField = passwordField;
    }

    public void insertString(int offset, String str, javax.swing.text.AttributeSet attr) throws javax.swing.text.BadLocationException {
        if (str == null)
            return;

        if ((getLength() + str.length()) <= limit ) {
            super.insertString(offset, str, attr);
        }
        else{
            JOptionPane.showMessageDialog(null, "Must only contain a maximum of " + limit + " characters", "Error", JOptionPane.ERROR_MESSAGE);

            passwordField.setText("");

            java.awt.Toolkit.getDefaultToolkit().beep();
    }


}
}
