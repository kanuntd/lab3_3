import java.util.Scanner;

public class Game {
    private static OX ox;
    private static Scanner kb = new Scanner(System.in);
    private static int row, col;

    public static void main(String[] args) {
        ox = new OX();
        while (true) {
            printTable();
            input();
            if (ox.checkWin(col, row)) {
                printTable();
                printWin();
                printScore("x win : " + ox.getCountX(), "o win : ", ox.getCountO(), "Draw : ", ox.getCountDraw());
                ox.reset();
                continue;
            }
            if (ox.isDraw()) {
                printTable();
                printScore("Draw", "x win : ", ox.getCountX(), "o win : ", ox.getCountO());
                printDraw("Draw : ", ox.getCountDraw());
                ox.reset();
                continue;
            }
            ox.switchPlayer();
        }

    }

    public static void input() {
        boolean canPut = true;
        do {
            System.out.print(ox.getCurrentPlayer() + " Col :");
            col = kb.nextInt();
            System.out.print(ox.getCurrentPlayer() + " Row :");
            row = kb.nextInt();
            canPut = ox.put(col, row);
            if (!canPut) {
                System.out.println("Please input number between 0-2");
            }

        } while (!canPut);
    }

    private static void printTable() {
        System.out.print(ox.getTableString());
    }

    private static void printDraw(String s, int scoreDraw) {
        System.out.println(s + scoreDraw);
    }

    private static void printWin() {
        System.out.println(ox.getCurrentPlayer() + " " + "Win");
    }

    private static void printScore(String s, String s2, int scoreO, String s3, int scoreDraw) {
        System.out.println(s);
        printDraw(s2, scoreO);
        printDraw(s3, scoreDraw);
    }
}
