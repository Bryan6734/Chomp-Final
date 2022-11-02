import java.util.Arrays;

public class Board {

    int[] boardState;
    boolean isWinning;
    int[] coordinates = new int[2];
    int[] moveToMake = new int[2];

    public Board(int[] boardState){
        this.boardState = boardState;
    }

    public Board (int[] boardState, int[] coordinates){
        this.boardState = boardState;
        this.coordinates = coordinates;
    }

    public String getBoardState(){
        return Arrays.toString(boardState);
    }

    public String getInfo(){
        return Arrays.toString(boardState) + " move: " + Arrays.toString(coordinates);
    }

}
