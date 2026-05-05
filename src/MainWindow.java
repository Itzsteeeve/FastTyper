import java.awt.CardLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainWindow extends JFrame {
    private CardLayout cardLayout;
    private JPanel contentPanel;
    private IntroPanel introPanel;
    private TestPanel testPanel;
    private ResultsPanel resultsPanel;

    public MainWindow() {
        setTitle("FastTyper");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setResizable(false);

        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);

        introPanel = new IntroPanel();
        testPanel = new TestPanel();
        resultsPanel = new ResultsPanel();

        contentPanel.add(introPanel, "intro");
        contentPanel.add(testPanel, "test");
        contentPanel.add(resultsPanel, "results");

        add(contentPanel);
    }

    public void showIntro() {
        cardLayout.show(contentPanel, "intro");
    }

    public void showTest() {
        cardLayout.show(contentPanel, "test");
    }

    public void showResults() {
        cardLayout.show(contentPanel, "results");
    }

    public IntroPanel getIntroPanel() {
        return introPanel;
    }

    public TestPanel getTestPanel() {
        return testPanel;
    }

    public ResultsPanel getResultsPanel() {
        return resultsPanel;
    }
}
