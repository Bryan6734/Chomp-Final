import java.io.Serializable;
import java.util.Arrays;

public class Board implements Serializable {

    int[] boardState;
    boolean isWinning = false;
    int[] coordinates = new int[2];
    int[] moveToMake = new int[2];

    public Board(int[] boardState){
        this.boardState = boardState;
    }

    public Board (int[] boardState, int[] coordinates){
        this.boardState = boardState;
        this.coordinates = coordinates;
    }


    public String getBoardState() {
        return Arrays.toString(boardState);
    }
}