import javax.swing.*;
import java.awt.*;
import java.io.File;

public class MainMenu extends JPanel {
	Font pixelFont;
    public MainMenu(JFrame frame) {
        setLayout(new BorderLayout());
	setBackground(new Color(30, 30, 60));

	try {
    		pixelFont = Font.createFont(Font.TRUETYPE_FONT, new File("assets/PressStart2P-Regular.ttf"))
                .deriveFont(Font.PLAIN, 18f);
    		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
    		ge.registerFont(pixelFont);
	} catch (Exception e) {
    		e.printStackTrace();
    		pixelFont = new Font("Monospaced", Font.PLAIN, 18); // fallback
	}

        // === Title Label ===
        JLabel title = new JLabel("Snake Game", SwingConstants.CENTER);
	title.setFont(pixelFont);
	title.setForeground(Color.WHITE);
        title.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add(title, BorderLayout.NORTH);

        // === Buttons Panel ===
        JPanel buttonsPanel = new JPanel(new GridLayout(3, 1, 10, 10));
	buttonsPanel.setOpaque(false);
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(30, 60, 60, 60));

        JButton startButton = new JButton("Start Game");
        JButton optionsButton = new JButton("Options");
        JButton exitButton = new JButton("Exit");

	// Optional: Match button style with dark theme
        JButton[] buttons = { startButton, optionsButton, exitButton };
        for (JButton btn : buttons) {
            btn.setBackground(new Color(60, 60, 90));
            btn.setForeground(Color.WHITE);
	    btn.setFont(pixelFont);
            btn.setFocusPainted(false);
        }

        // === Button Actions ===
        startButton.addActionListener(e -> {
            SnakeGame game = new SnakeGame(frame.getWidth(), frame.getHeight(), GameSettings.gameSpeed);
            frame.setContentPane(game);
            frame.revalidate();
            game.requestFocusInWindow();
        });

        optionsButton.addActionListener(e -> {

	    Color bgColor = new Color(30, 30, 60);
	    Color fgColor = Color.WHITE;

            String input = JOptionPane.showInputDialog(this, "Enter game speed \n (Lower Value = Faster Speed):", GameSettings.gameSpeed);
            if (input != null) {
                try {
                    int newSpeed = Integer.parseInt(input);
                    if (newSpeed >= 10 && newSpeed <= 1000) {
                        GameSettings.gameSpeed = newSpeed;
                    } else {
                        JOptionPane.showMessageDialog(this, "Speed must be between 10 and 1000 ms.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "bruh");
                }
            }
        });

        exitButton.addActionListener(e -> System.exit(0));

        buttonsPanel.add(startButton);
        buttonsPanel.add(optionsButton);
        buttonsPanel.add(exitButton);

        add(buttonsPanel, BorderLayout.CENTER);
    }
}
