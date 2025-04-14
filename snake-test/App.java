import javax.swing.*;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Snake");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(600, 600);
            frame.setLocationRelativeTo(null);
            frame.setResizable(false);

            MainMenu menu = new MainMenu(frame);
            frame.setContentPane(menu);
            frame.setVisible(true);
        });
    }
}
