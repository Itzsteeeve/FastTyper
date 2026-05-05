import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ResultsPanel extends JPanel {
    private JButton retryButton;
    private JButton homeButton;

    public ResultsPanel() {
        setLayout(new BorderLayout());

        JLabel label = new JLabel("Results screen", JLabel.CENTER);
        label.setFont(new Font("SansSerif", Font.PLAIN, 24));
        add(label, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        retryButton = new JButton("Retry");
        homeButton = new JButton("Home");
        buttonPanel.add(retryButton);
        buttonPanel.add(homeButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    public void setResult(TestResult result) {
    }

    public void setRetryListener(Runnable listener) {
        retryButton.addActionListener(e -> listener.run());
    }

    public void setHomeListener(Runnable listener) {
        homeButton.addActionListener(e -> listener.run());
    }
}
