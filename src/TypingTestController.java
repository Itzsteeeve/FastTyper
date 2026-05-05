public class TypingTestController {
    private MainWindow window;
    private SentenceProvider provider;

    public TypingTestController(MainWindow window, SentenceProvider provider) {
        this.window = window;
        this.provider = provider;
    }

    public void start() {
        window.getIntroPanel().setStartListener(() -> handleStart(window.getIntroPanel().getConfiguration()));
        window.getResultsPanel().setRetryListener(this::handleRetry);
        window.getResultsPanel().setHomeListener(this::handleHome);
        window.showIntro();
        window.setVisible(true);
    }

    public void handleStart(TestConfiguration config) {
        window.showTest();
    }

    public void handleInput(String input) {
    }

    public void handleRetry() {
        window.showIntro();
    }

    public void handleHome() {
        window.showIntro();
    }

    private void finishTest() {
        window.showResults();
    }

    private String getCurrentSentence() {
        return "";
    }

    private int getRemainingTime() {
        return 0;
    }
}
