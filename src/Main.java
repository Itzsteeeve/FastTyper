import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainWindow window = new MainWindow();
            SentenceProvider provider = new SentenceProvider();
            TypingTestController controller = new TypingTestController(window, provider);
            controller.start();
        });
    }
}
