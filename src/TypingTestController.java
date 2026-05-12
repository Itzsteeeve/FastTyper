import Enums.Correctness;
import Enums.Difficulty;
import java.util.List;

public class TypingTestController implements InputListener {
    private MainWindow window;
    private SentenceProvider provider;
    private TestConfiguration currentConfig;
    private List<String> sentences;
    private String userInput;
    private int currentSentenceIndex = 0;
    private int totalSentences = 5;

    public TypingTestController(MainWindow window, SentenceProvider provider) {
        this.window = window;
        this.provider = provider;
        this.userInput = "";
    }

    public void start() {
        window.getIntroPanel().setStartListener(() -> handleStart(window.getIntroPanel().getConfiguration()));
        window.getResultsPanel().setRetryListener(this::handleRetry);
        window.getResultsPanel().setHomeListener(this::handleHome);
        window.showIntro();
        window.setVisible(true);
    }

    public void handleStart(TestConfiguration config) {
        this.currentConfig = config;
        this.sentences = provider.getSentences(config.getDifficulty(), totalSentences);
        this.userInput = "";
        this.currentSentenceIndex = 0;

        System.out.println("Starting test with " + sentences.size() + " sentences");
        for (int i = 0; i < sentences.size(); i++) {
            System.out.println("Sentence " + (i+1) + ": " + sentences.get(i));
        }

        TestPanel testPanel = window.getTestPanel();
        testPanel.setSentences(sentences);
        testPanel.setInputListener(this);
        testPanel.clearInput();
        testPanel.focusInput();

        window.showTest();
    }

    @Override
    public void onInputChanged(String input) {
        this.userInput = input;

        if (currentSentenceIndex >= sentences.size()) {
            return;
        }

        String currentSentence = sentences.get(currentSentenceIndex);
        TestPanel testPanel = window.getTestPanel();

        if (input.length() > currentSentence.length()) {
            testPanel.clearInput();
            return;
        }

        Enum<Difficulty> difficulty = currentConfig.getDifficulty();

        for (int i = 0; i < input.length() && i < currentSentence.length(); i++) {
            char userChar = input.charAt(i);
            char sentenceChar = currentSentence.charAt(i);

            Enum<Correctness> correctness = checkCharacter(userChar, sentenceChar, difficulty);
            testPanel.updateCharacterStatus(currentSentenceIndex, i, correctness);
        }
    }

    @Override
    public void onEnterPressed() {
        System.out.println("Enter pressed! Moving to next sentence...");

        if (currentSentenceIndex >= sentences.size()) {
            return;
        }

        TestPanel testPanel = window.getTestPanel();
        testPanel.markSentenceCompleted(currentSentenceIndex);

        currentSentenceIndex++;
        testPanel.clearInput();

        System.out.println("Now on sentence " + (currentSentenceIndex ) + " of " + sentences.size());

        if (currentSentenceIndex >= sentences.size()) {
            System.out.println("All sentences completed! Test finished.");
            finishTest();
        } else {
            testPanel.focusInput();
        }
    }

    private Enum<Correctness> checkCharacter(char userChar, char sentenceChar, Enum<Difficulty> difficulty) {

        if (userChar == sentenceChar) {
            return Correctness.RIGHT;
        } else if (String.valueOf(userChar).toUpperCase().equals(String.valueOf(sentenceChar))||
        String.valueOf(userChar).toLowerCase().equals(String.valueOf(sentenceChar))) {
            return Correctness.HALF;
        } else {
            return Correctness.WRONG;
        }
    }


    // TODO implement timer
    public void timerStart(){};

    public void timerEnd(){};

    public void handleRetry() {
        window.showIntro();
    }

    public void handleHome() {
        window.showIntro();
    }

    private void finishTest() {
        window.showResults();
    }


    private int getRemainingTime() {
        return 0;
    }
}
