import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
public class EditContact extends Contact{
    private JTextField insNameField;
    private JTextField fNameField;
    private JTextField lNameField;
    private JTextField numberField;
    private JRadioButton fam;
    private JRadioButton frn;
    private JRadioButton otr;
    private JButton cancelButton;
    private JButton saveButton;
    private JButton searchButton;
    public JPanel editPanel;
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

    public EditContact() {
        bg = new ButtonGroup();
        bg.add(fam);
        bg.add(frn);
        bg.add(otr);
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name, line, srcName;
                name = capsFirst(insNameField.getText());
                try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
                    while ((line = br.readLine()) != null) {
                        String[] data = line.split(",");
                        srcName = data[0];

                        if (srcName.equals(name)) {
                            fNameField.setText(srcName);
                            lNameField.setText(data[1]);
                            numberField.setText(data[2]);

                            if (data[3].trim().equalsIgnoreCase("Family")) {
                                fam.setSelected(true);
                            } else if (data[3].trim().equalsIgnoreCase("Friend")) {
                                frn.setSelected(true);
                            } else if (data[3].trim().equalsIgnoreCase("Other")) {
                                otr.setSelected(true);
                            }
                            return;
                        }
                    }
                    JOptionPane.showMessageDialog(editPanel, "Contact not found", "Try again", JOptionPane.ERROR_MESSAGE);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(editPanel, "Error loading data", "Try again", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name, fname, lname, num, textRB;
                name = insNameField.getText();
                fname = fNameField.getText();
                lname = lNameField.getText();
                num = numberField.getText();
                textRB = getSelectedRadioButtonText();
                try {
                    BufferedReader br = new BufferedReader(new FileReader(filePath));
                    ArrayList<String> lines = new ArrayList<>();
                    String line;

                    while ((line = br.readLine()) != null) {
                        lines.add(line);
                    }
                    br.close();

                    for (int i = 0; i < lines.size(); i++) {
                        String[] data = lines.get(i).split(",");
                        String srcName = data[0];

                        if (name.equalsIgnoreCase(srcName)) {
                            lines.set(i, capsFirst(fname) + "," + capsFirst(lname) + "," + num + "," + textRB);
                            break;
                        }
                    }

                    BufferedWriter bw = new BufferedWriter(new FileWriter(filePath));
                    for (String updatedLine : lines) {
                        bw.write(updatedLine);
                        bw.newLine();
                    }
                    bw.close();
                    if (name.isEmpty()){
                        JOptionPane.showMessageDialog(editPanel, "No searched data", "Try again", JOptionPane.ERROR_MESSAGE);
                        insNameField.requestFocus();
                    }
                    else {
                        JOptionPane.showMessageDialog(editPanel, "Contact has been updated.");
                    }

                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(editPanel, "Error saving data", "Try again", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                contact.closeWindow(editPanel);
            }
        });
    }
}
