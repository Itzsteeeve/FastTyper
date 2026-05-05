import Enums.Correctness;

import javax.swing.*;
import java.awt.*;

public class LetterContainer extends JPanel {
    private String letter;
    private Enum<Correctness> correctness;
    private JLabel label;

    public LetterContainer(String letter) {
        this.letter = letter;
        this.correctness = Correctness.NONE;

        Dimension size = new Dimension(50,50);
        setPreferredSize(size);
        setMaximumSize(size);
        setMinimumSize(size);
        setLayout(new GridBagLayout());
        label = new JLabel(letter);
        label.setFont(new Font("SansSerif", Font.BOLD, 22));
        add(label);

        setBackground(Color.WHITE);
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

    }

    public void setColor(Color bg) {
        setBackground(bg);
    }

    public Enum<Correctness> correctLetter(String toCorrect){
        if (toCorrect.equals(letter)){
            correctness = Correctness.RIGHT;
            return correctness;
        } else if (toCorrect.toUpperCase().equals(letter)) {
            correctness = Correctness.HALF;
            return correctness;
        }else {
            return Correctness.WRONG;
        }
    }

    public void setStatus(Enum<Correctness> status){
        switch (status){
            case Correctness.RIGHT:
                setColor(Color.GREEN);
                break;
            case Correctness.HALF:
                setColor(Color.YELLOW);
                break;
            case Correctness.WRONG:
                setColor(Color.RED);
                break;
            default:
                setColor(Color.WHITE);
        }
    }
}
