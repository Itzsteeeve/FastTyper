import javax.swing.JFrame;

public class MainWindow {
    private JFrame menuFrame;
    private JFrame testFrame;
    private JFrame resultsFrame;
    private JFrame settingsFrame;

    private IntroPanel introPanel;
    private TestPanel testPanel;
    private ResultsPanel resultsPanel;
    private SettingsPanel settingsPanel;

    public MainWindow() {
        introPanel = new IntroPanel();
        testPanel = new TestPanel();
        resultsPanel = new ResultsPanel();
        settingsPanel = new SettingsPanel();

        menuFrame = buildFrame("FastTyper – Menu", 500, 400);
        menuFrame.add(introPanel);
        menuFrame.setSize(500, 400);

        testFrame = buildFrame("FastTyper – Typing Test", 900, 600);
        testFrame.add(testPanel);
        testFrame.setSize(900, 600);

        resultsFrame = buildFrame("FastTyper – Results", 500, 400);
        resultsFrame.add(resultsPanel);
        resultsFrame.setSize(500, 400);

        settingsFrame = buildFrame("FastTyper – Settings", 500, 400);
        settingsFrame.add(settingsPanel);
        settingsFrame.setSize(500, 400);
    }

    private JFrame buildFrame(String title, int w, int h) {
        JFrame frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(w, h);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        return frame;
    }

    public void showIntro() {
        testFrame.setVisible(false);
        resultsFrame.setVisible(false);
        settingsFrame.setVisible(false);
        menuFrame.setLocationRelativeTo(null);
        menuFrame.setVisible(true);
    }

    public void showTest() {
        menuFrame.setVisible(false);
        resultsFrame.setVisible(false);
        settingsFrame.setVisible(false);
        testFrame.setLocationRelativeTo(null);
        testFrame.setVisible(true);
    }

    public void showResults() {
        menuFrame.setVisible(false);
        testFrame.setVisible(false);
        settingsFrame.setVisible(false);
        resultsFrame.setLocationRelativeTo(null);
        resultsFrame.setVisible(true);
    }

    public void showSettings() {
        menuFrame.setVisible(false);
        settingsFrame.setLocationRelativeTo(null);
        settingsFrame.setVisible(true);
    }

    public IntroPanel getIntroPanel() { return introPanel; }
    public TestPanel getTestPanel() { return testPanel; }
    public ResultsPanel getResultsPanel() { return resultsPanel; }
    public SettingsPanel getSettingsPanel() { return settingsPanel; }
}
