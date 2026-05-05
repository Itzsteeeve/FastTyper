import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class IntroPanel extends JPanel {
    private JButton startButton;

    public IntroPanel() {
        setLayout(new BorderLayout());

        JLabel label = new JLabel("Keyboard Speed Test", JLabel.CENTER);
        label.setFont(new Font("SansSerif", Font.BOLD, 32));
        add(label, BorderLayout.CENTER);

        startButton = new JButton("Start");
        add(startButton, BorderLayout.SOUTH);
    }

    public void setStartListener(Runnable listener) {
        startButton.addActionListener(e -> listener.run());
    }

    public TestConfiguration getConfiguration() {
        return new TestConfiguration("easy", "english", 60);
    }
}
