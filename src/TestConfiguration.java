import Enums.Difficulty;
import Enums.Duration;

public class TestConfiguration {
    private Enum<Difficulty> difficulty;
    private Enum<Duration> duration;

    public TestConfiguration(Difficulty difficulty, Duration duration) {
        this.difficulty = difficulty;
        this.duration = duration;
    }

    public Enum<Difficulty> getDifficulty() { return difficulty; }
    public void setDifficulty(Enum<Difficulty> difficulty) { this.difficulty = difficulty; }

    public Enum<Duration> getDuration() { return duration; }
    public void setDuration(Enum<Duration> duration) { this.duration = duration; }
}
