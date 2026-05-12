public class TestResult {
    private int lpm;
    private double accuracy;
    private int letterCount;
    private int errorCount;
    private int halfCount;
    private int rightCount;
    private int time;

    public TestResult(int wpm, double accuracy, int errorCount,
                      int halfCount, int rightCount, int time, int letterCount) {
        this.accuracy = accuracy;
        this.errorCount = errorCount;
        this.halfCount = halfCount;
        this.rightCount = rightCount;
        this.time = time;
        this.letterCount = letterCount;
        this.lpm = letterCount/60;
    }

    public int getLpm() {
        return lpm;
    }

    public double getAccuracy() {
        return accuracy;
    }

    public int getErrorCount() {
        return errorCount;
    }

}
