import Enums.Difficulty;
import Enums.Duration;

import java.awt.*;
import javax.swing.*;

public class SettingsPanel extends JPanel {

    private final JToggleButton[] difficultyBtns;
    private final JToggleButton[] durationBtns;
    private JButton backButton;

    public SettingsPanel() {
        setLayout(new GridBagLayout());

        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.setOpaque(false);

        JLabel title = new JLabel("Settings");
        title.setFont(new Font("SansSerif", Font.BOLD, 36));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        difficultyBtns = new JToggleButton[3];
        durationBtns   = new JToggleButton[3];

        content.add(title);
        content.add(Box.createVerticalStrut(30));
        content.add(buildRow("Difficulty", new String[]{"Easy", "Medium", "Hard"}, difficultyBtns));
        content.add(Box.createVerticalStrut(16));
        content.add(buildRow("Duration",   new String[]{"Short", "Medium", "Long"}, durationBtns));
        content.add(Box.createVerticalStrut(36));

        backButton = new JButton("Back");
        backButton.setFont(new Font("SansSerif", Font.PLAIN, 16));
        backButton.setPreferredSize(new Dimension(160, 42));
        backButton.setMaximumSize(new Dimension(160, 42));
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        content.add(backButton);

        add(content);
    }

    private JPanel buildRow(String labelText, String[] options, JToggleButton[] store) {
        JPanel row = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        row.setOpaque(false);

        JLabel label = new JLabel(labelText + ": ");
        label.setFont(new Font("SansSerif", Font.BOLD, 15));
        label.setPreferredSize(new Dimension(90, 30));
        row.add(label);

        ButtonGroup group = new ButtonGroup();
        for (int i = 0; i < options.length; i++) {
            JToggleButton btn = new JToggleButton(options[i]);
            btn.setFont(new Font("SansSerif", Font.PLAIN, 14));
            btn.setPreferredSize(new Dimension(90, 34));
            btn.setFocusPainted(false);
            group.add(btn);
            row.add(btn);
            store[i] = btn;
            if (i == 0) btn.setSelected(true);
        }

        return row;
    }

    public void setBackListener(Runnable listener) {
        backButton.addActionListener(e -> listener.run());
    }

    /** Reads the currently selected buttons and returns the matching test configuration. */
    public TestConfiguration getConfiguration() {
        Difficulty difficulty = switch (selectedIndex(difficultyBtns)) {
            case 1  -> Difficulty.MEDIUM;
            case 2  -> Difficulty.HARD;
            default -> Difficulty.EASY;
        };
        Duration duration = switch (selectedIndex(durationBtns)) {
            case 1  -> Duration.MEDIUM;
            case 2  -> Duration.LONG;
            default -> Duration.SHORT;
        };
        return new TestConfiguration(difficulty, duration);
    }

    private int selectedIndex(JToggleButton[] btns) {
        for (int i = 0; i < btns.length; i++) {
            if (btns[i].isSelected()) {
                return i;
            }
        }
        return 0;
    }
}
