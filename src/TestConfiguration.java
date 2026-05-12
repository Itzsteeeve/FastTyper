import Enums.Difficulty;
import Enums.Duration;
import Enums.Language;

public class TestConfiguration {
    private Enum<Difficulty> difficulty;
    private Enum<Language> language;
    private Enum<Duration> duration;


    public TestConfiguration(Difficulty easy, Language english, Duration aShort){
        this.difficulty = Difficulty.HARD;
        this.language = Language.ENGLISH;
        this.duration = Duration.SHORT;
    }

    public Enum<Difficulty> getDifficulty() {
        return this.difficulty;
    }

    public void setDifficulty(Enum<Difficulty> difficulty) {
        this.difficulty = difficulty;
    }

    public Enum<Language> getLanguage() {
        return language;
    }

    public void setLanguage(Enum<Language> language) {
        this.language = language;
    }

    public Enum<Duration> getDuration() {
        return duration;
    }

    public void setDuration(Enum<Duration> duration) {
        this.duration = duration;
    }
}
