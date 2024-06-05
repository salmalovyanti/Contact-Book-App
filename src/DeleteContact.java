import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.util.Comparator;

public class DeleteContact extends Contact{
    private JScrollPane scrPanel;
    private JTable tblContact;
    private JButton delButton;
    public JPanel delPanel;
    private JButton backButton;
    private DefaultTableModel tableModel;
    Contact contact = new Contact();
    private String filePath = "data.txt";

    public DeleteContact() {
        tableModel = new DefaultTableModel();
        tableModel.addColumn("First Name");
        tableModel.addColumn("Last Name");
        tableModel.addColumn("Phone");
        tableModel.addColumn("Relation");

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                tableModel.addRow(data);
            }
            tableModel.getDataVector().sort(Comparator.comparing(o -> (String) o.elementAt(0)));
        } catch (IOException e) {
            tableModel.getDataVector().sort(Comparator.comparing(o -> (String) o.elementAt(0)));
            JOptionPane.showMessageDialog(delPanel, "Error loading data", "Try again", JOptionPane.ERROR_MESSAGE);
        }

        tblContact.setModel(tableModel);
        tblContact.setDefaultEditor(Object.class, null);
        scrPanel.setViewportView(tblContact);

        scrPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int selectedRow = tblContact.getSelectedRow();
                if (selectedRow != -1) {
                    String fname = tableModel.getValueAt(selectedRow, 0).toString();
                    String lname = tableModel.getValueAt(selectedRow, 1).toString();
                    String num = tableModel.getValueAt(selectedRow, 2).toString();
                    String relation = tableModel.getValueAt(selectedRow, 3).toString();

                    JOptionPane.showMessageDialog(null,
                            "Selected Contact:\n" +
                                    "First Name: " + fname + "\n" +
                                    "Last Name: " + lname + "\n" +
                                    "Phone: " + num + "\n" +
                                    "Relation: " + relation);
                }
            }
        });
        delButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tblContact.getSelectedRow();
                if (selectedRow != -1) {
                    tableModel.removeRow(selectedRow);
                    try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
                        for (int i = 0; i < tableModel.getRowCount(); i++) {
                            for (int j = 0; j < tableModel.getColumnCount(); j++) {
                                bw.write(tableModel.getValueAt(i, j).toString());
                                if (j < tableModel.getColumnCount() - 1) {
                                    bw.write(",");
                                }
                            }
                            bw.newLine();
                        }
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(delPanel, "Error saving data to file", "Try again", JOptionPane.ERROR_MESSAGE);
                    }
                    JOptionPane.showMessageDialog(null, "Contact has been deleted.");
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a contact to delete.");
                }
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                contact.closeWindow(delPanel);
            }
        });
    }
}
