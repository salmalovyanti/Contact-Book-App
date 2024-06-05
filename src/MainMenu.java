import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu {
    private JPanel mainMenuPanel;
    private JButton editButton;
    private JButton addButton;
    private JButton viewButton;
    private JButton deleteButton;
    private JButton exitButton;
    public MainMenu() {
        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame viewframe = new JFrame("ViewContact");
                viewframe.setContentPane(new ViewContact().viewPanel);
                viewframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                viewframe.setSize(450, 300);
                viewframe.setLocationRelativeTo(null);
                viewframe.setVisible(true);
            }
        });
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame addframe = new JFrame("AddContact");
                addframe.setContentPane(new AddContact().addPanel);
                addframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                addframe.setSize(450,300);
                addframe.setLocationRelativeTo(null);
                addframe.setVisible(true);
            }
        });
        editButton.addActionListener(new ActionListener() {
            @Override

            public void actionPerformed(ActionEvent e) {
                JFrame editframe = new JFrame("EditContact");

                editframe.setContentPane(new EditContact().editPanel);
                editframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                editframe.setSize(450,300);
                editframe.setLocationRelativeTo(null);
                editframe.setVisible(true);
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame delframe = new JFrame("DeleteContact");
                delframe.setContentPane(new DeleteContact().delPanel);
                delframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                delframe.setSize(450, 300);
                delframe.setLocationRelativeTo(null);
                delframe.setVisible(true);
            }
        });
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("MainMenu");
        frame.setContentPane(new MainMenu().mainMenuPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(450, 300);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
