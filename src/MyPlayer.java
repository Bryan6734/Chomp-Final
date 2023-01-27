import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Arrays;
import java.util.HashMap;

public class MyPlayer {
    public HashMap<String, int[]> hashmapMoves = new HashMap<>();
    public Chip[][] gameBoard;
    public int[] columns;

    public MyPlayer() {
        columns = new int[10];
    }

    public Point move(Chip[][] gameBoard) {
        System.out.println("MyPlayer Move");

        this.gameBoard = gameBoard;
        this.columns = convertGameBoard(gameBoard);
        int[] moveToMake;

        if (hashmapMoves.containsKey(Arrays.toString(this.columns))){
            moveToMake = hashmapMoves.get(Arrays.toString(this.columns));
            return new Point(moveToMake[0], moveToMake[1]);
        } else {
            int randomCol;
            int randomRow;

            do {
                randomRow = (int) (Math.random() * 10);
                randomCol = (int) (Math.random() * 10);
            } while(!gameBoard[randomRow][randomCol].isAlive);

            return new Point(randomRow,randomCol);
        }


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

    public void unserializeHashMap(){
        try {
            FileInputStream fis = new FileInputStream("stringHashmapMoves.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);
            hashmapMoves = (HashMap<String, int[]>) ois.readObject();
            ois.close();
            fis.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
