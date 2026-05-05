import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        MainWindow window = new MainWindow();
        SentenceProvider provider = new SentenceProvider();
        TypingTestController controller = new TypingTestController(window, provider);
        controller.start();
    }
}
