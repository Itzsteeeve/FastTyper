import java.util.Map;

public class TestResult {
    private int wpm;
    private double accuracy;
    private int errorCount;
    private Map<String, Integer> commonMistakes;

    public TestResult(int wpm, double accuracy, int errorCount, Map<String, Integer> commonMistakes) {
        this.wpm = wpm;
        this.accuracy = accuracy;
        this.errorCount = errorCount;
        this.commonMistakes = commonMistakes;
    }

    public int getWpm() {
        return wpm;
    }

    public double getAccuracy() {
        return accuracy;
    }

    public int getErrorCount() {
        return errorCount;
    }

    public Map<String, Integer> getCommonMistakes() {
        return commonMistakes;
    }
}
