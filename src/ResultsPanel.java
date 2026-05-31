import java.awt.*;
import javax.swing.*;

public class ResultsPanel extends JPanel {
    private JButton againButton;
    private JButton menuButton;

    private JLabel lpmValue;
    private JLabel accuracyValue;
    private JLabel correctValue;
    private JLabel halfValue;
    private JLabel wrongValue;
    private JLabel timeValue;
    private JLabel scoreValue;
    private JLabel difficultyValue;

    public ResultsPanel() {
        setLayout(new GridBagLayout());

        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.setOpaque(false);
        JLabel title = new JLabel("Results");
        title.setFont(new Font("SansSerif", Font.BOLD, 36));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        content.add(title);
        content.add(Box.createVerticalStrut(24));

        JPanel stats = new JPanel(new GridLayout(0, 2, 12, 8));
        stats.setOpaque(false);

        lpmValue      = new JLabel("—");
        accuracyValue = new JLabel("—");
        correctValue  = new JLabel("—");
        halfValue     = new JLabel("—");
        wrongValue    = new JLabel("—");
        timeValue     = new JLabel("—");
        scoreValue    = new JLabel("—");
        difficultyValue = new JLabel("—");

        addRow(stats, "Letters / min:", lpmValue);
        addRow(stats, "Accuracy:",      accuracyValue);
        addRow(stats, "Correct:",       correctValue);
        addRow(stats, "Half correct:",  halfValue);
        addRow(stats, "Wrong:",         wrongValue);
        addRow(stats, "Time:",          timeValue);
        addRow(stats, "Score:",         scoreValue);
        addRow(stats, "Difficulty:",    difficultyValue);

        content.add(stats);
        content.add(Box.createVerticalStrut(28));

        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.CENTER, 16, 0));
        buttons.setOpaque(false);

        againButton = new JButton("Again");
        againButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        againButton.setPreferredSize(new Dimension(120, 44));
        menuButton = new JButton("Main Menu");
        menuButton.setFont(new Font("SansSerif", Font.PLAIN, 16));
        menuButton.setPreferredSize(new Dimension(140, 44));

        buttons.add(againButton);
        buttons.add(menuButton);
        content.add(buttons);
        add(content);
    }

    private void addRow(JPanel grid, String label, JLabel value) {
        JLabel lbl = new JLabel(label);
        lbl.setFont(new Font("SansSerif", Font.BOLD, 14));
        lbl.setHorizontalAlignment(SwingConstants.RIGHT);
        value.setFont(new Font("SansSerif", Font.PLAIN, 14));
        grid.add(lbl);
        grid.add(value);
    }

    /** Fills all stat labels with data from the completed test. */
    public void setResult(TestResult result) {
        int min = result.getTime() / 60;
        int sec = result.getTime() % 60;

        lpmValue.setText(String.valueOf(result.getLpm()));
        accuracyValue.setText(String.format("%.1f%%", result.getAccuracy()));
        correctValue.setText(String.valueOf(result.getRightCount()));
        halfValue.setText(String.valueOf(result.getHalfCount()));
        wrongValue.setText(String.valueOf(result.getErrorCount()));
        timeValue.setText(String.format("%02d:%02d", min, sec));
        scoreValue.setText(String.format("%.1f", result.getScore()));
        difficultyValue.setText(result.getDifficulty().toString());
    }

    public void setAgainListener(Runnable listener) {
        againButton.addActionListener(e -> listener.run());
    }

    public void setMenuListener(Runnable listener) {
        menuButton.addActionListener(e -> listener.run());
    }
}
