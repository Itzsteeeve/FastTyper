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

    public TestResult(int wpm, double accuracy, int errorCount,
                      int halfCount, int rightCount, int time, int letterCount,Enum<Difficulty> difficulty) {
        this.accuracy = accuracy;
        this.errorCount = errorCount;
        this.halfCount = halfCount;
        this.rightCount = rightCount;
        this.time = time;
        this.letterCount = letterCount;
        this.lpm = letterCount/60;
        this.difficulty = difficulty;
        this.score = calcScore();
    }

    private double getIntDif(Enum<Difficulty> difficulty){
        if (difficulty.equals(Difficulty.EASY)){
            return 0.5;
        } else if (difficulty.equals(Difficulty.MEDIUM)) {
            return 1;
        } else {
            return 1.5;
        }
    }

    private double calcScore(){
        return (double) getIntDif(difficulty)*((accuracy*rightCount)-halfCount*0.5-errorCount-time/10);
    }

    //region getters

    public int getLpm() {
        return lpm;
    }

    public double getAccuracy() {
        return accuracy;
    }

    public int getLetterCount() {
        return letterCount;
    }

    public int getErrorCount() {
        return errorCount;
    }

    public int getHalfCount() {
        return halfCount;
    }

    public int getRightCount() {
        return rightCount;
    }

    public int getTime() {
        return time;
    }

    //endregion
}
