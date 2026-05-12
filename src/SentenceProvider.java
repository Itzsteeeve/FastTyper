import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Arrays;
import Enums.Difficulty;

public class SentenceProvider {
    private ArrayList<String> easy;
    private ArrayList<String> medium;
    private ArrayList<String> hard;

    public SentenceProvider(){
        easy = new ArrayList<>();
        medium = new ArrayList<>();
        hard = new ArrayList<>();

        easy.add("Hello");
        easy.add("World");
        easy.add("Java");
        easy.add("Test");
        easy.add("Jan Karel");

        medium.add("Programming");
        medium.add("Challenge");
        medium.add("Keyboard");
        medium.add("Accuracy");
        medium.add("Perfect");

        hard.add("Extraordinary");
        hard.add("Achievements");
        hard.add("Sophisticated");
        hard.add("Successfully");
        hard.add("Opportunity");
    }

    public List<String> getSentences(Enum<Difficulty> difficulty, int count) {
        List<String> result = new ArrayList<>();
        ArrayList<String> source = null;

        if (difficulty.equals(Difficulty.EASY)) {
            source = easy;
        } else if (difficulty.equals(Difficulty.MEDIUM)) {
            source = medium;
        } else if (difficulty.equals(Difficulty.HARD)) {
            source = hard;
        }

        if (source != null) {
            for (int i = 0; i < count && i < source.size(); i++) {
                result.add(source.get(i));
            }
        }

        return result;
    }
}
