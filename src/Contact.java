import javax.swing.*;

public class Contact {
    public void closeWindow(JPanel panel){
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(panel);
        frame.dispose();
        new MainMenu();
    }
}
