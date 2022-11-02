import java.awt.*;

public class MyPlayer {
    public Chip[][] gameBoard;
    public int[] columns;
    public ChompSolver chompSolver;

    public MyPlayer() {
        columns = new int[10];
        chompSolver = new ChompSolver();
    }

    public Point move(Chip[][] gameBoard) {
        System.out.println("MyPlayer Move");

        this.gameBoard = gameBoard;
        this.columns = convertGameBoard(gameBoard);

        int[] moveToMake = chompSolver.getMove(this.columns);

        int col = moveToMake[1];
        int row = moveToMake[0];
        return new Point(row, col);

    }

    private int[] convertGameBoard(Chip[][] gameBoard){
        int[] boardState = new int[10];

        for (Chip[] chipsRow : gameBoard) {
            for (int col = 0; col < chipsRow.length; col++) {
                if (chipsRow[col].isAlive) {
                    boardState[col]++;
                }
            }
            System.out.println();
        }

        return boardState;
    }

}
