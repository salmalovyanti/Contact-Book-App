import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Comparator;

public class ViewContact extends Contact{
    public JPanel viewPanel;
    private JTable tblContact;
    private JScrollPane scrPanel;
    private JButton backButton;
    Contact contact = new Contact();

    public ViewContact() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("First Name");
        model.addColumn("Last Name");
        model.addColumn("Phone");
        model.addColumn("Relation");

        try (BufferedReader br = new BufferedReader(new FileReader("data.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                model.addRow(data);
            }
            model.getDataVector().sort(Comparator.comparing(o -> (String) o.elementAt(0)));
        } catch (IOException e) {
            model.getDataVector().sort(Comparator.comparing(o -> (String) o.elementAt(0)));
            JOptionPane.showMessageDialog(viewPanel, "No data", "Try again", JOptionPane.ERROR_MESSAGE);
        }

        tblContact.setModel(model);
        tblContact.setDefaultEditor(Object.class, null);
        scrPanel.setViewportView(tblContact);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                contact.closeWindow(viewPanel);
            }
        });
    }
}

