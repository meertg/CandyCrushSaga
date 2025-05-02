package game10;

import javax.swing.*;

public class CandyCrush {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {
        }

        GameUI gameUI = new GameUI();
        SwingUtilities.invokeLater(gameUI::createAndShowGUI);
    }
}
