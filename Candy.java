package game10;

import javax.swing.*;
import java.awt.*;

public class Candy extends BaseCandy {
    private JButton sekerButonu;

    public Candy() {
        super(CandyLogic.randomCandy());
        sekerButonu = new JButton(getType());
        setCandyFont(70);
    }
    
    public void setCandyFont(int slotSize) {
        int fontSize = (int) (slotSize * 0.3);
        Font emojiFont = new Font("Segoe UI Emoji", Font.BOLD, fontSize);
        sekerButonu.setFont(emojiFont);
    }

    public JButton getSekerButonu() {
        return sekerButonu;
    }

    public void updateButton() {
        sekerButonu.setText(getType() == null ? "" : getType());
    }
}
