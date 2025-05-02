package game10;

import java.util.Random;

public class CandyLogic {
    private static final Random random = new Random();
    private static String[] sekerTurleri = { "🐣", "🐳", "🦄", "🐙" };

    public static void increaseCandyTypes(int level) {
        String[] yeniTurler = { "🐣", "🐳", "🦄", "🐙", "🐷", "🐶", "🐻", "🐸", "🐔" };
        sekerTurleri = new String[level + 3];
        System.arraycopy(yeniTurler, 0, sekerTurleri, 0, level + 3);
    }

    public static void swapCandies(Candy seker1, Candy seker2) {
        String temp = seker1.getType();
        seker1.setType(seker2.getType());
        seker2.setType(temp);
        seker1.updateButton();
        seker2.updateButton();
    }
    
    public static void refillBoard(Candy[][] sekerler) {
        for (int row = 0; row < sekerler.length; row++) {
            for (int col = 0; col < sekerler[row].length; col++) {
                if (sekerler[row][col].getType() == null) {
                    sekerler[row][col].setType(randomCandy());
                }
            }
        }
    }

    public static String randomCandy() {
        return sekerTurleri[random.nextInt(sekerTurleri.length)];
    }

    public static boolean findAndPopMatches(Candy[][] sekerler, GameUI gameUI) {
        boolean hasMatches = false;
        boolean[][] toPop = new boolean[sekerler.length][sekerler[0].length];

        for (int row = 0; row < sekerler.length; row++) {
            for (int col = 0; col < sekerler[row].length - 2; col++) {
                if (sekerler[row][col].getType() != null &&
                        sekerler[row][col].getType().equals(sekerler[row][col + 1].getType()) &&
                        sekerler[row][col].getType().equals(sekerler[row][col + 2].getType())) {
                    hasMatches = true;
                    toPop[row][col] = true;
                    toPop[row][col + 1] = true;
                    toPop[row][col + 2] = true;
                }
            }
        }

        for (int col = 0; col < sekerler[0].length; col++) {
            for (int row = 0; row < sekerler.length - 2; row++) {
                if (sekerler[row][col].getType() != null &&
                        sekerler[row][col].getType().equals(sekerler[row + 1][col].getType()) &&
                        sekerler[row][col].getType().equals(sekerler[row + 2][col].getType())) {
                    hasMatches = true;
                    toPop[row][col] = true;
                    toPop[row + 1][col] = true;
                    toPop[row + 2][col] = true;
                }
            }
        }

        for (int row = 0; row < sekerler.length; row++) {
            for (int col = 0; col < sekerler[row].length; col++) {
                if (toPop[row][col]) {
                    sekerler[row][col].setType(null);
                }
            }
        }

        if (hasMatches) {
            gameUI.addScore(75);
        }

        return hasMatches;
    }
}
