import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TestPanel extends JPanel {
    private JLabel sentenceLabel;

    public TestPanel() {
        setLayout(new BorderLayout());

        sentenceLabel = new JLabel("Test screen", JLabel.CENTER);
        sentenceLabel.setFont(new Font("SansSerif", Font.PLAIN, 24));
        add(sentenceLabel, BorderLayout.CENTER);
    }

    public void setSentence(String sentence) {
        sentenceLabel.setText(sentence);
    }

    public void updateCharacterStatus(int index, boolean correct) {
    }

    public void setTimerText(String text) {
    }

    public void setStatsText(String wpm, String accuracy) {
    }

    public void setInputListener(InputListener listener) {
    }

    public String getInputText() {
        return "";
    }

    public void clearInput() {
    }

    public void focusInput() {
    }

    public void reset() {
    }
}
