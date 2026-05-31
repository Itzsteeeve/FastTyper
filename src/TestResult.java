import Enums.Difficulty;

public class TestResult {
    private int lpm;
    private double accuracy;
    private int letterCount;
    private int errorCount;
    private int halfCount;
    private int rightCount;
    private int time;
    private double score;
    private Enum<Difficulty> difficulty;

    public TestResult(double accuracy, int errorCount, int halfCount,
                      int rightCount, int time, int letterCount, Enum<Difficulty> difficulty) {
        this.accuracy = accuracy;
        this.errorCount = errorCount;
        this.halfCount = halfCount;
        this.rightCount = rightCount;
        this.time = time;
        this.letterCount = letterCount;
        if (time > 0) {
            this.lpm = (int) (letterCount * 60.0 / time);
        } else {
            this.lpm = 0;
        }
        this.difficulty = difficulty;
        this.score = calcScore();
    }

    private double difficultyMultiplier() {
        if (difficulty.equals(Difficulty.EASY)) {
            return 0.5;
        }
        if (difficulty.equals(Difficulty.MEDIUM)) {
            return 1.0;
        }
        return 1.5;
    }

    private double calcScore() {
        return difficultyMultiplier() * ((accuracy * rightCount) - halfCount * 0.5 - errorCount - time / 10.0);
    }

    public int getLpm() { return lpm; }
    public double getAccuracy() { return accuracy; }
    public int getLetterCount() { return letterCount; }
    public int getErrorCount() { return errorCount; }
    public int getHalfCount() { return halfCount; }
    public int getRightCount() { return rightCount; }
    public int getTime() { return time; }
    public double getScore() { return score; }
    public Enum<Difficulty> getDifficulty() { return difficulty; }
}
