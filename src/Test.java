import java.util.ArrayList;
import java.util.Arrays;


public class Test {

    public static void main(String[] args) {

        ArrayList<int[]> boardStates = generateAllBoardStates();

        for (int[] board : boardStates){
            System.out.println(Arrays.toString(board));
            for (int[] result : generateResultBoards(board)){
                System.out.println("---- " + Arrays.toString(result));
            }
        }
    }

    public static ArrayList<int[]> generateResultBoards(int[] board){
        ArrayList<int[]> resultBoards = new ArrayList<>();

        for (int z = board[2] - 1; z >= 0; z--){
            resultBoards.add(new int[] {board[0], board[1], z});
        }

        for (int y = board[1] - 1; y >= 0; y--){
            if (y < board[2]){
                resultBoards.add(new int[] {board[0], y, y});
            } else {
                resultBoards.add(new int[] {board[0], y, board[2]});
            }
        }

        for (int x = board[0] - 1; x > 0; x--){
            if (x < board[1] && x < board[2]){
                resultBoards.add(new int[] {x, x, x});
            } else if (x < board[1]){
                resultBoards.add(new int[] {x, x, board[2]});
            } else {
                resultBoards.add(new int[] {x, board[1], board[2]});
            }
        }


        return resultBoards;
    }

//            for (int z = board[2]; z >= 0; z--) {
//            for (int y = board[1]; y >= z; y--) {
//                for (int x = board[0]; x >= y && x > 0; x--) {
//                    resultBoards.add(new int[] {x, y, z});
//                }
//            }
//        }

    public static ArrayList<int[]> generateAllBoardStates(){
        ArrayList<int[]> boardStates = new ArrayList<>();

        for (int x = 1; x < 4; x++){
            for (int y = 0; y <= x; y++){
                for (int z = 0; z <= y; z++){
                    boardStates.add(new int[] {x, y, z});
                }
            }
        }

        return boardStates;
    }


}
