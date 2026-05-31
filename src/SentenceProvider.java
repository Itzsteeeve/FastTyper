import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import Enums.Difficulty;

public class SentenceProvider {

    private final ArrayList<String> easy   = new ArrayList<>();
    private final ArrayList<String> medium = new ArrayList<>();
    private final ArrayList<String> hard   = new ArrayList<>();

    public SentenceProvider() {
        easy.add("hello");
        easy.add("world");
        easy.add("java");
        easy.add("test");
        easy.add("type");
        easy.add("fast");
        easy.add("code");
        easy.add("run");
        easy.add("play");
        easy.add("win");
        easy.add("open");
        easy.add("close");
        easy.add("save");
        easy.add("load");
        easy.add("start");


        medium.add("programming");
        medium.add("challenge");
        medium.add("keyboard");
        medium.add("accuracy");
        medium.add("perfect");
        medium.add("practice");
        medium.add("software");
        medium.add("developer");
        medium.add("function");
        medium.add("variable");
        medium.add("interface");
        medium.add("compiler");
        medium.add("database");
        medium.add("algorithm");
        medium.add("framework");

        hard.add("extraordinary");
        hard.add("achievements");
        hard.add("sophisticated");
        hard.add("successfully");
        hard.add("opportunity");
        hard.add("circumstances");
        hard.add("perpendicular");
        hard.add("infrastructure");
        hard.add("synchronization");
        hard.add("miscellaneous");
        hard.add("incomprehensible");
        hard.add("conscientious");
        hard.add("unquestionably");
        hard.add("entrepreneurship");
        hard.add("acknowledgement");
    }

    public List<String> getSentences(Enum<Difficulty> difficulty, int count, int setIndex) {
        ArrayList<String> shuffled = new ArrayList<>(getSource(difficulty));
        Collections.shuffle(shuffled);
        return shuffled.subList(0, Math.min(count, shuffled.size()));
    }

    public List<String> getSentences(Enum<Difficulty> difficulty, int count) {
        return getSentences(difficulty, count, 0);
    }

    private ArrayList<String> getSource(Enum<Difficulty> difficulty) {
        if (difficulty.equals(Difficulty.EASY)) {
            return easy;
        }
        if (difficulty.equals(Difficulty.MEDIUM)) {
            return medium;
        }
        return hard;
    }
}
