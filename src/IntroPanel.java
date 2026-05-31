import java.awt.*;
import javax.swing.*;

public class IntroPanel extends JPanel {
    private JButton startButton;
    private JButton settingsButton;

    public IntroPanel() {
        setLayout(new GridBagLayout());

        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.setOpaque(false);

        JLabel title = new JLabel("FastTyper");
        title.setFont(new Font("SansSerif", Font.BOLD, 48));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subtitle = new JLabel("Test your typing speed");
        subtitle.setFont(new Font("SansSerif", Font.PLAIN, 16));
        subtitle.setForeground(Color.GRAY);
        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        startButton = new JButton("Start");
        startButton.setFont(new Font("SansSerif", Font.BOLD, 18));
        startButton.setPreferredSize(new Dimension(220, 50));
        startButton.setMaximumSize(new Dimension(220, 50));
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        settingsButton = new JButton("Settings");
        settingsButton.setFont(new Font("SansSerif", Font.PLAIN, 16));
        settingsButton.setPreferredSize(new Dimension(220, 45));
        settingsButton.setMaximumSize(new Dimension(220, 45));
        settingsButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        content.add(title);
        content.add(Box.createVerticalStrut(8));
        content.add(subtitle);
        content.add(Box.createVerticalStrut(40));
        content.add(startButton);
        content.add(Box.createVerticalStrut(12));
        content.add(settingsButton);

        add(content);
    }

    public void setStartListener(Runnable listener) {
        startButton.addActionListener(e -> listener.run());
    }

    public void setSettingsListener(Runnable listener) {
        settingsButton.addActionListener(e -> listener.run());
    }
}
