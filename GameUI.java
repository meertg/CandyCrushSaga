package game10;

import javax.swing.*;
import java.awt.*;

public class GameUI {
    private final int MAX_LEVEL = 5;
    private int gridSize = 6;
    private int level = 1;
    private GameBoard gameBoard;
    private JLabel scoreLabel;
    public int skor;
    private JFrame frame;

    public void createAndShowGUI() {
        SwingUtilities.invokeLater(() -> {
            frame = new JFrame();
            frame.setTitle("Candy Crush");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new BorderLayout());

            skor = 0;
            scoreLabel = new JLabel("Skor: " + skor + " | Seviye: " + level, JLabel.CENTER);

            gameBoard = new GameBoard(gridSize, this);
            frame.add(scoreLabel, BorderLayout.NORTH);
            frame.add(gameBoard.getBoardPanel(), BorderLayout.CENTER);

            frame.setSize(600, 650);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }

    public void addScore(int points) {
        skor += points;

        SwingUtilities.invokeLater(() -> {
            scoreLabel.setText("Skor: " + skor + " | Seviye: " + level);

            if (skor >= level * 500) {
                levelUp();
            }
        });
    }

    private void levelUp() {
        if (level == MAX_LEVEL) {
            endGame();
            return;
        }

        level++;
        gridSize++;
        CandyLogic.increaseCandyTypes(level);

        SwingUtilities.invokeLater(() -> {
            JOptionPane.showMessageDialog(frame, "Tebrikler, sonraki levele geç!", "Tebrikler", JOptionPane.PLAIN_MESSAGE);

            frame.remove(gameBoard.getBoardPanel());
            gameBoard = new GameBoard(gridSize, this);
            frame.add(gameBoard.getBoardPanel(), BorderLayout.CENTER);

            scoreLabel.setText("Skor: " + skor + " | Seviye: " + level);
            frame.revalidate();
            frame.repaint();
        });
    }

    private void endGame() {
        SwingUtilities.invokeLater(() -> {
            JOptionPane.showMessageDialog(frame, "Oyun Bitti! Son skorunuz: " + skor, "Oyun Bitti", JOptionPane.PLAIN_MESSAGE);
            System.exit(0); 
        });
    }

}
