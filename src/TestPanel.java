import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import Enums.Correctness;

public class TestPanel extends JPanel {
    private JPanel sentencesPanel;
    private JTextField inputField;
    private JLabel timerLabel;
    private JLabel statsLabel;
    private List<JPanel> sentenceContainers;
    private List<List<LetterContainer>> allLetterContainers;
    private InputListener inputListener;
    private int completedSentences = 0;

    public TestPanel() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JScrollPane scrollPane = new JScrollPane();
        sentencesPanel = new JPanel();
        sentencesPanel.setLayout(new BoxLayout(sentencesPanel, BoxLayout.Y_AXIS));
        sentencesPanel.setBackground(Color.WHITE);
        scrollPane.setViewportView(sentencesPanel);
        add(scrollPane, BorderLayout.CENTER);

        allLetterContainers = new ArrayList<>();
        sentenceContainers = new ArrayList<>();

        JPanel topPanel = new JPanel(new BorderLayout());
        timerLabel = new JLabel("Time: 00:00");
        timerLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        statsLabel = new JLabel("WPM: 0 | Accuracy: 100%");
        statsLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        topPanel.add(timerLabel, BorderLayout.WEST);
        topPanel.add(statsLabel, BorderLayout.EAST);
        add(topPanel, BorderLayout.NORTH);


        inputField = new JTextField();
        inputField.setFont(new Font("SansSerif", Font.PLAIN, 16));
        inputField.setPreferredSize(new java.awt.Dimension(400, 40));
        inputField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    e.consume();
                    if (inputListener != null) {
                        inputListener.onEnterPressed();
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (inputListener != null) {
                    inputListener.onInputChanged(inputField.getText());
                }
            }
        });
        inputField.setFocusable(true);
        add(inputField, BorderLayout.SOUTH);
    }

    public void updateTimerDisplay(String time) {
        timerLabel.setText("Time: " + time);
    }

    public void updateStats(int wpm, double accuracy, int currentSet, int totalSets) {
        String setInfo;
        if (totalSets > 1) {
            setInfo = String.format("%d/%d  |  ", currentSet, totalSets);
        } else {
            setInfo = "";
        }
        statsLabel.setText(String.format("%sWPM: %d  |  Accuracy: %.0f%%", setInfo, wpm, accuracy));
    }

    /** Clears the panel and renders a new list of words as letter boxes ready for colour feedback. */
    public void setSentences(List<String> sentences) {
        System.out.println("Setting " + sentences.size() + " sentences in TestPanel");
        sentencesPanel.removeAll();
        allLetterContainers.clear();
        sentenceContainers.clear();
        completedSentences = 0;

        for (String sentence : sentences) {
            System.out.println("Creating sentence container for: " + sentence);
            JPanel sentencePane = new JPanel();
            sentencePane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
            sentencePane.setBackground(Color.WHITE);
            sentencePane.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
            sentencePane.setMaximumSize(new java.awt.Dimension(Integer.MAX_VALUE, 100));
            List<LetterContainer> letterContainers = new ArrayList<>();

            for (char c : sentence.toCharArray()) {
                LetterContainer container = new LetterContainer(String.valueOf(c));
                letterContainers.add(container);
                sentencePane.add(container);
            }

            sentencesPanel.add(sentencePane);
            sentencesPanel.add(Box.createVerticalStrut(15));

            allLetterContainers.add(letterContainers);
            sentenceContainers.add(sentencePane);
        }

        sentencesPanel.revalidate();
        sentencesPanel.repaint();
        System.out.println("TestPanel refreshed");
    }

    public void updateCharacterStatus(int sentenceIndex, int charIndex, Enum<Correctness> correctness) {
        if (sentenceIndex >= 0 && sentenceIndex < allLetterContainers.size()) {
            List<LetterContainer> sentenceLetters = allLetterContainers.get(sentenceIndex);
            if (charIndex >= 0 && charIndex < sentenceLetters.size()) {
                sentenceLetters.get(charIndex).setStatus(correctness);
            }
        }
    }

    public void markSentenceCompleted(int sentenceIndex) {
        completedSentences++;
        if (sentenceIndex < sentenceContainers.size()) {
            sentenceContainers.get(sentenceIndex).setBorder(
                    BorderFactory.createLineBorder(Color.black, 2)
            );
        }
    }


    public void setInputListener(InputListener listener) {
        this.inputListener = listener;
    }

    public String getInputText() {
        return inputField.getText();
    }

    public void clearInput() {
        inputField.setText("");
    }

    public void focusInput() {
        inputField.requestFocus();
    }

}
