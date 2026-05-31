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

    public void setStatus(Enum<Correctness> status){
        if (status.equals(Correctness.RIGHT)) {
            setBackground(Color.GREEN);
        } else if (status.equals(Correctness.HALF)) {
            setBackground(Color.YELLOW);
        } else if (status.equals(Correctness.WRONG)) {
            setBackground(Color.RED);
        } else {
            setBackground(Color.WHITE);
        }
    }
}
