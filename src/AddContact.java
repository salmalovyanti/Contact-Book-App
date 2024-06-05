import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class AddContact extends Contact{
    private JTextField fNameField;
    private JTextField lNameField;
    private JTextField numberField;
    private JButton saveButton;
    private JButton cancelButton;
    public JPanel addPanel;
    private JRadioButton fam;
    private JRadioButton frn;
    private JRadioButton otr;
    private ButtonGroup bg;
    Contact contact = new Contact();
    private String filePath = "data.txt";

    public String getSelectedRadioButtonText() {
        if (fam.isSelected()) {
            return fam.getText();
        } else if (frn.isSelected()) {
            return frn.getText();
        } else if (otr.isSelected()) {
            return otr.getText();
        }
        return "";
    }
    private String capsFirst(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }
        return input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();
    }
    private boolean contactExists(String fname, String lname) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length >= 2 && data[0].equalsIgnoreCase(fname) && data[1].equalsIgnoreCase(lname)) {
                    return true;
                }
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(addPanel, "Database failed to open", "Try again", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    public AddContact() {

        bg = new ButtonGroup();
        bg.add(fam);
        bg.add(frn);
        bg.add(otr);

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fname, lname, num, textRB;
                fname = fNameField.getText();
                lname = lNameField.getText();
                num = numberField.getText();
                textRB = getSelectedRadioButtonText();

                if (lname.isEmpty()){
                    lname = "";
                }
                if (fname.isEmpty()){
                    JOptionPane.showMessageDialog(addPanel, "First Name cannot be empty!", "Try again", JOptionPane.ERROR_MESSAGE);
                    fNameField.requestFocus();
                    return;
                }
                if (num.isEmpty()){
                    JOptionPane.showMessageDialog(addPanel, "Number cannot be empty!", "Try again", JOptionPane.ERROR_MESSAGE);
                    numberField.requestFocus();
                    return;
                }
                if (textRB.isEmpty()) {
                    JOptionPane.showMessageDialog(addPanel, "Please select a relation!", "Try again", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (contactExists(fname, lname)) {
                    JOptionPane.showMessageDialog(addPanel, "Contact with the same name already exists!", "Try again", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                try {

                    FileWriter myWriter = new FileWriter(filePath, true);
                    myWriter.write(capsFirst(fname) + "," + capsFirst(lname) + "," + num + "," +  textRB + "\n");
                    myWriter.close();
                    JOptionPane.showMessageDialog(addPanel, "Contact has been added.");

                    fNameField.setText("");
                    lNameField.setText("");
                    numberField.setText("");

                } catch (IOException er) {
                    JOptionPane.showMessageDialog(addPanel, "Contact cannot be added", "Try again", JOptionPane.ERROR_MESSAGE);
                }

            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                contact.closeWindow(addPanel);
            }
        });
    }
}
