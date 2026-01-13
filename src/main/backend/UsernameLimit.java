package main.backend;


import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class UsernameLimit extends javax.swing.text.PlainDocument{
    private int limit;
    private JTextField textField;

    public UsernameLimit(int limit, JTextField textField) {
        this.limit = limit;
        this.textField = textField;
    }

    public void insertString(int offset, String str, javax.swing.text.AttributeSet attr) throws javax.swing.text.BadLocationException {
        if (str == null)
            return;

        if ((getLength() + str.length()) <= limit) {
            super.insertString(offset, str, attr);
        }
        else{
            JOptionPane.showMessageDialog(null, "Must only contain a maximum of " + limit + " characters", "Error", JOptionPane.ERROR_MESSAGE);

            textField.setText("");

            java.awt.Toolkit.getDefaultToolkit().beep();
    }


}
}

