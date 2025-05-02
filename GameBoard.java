package game10;

import javax.swing.*;
import java.awt.*;

public class GameBoard {
    private JPanel boardPanel;
    private final int gridSize;
    private int[] selectedSlot = { -1, -1 };
    private final GameUI gameUI;
    private Candy[][] sekerler;

    public GameBoard(int size, GameUI ui) {
        this.gridSize = size;
        this.gameUI = ui;
        initializeBoard();
    }

    private void initializeBoard() {
        boardPanel = new JPanel(new GridLayout(gridSize, gridSize));
        sekerler = new Candy[gridSize][gridSize];

        for (int row = 0; row < gridSize; row++) {
            for (int col = 0; col < gridSize; col++) {
                sekerler[row][col] = new Candy();
                JButton candyButton = sekerler[row][col].getSekerButonu();
                int finalRow = row;
                int finalCol = col;

                candyButton.addActionListener(e -> handleCandyClick(finalRow, finalCol));
                boardPanel.add(candyButton);
            }
        }

        SwingUtilities.invokeLater(() -> {
            updateCandyFontSize();
            refreshBoard();
        });

        SwingUtilities.invokeLater(() -> {
            while (CandyLogic.findAndPopMatches(sekerler, gameUI)) {
                CandyLogic.refillBoard(sekerler);
            }
            refreshBoard();
        });
        
        
    }

    public JPanel getBoardPanel() {
        return boardPanel;
    }
    
    private void refreshBoard() {
        for (int row = 0; row < sekerler.length; row++) {
            for (int col = 0; col < sekerler[row].length; col++) {
                sekerler[row][col].updateButton();
            }
        }
    }

    public void updateCandyFontSize() {
        int slotSize = Math.min(boardPanel.getWidth() / gridSize, boardPanel.getHeight() / gridSize);

        for (int row = 0; row < sekerler.length; row++) {
            for (int col = 0; col < sekerler[row].length; col++) {
                sekerler[row][col].setCandyFont(slotSize);
            }
        }
    }

    private void handleCandyClick(int row, int col) {
        if (selectedSlot[0] == -1) {
            selectedSlot[0] = row;
            selectedSlot[1] = col;
        } else {
            int prevRow = selectedSlot[0];
            int prevCol = selectedSlot[1];
            selectedSlot[0] = -1;

            if (Math.abs(row - prevRow) + Math.abs(col - prevCol) == 1) {
                CandyLogic.swapCandies(sekerler[prevRow][prevCol], sekerler[row][col]);
                refreshBoard();

                if (!CandyLogic.findAndPopMatches(sekerler, gameUI)) {
                    CandyLogic.swapCandies(sekerler[prevRow][prevCol], sekerler[row][col]);
                    refreshBoard();
                } else {
                    do {
                        CandyLogic.refillBoard(sekerler);
                        refreshBoard();
                    } while (CandyLogic.findAndPopMatches(sekerler, gameUI));
                }
            }
        }
    }
}
