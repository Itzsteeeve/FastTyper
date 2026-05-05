import java.util.Map;
import java.util.HashMap;

public class TypingStatistics {
    private int correctChars;
    private int incorrectChars;
    private int totalChars;
    private Map<String, Integer> mistakes;

    public TypingStatistics() {
        this.mistakes = new HashMap<>();
    }

    public void registerCharacter(boolean correct) {
        totalChars++;
        if (correct) {
            correctChars++;
        } else {
            incorrectChars++;
        }
    }

    public int getWpm(int elapsedSeconds) {
        if (elapsedSeconds == 0) return 0;
        return (int) ((correctChars / 5.0) * (60.0 / elapsedSeconds));
    }

    public double getAccuracy() {
        if (totalChars == 0) return 100.0;
        return (correctChars * 100.0) / totalChars;
    }

    public int getErrorCount() {
        return incorrectChars;
    }

    public int getTypedCharacters() {
        return totalChars;
    }

    public Map<String, Integer> getCommonMistakes() {
        return mistakes;
    }
}
