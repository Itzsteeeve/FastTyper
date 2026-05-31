import Enums.Correctness;
import Enums.Difficulty;
import Enums.Duration;
import javax.swing.Timer;
import java.util.List;

public class TypingTestController implements InputListener {
    private MainWindow window;
    private SentenceProvider provider;
    private TestConfiguration currentConfig;
    private List<String> sentences;
    private int currentSentenceIndex = 0;
    private int totalSets;
    private int currentSet = 1;
    private static final int WORDS_PER_SET = 5;
    private Timer swingTimer;
    private int elapsedSeconds = 0;
    private boolean timerStarted = false;
    private int completedCorrectChars = 0;
    private int completedHalfChars = 0;
    private int completedTotalChars = 0;

    public TypingTestController(MainWindow window, SentenceProvider provider) {
        this.window = window;
        this.provider = provider;
    }

    /** Registers all button listeners and shows the main menu. Called once on startup. */
    public void start() {
        window.getIntroPanel().setStartListener(() -> handleStart(window.getSettingsPanel().getConfiguration()));
        window.getIntroPanel().setSettingsListener(window::showSettings);
        window.getSettingsPanel().setBackListener(window::showIntro);
        window.getResultsPanel().setAgainListener(this::handleRetry);
        window.getResultsPanel().setMenuListener(this::handleHome);
        window.showIntro();
    }

    /** Resets all state and starts a new test with the given configuration. */
    public void handleStart(TestConfiguration config) {
        this.currentConfig = config;
        this.currentSentenceIndex = 0;
        this.currentSet = 1;
        this.totalSets = setsForDuration(config.getDuration());
        this.timerStarted = false;
        this.elapsedSeconds = 0;
        this.completedCorrectChars = 0;
        this.completedHalfChars = 0;
        this.completedTotalChars = 0;

        sentences = provider.getSentences(config.getDifficulty(), WORDS_PER_SET, 0);

        TestPanel testPanel = window.getTestPanel();
        testPanel.setSentences(sentences);
        testPanel.setInputListener(this);
        testPanel.clearInput();
        testPanel.updateTimerDisplay("00:00");
        testPanel.updateStats(0, 100.0, currentSet, totalSets);
        testPanel.focusInput();

        window.showTest();
    }

    /**
     * Called on every keystroke. Starts the timer on the first character,
     * colours each letter and auto-advances to the next word when it is fully typed.
     */
    @Override
    public void onInputChanged(String input) {
        if (!timerStarted && !input.isEmpty()) {
            timerStarted = true;
            timerStart();
        }

        if (currentSentenceIndex >= sentences.size()) {
            return;
        }

        String currentSentence = sentences.get(currentSentenceIndex);
        TestPanel testPanel = window.getTestPanel();

        if (input.length() > currentSentence.length()) {
            testPanel.clearInput();
            return;
        }

        for (int i = 0; i < input.length(); i++) {
            testPanel.updateCharacterStatus(currentSentenceIndex, i,
                    checkCharacter(input.charAt(i), currentSentence.charAt(i)));
        }

        updateLiveStats(input, currentSentence);

        if (input.length() == currentSentence.length()) {
            advanceSentence();
        }
    }

    @Override
    public void onEnterPressed() {
        advanceSentence();
    }

    /**
     * Records stats for the completed word, marks it as done and moves to the next one.
     * After the last word of a set, loads the next set or finishes the test.
     */
    private void advanceSentence() {
        if (currentSentenceIndex >= sentences.size()) {
            return;
        }

        recordSentenceStats(sentences.get(currentSentenceIndex));

        TestPanel testPanel = window.getTestPanel();
        testPanel.markSentenceCompleted(currentSentenceIndex);
        currentSentenceIndex++;
        testPanel.clearInput();

        if (currentSentenceIndex >= sentences.size()) {
            if (currentSet < totalSets) {
                currentSet++;
                sentences = provider.getSentences(currentConfig.getDifficulty(), WORDS_PER_SET, currentSet - 1);
                currentSentenceIndex = 0;
                testPanel.setSentences(sentences);
                testPanel.updateStats(computeWpm(), computeAccuracy(), currentSet, totalSets);
                testPanel.focusInput();
            } else {
                finishTest();
            }
        } else {
            testPanel.focusInput();
        }
    }

    /** Adds the correct, half-correct and total character counts of the completed word to the running totals. */
    private void recordSentenceStats(String sentence) {
        String typed = window.getTestPanel().getInputText();
        int len = Math.min(typed.length(), sentence.length());
        for (int i = 0; i < len; i++) {
            completedTotalChars++;
            char u = typed.charAt(i), s = sentence.charAt(i);
            if (u == s) {
                completedCorrectChars++;
            } else if (Character.toLowerCase(u) == Character.toLowerCase(s)) {
                completedHalfChars++;
            }
        }
    }

    /** Recomputes WPM and accuracy from completed words plus the current in-progress word and updates the panel. */
    private void updateLiveStats(String input, String currentSentence) {
        if (elapsedSeconds == 0) {
            return;
        }
        int liveCorrect = completedCorrectChars;
        int liveTotal = completedTotalChars;
        for (int i = 0; i < input.length() && i < currentSentence.length(); i++) {
            liveTotal++;
            if (input.charAt(i) == currentSentence.charAt(i)) {
                liveCorrect++;
            }
        }
        int wpm = (int) ((liveCorrect / 5.0) * (60.0 / elapsedSeconds));
        double accuracy;
        if (liveTotal == 0) {
            accuracy = 100.0;
        } else {
            accuracy = (liveCorrect * 100.0) / liveTotal;
        }
        window.getTestPanel().updateStats(wpm, accuracy, currentSet, totalSets);
    }

    private int computeWpm() {
        if (elapsedSeconds == 0) {
            return 0;
        }
        return (int) ((completedCorrectChars / 5.0) * (60.0 / elapsedSeconds));
    }

    private double computeAccuracy() {
        if (completedTotalChars == 0) {
            return 100.0;
        }
        return (completedCorrectChars * 100.0) / completedTotalChars;
    }

    private Enum<Correctness> checkCharacter(char userChar, char sentenceChar) {
        if (userChar == sentenceChar) {
            return Correctness.RIGHT;
        }
        if (Character.toLowerCase(userChar) == Character.toLowerCase(sentenceChar)) {
            return Correctness.HALF;
        }
        return Correctness.WRONG;
    }

    private int setsForDuration(Enum<Duration> duration) {
        if (duration.equals(Duration.MEDIUM)) {
            return 2;
        }
        if (duration.equals(Duration.LONG)) {
            return 3;
        }
        return 1;
    }

    public void timerStart() {
        swingTimer = new Timer(1000, e -> {
            elapsedSeconds++;
            int min = elapsedSeconds / 60;
            int sec = elapsedSeconds % 60;
            window.getTestPanel().updateTimerDisplay(String.format("%02d:%02d", min, sec));
        });
        swingTimer.start();
    }

    public void timerEnd() {
        if (swingTimer != null) {
            swingTimer.stop();
        }
    }

    public void handleRetry() {
        timerEnd();
        window.showIntro();
    }

    public void handleHome() {
        timerEnd();
        window.showIntro();
    }

    /** Stops the timer, builds the test result and navigates to the results screen. */
    private void finishTest() {
        timerEnd();
        int wrong = completedTotalChars - completedCorrectChars - completedHalfChars;
        double accuracy;
        if (completedTotalChars == 0) {
            accuracy = 100.0;
        } else {
            accuracy = (completedCorrectChars * 100.0) / completedTotalChars;
        }
        TestResult result = new TestResult(
                accuracy, wrong, completedHalfChars,
                completedCorrectChars, elapsedSeconds,
                completedTotalChars, currentConfig.getDifficulty());
        window.getResultsPanel().setResult(result);
        window.showResults();
    }
}
