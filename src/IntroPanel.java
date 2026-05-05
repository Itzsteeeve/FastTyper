import Enums.Difficulty;
import Enums.Duration;
import Enums.Language;

import java.awt.*;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class IntroPanel extends JPanel {
    private JButton startButton;
    private JButton settingsButton;

    public IntroPanel() {
        setLayout(new BorderLayout());

        JLabel label = new JLabel("FastTyper", JLabel.CENTER);
        label.setFont(new Font("SansSerif", Font.BOLD, 35));
        add(label, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new GridLayout(0, 1, 10, 10));
        buttonPanel.setBorder(new EmptyBorder(10, 50, 20, 50));
        buttonPanel.setBorder(new EmptyBorder(0,200,300,200));


        startButton = new JButton("Start");
        settingsButton = new JButton("Settings");

        buttonPanel.add(startButton);
        buttonPanel.add(settingsButton);

        add(buttonPanel,BorderLayout.SOUTH);
    }

    public void setStartListener(Runnable listener) {
        startButton.addActionListener(e -> listener.run());
    }

    public TestConfiguration getConfiguration() {
        return new TestConfiguration(Difficulty.EASY, Language.ENGLISH, Duration.SHORT);
    }
}
