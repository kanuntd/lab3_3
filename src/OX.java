public class OX {
    private String[][] table = {
            { " ", "0", "1", "2" },
            { "0", "-", "-", "-" },
            { "1", "-", "-", "-" },
            { "2", "-", "-", "-" },
    };
    private String player;
    private int turnCount;
    private int countX;
    private int countO;
    private int countDraw;

    public OX() {
        player = "X";
        turnCount = 0;
        countX = 0;
        countO = 0;
        countDraw = 0;
    }

    public String getTableString() {
        String result = "";
        for (int row = 0; row < table.length; row++) {
            for (int col = 0; col < table[row].length; col++) {
                result = result +table[row][col];
            }
            result = result + "\n";
        }
        return result;
    }

    public String getCurrentPlayer() {
        return player;
    }

    public int getCountO() {
        return countO;
    }

    public int getCountX() {
        return countX;
    }

    public int getCountDraw() {
        return countDraw;
    }

    public int getTurnCount() {
        return turnCount;
    }

    public boolean put(int col, int row) {
        try {
            if (!table[row + 1][col + 1].equals("-")) {
                return false;
            }
            table[row + 1][col + 1] = getCurrentPlayer();

        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
        turnCount++;
        if (checkWin(col, row)) {
            if (player.equals("X")) {
                countX++;
            } else if (player.equals("O")) {
                countO++;
            }
        }

        if (isDraw()) {
            countDraw++;
        }

        return true;
    }

    public void switchPlayer() {
        if (player.equals("X")) {
            player = "O";
        } else {
            player = "X";
        }

    }

    public String get(int col, int row) {
        if (col > 2 || col < 0 || row > 2 || row < 0) {
            return null;
        }
        return table[row + 1][col + 1];
    }

    public boolean checkWin(int col, int row) {
        /* checkColWin */
        boolean colWin = true;
        for (int i = 0; i < 3; i++) {
            if (!table[i+1][col + 1].equals(player)) {
                colWin = false;
            }
        }
        if (colWin) {
            return true;
        }
        /* checkRowWin */
        boolean rowWin = true;
        for (int i = 0; i < 3; i++) {
            if (!table[row + 1][i + 1].equals(player)) {
                rowWin = false;
            }
        }
        if (rowWin) {
            return true;
        }

        /* checkEsWin */
        boolean esWin = true;
        for (int i = 0; i < 3; i++) {
            if (!table[i + 1][i + 1].equals(player)) {
                esWin = false;
            }
        }
        if (esWin) {
            return true;
        }

        /* checkEsWin */
        boolean ssWin = true;
        for (int i = 0; i < 3; i++) {
            /* col,row -> 2,0, 1,1, 0,2 */
            /* row,col -> 1,3, 2,2, 3,1 */
            /* row,col -> i:0 ,i+1,3-i, i:1 2,2, 3,1 */
            if (!table[i + 1][3 - i].equals(player)) {
                ssWin = false;
            }
        }
        if (ssWin) {
            return true;
        }
        return false;

    }

    public void reset() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                table[i + 1][j + 1] = "-";
            }
        }
        player = "X";
        turnCount = 0;
    }

    public boolean isDraw() {
        if (turnCount < 9) {
            return false;
        }
        return true;
    }
}

