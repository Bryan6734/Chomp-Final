import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class Test {

    static ArrayList<Board> boardStates = generateAllBoardStates();
    static ArrayList<Board> losingBoards = new ArrayList<>();
    static ArrayList<Board> winningBoards = new ArrayList<>();

    public static void main(String[] args) {

        determineBoardStates(boardStates);

        System.out.println("Result Boards");
        printEvaluatedBoards();

//        printBoardStates();

    }

    public static void determineBoardStates(ArrayList<Board> boardStates){

        // If any result board is a losing board, then the current board must be winning.
        // If all result boards are known winning boards, then the current board must be losing.

        for (Board board : boardStates){

            for (Board resultBoard : generateResultBoards(board.boardState)){
                // If any result board is a losing board, then the current board must be winning.
                if (containsBoard(losingBoards, resultBoard)){
                    board.isWinning = true;
                    winningBoards.add(board);
                    // Once we find at least one possible way to win, break.
                    break;
                }
            }

            // If a current board is losing, then all of its result boards must be winning.
            boolean allWinningResultBoards = true;

            for (Board resultBoard : generateResultBoards(board.boardState)){
                // If there is at least one losing result board, then our current board must be winning.
                // Otherwise, if every single result board is winning, then our current board must be losing.
                if (!containsBoard(winningBoards, resultBoard)){
                    allWinningResultBoards = false;
                    break;
                }
            }

            // If all result boards are winning, then our current board must be losing, because we cannot send our
            // opponent to a board that would be losing for them.
            if (allWinningResultBoards){
                board.isWinning = false;
                losingBoards.add(board);
            }
        }

        System.out.println("Winning Boards");
        for (Board win : winningBoards){
            System.out.println(win.getBoardState());
        }

        System.out.println("Losing Boards");
        for (Board loss : losingBoards){
            System.out.println(loss.getBoardState());
        }
    }

    /**
     * Generates all starting board states for the 3x3 board
     * @return ArrayList of Board objects
     */
    public static ArrayList<Board> generateAllBoardStates(){
        ArrayList<Board> boardStates = new ArrayList<>();
        for (int x = 1; x < 4; x++){
            for (int y = 0; y <= x; y++){
                for (int z = 0; z <= y; z++){
                    boardStates.add(new Board(new int[] {x, y, z}));
                }
            }
        }

        return boardStates;
    }

    /**
     * Generates all one-move away boards based on a given board state
     * @param board int[] array of boardState
     * @return ArrayList of Board objects
     */
    public static ArrayList<Board> generateResultBoards(int[] board){
        // TODO: Generalize result board move-to-make coordinates
        ArrayList<Board> resultBoards = new ArrayList<>();

        for (int z = board[2] - 1; z >= 0; z--){
            resultBoards.add(new Board(new int[] {board[0], board[1], z}, new int[] {z, 2}));

        }

        for (int y = board[1] - 1; y >= 0; y--){
            if (y < board[2]){
                resultBoards.add(new Board(new int[] {board[0], y, y}, new int[] {y, 1}));
            } else {
                resultBoards.add(new Board(new int[] {board[0], y, board[2]}, new int[] {y, 1}));
            }
        }

        for (int x = board[0] - 1; x > 0; x--){
            if (x < board[1] && x < board[2]){
                resultBoards.add(new Board(new int[] {x, x, x}, new int[] {x, 0}));
            } else if (x < board[1]){
                resultBoards.add(new Board(new int[] {x, x, board[2]}, new int[] {x, 0}));
            } else {
                resultBoards.add(new Board(new int[] {x, board[1], board[2]}, new int[] {x, 0}));
            }
        }

        return resultBoards;
    }


    /**
     *
     * @param resultBoards ArrayList of int[] board states
     * @param board int[] array to be compared
     * @return Boolean representing if the given int[] array is contained in the ArrayList of int[] arrays
     */
    public static boolean containsBoard(ArrayList<Board> resultBoards, Board board){

        for (Board resultBoard : resultBoards){
            if (Arrays.equals(resultBoard.boardState, board.boardState)) return true;
        }
        return false;
    }

    public static void printEvaluatedBoards(){
        for (Board board : boardStates){
            System.out.println(Arrays.toString(board.boardState) + ": " + board.isWinning);
            for (Board result : generateResultBoards(board.boardState)){

                if (containsBoard(winningBoards, result)){
                    System.out.println("---> " + result.getBoardState() + ": true");
                } else{
                    System.out.println("---> " + result.getBoardState() + ": false");
                }

            }
        }
    }

    public static void printBoardStates(){
        for (Board board : boardStates){
            System.out.println(board.getBoardState());
        }
    }



    // Generate n by n board (not complete)
    public static ArrayList<int[]> generateAllBoardStates(int gridSize){
        ArrayList<int[]> boardStates = new ArrayList<>();

        // Generate 5x5 board
        // [5, 5, 5, 5, 5]
        // [1, 0, 0, 0, 0]

        int[] startingBoard = new int[5];
        for (int i = 0; i < gridSize; i++){
            startingBoard[i] = gridSize;
        }

        int currentCol = gridSize - 1;
        int currentVal = startingBoard[currentCol];

        while (currentCol != 0){
            currentVal--;
        }

        /*
        [5, 5, 5, 5, 5]
        [5, 5, 5, 5, 4]
        [5, 5, 5, 5, 3]
        [5, 5, 5, 5, 2]
        [5, 5, 5, 5, 1]
        [5, 5, 5, 5, 0]
        [5, 5, 5, 4, 4]
        [5, 5, 5, 3, 3]
        [5, 5, 5, 2, 2]
        [5, 5, 5, 1, 1]
        [5, 5, 5, 0, 0]
        [5, 5, 4, 5, 5]
        s[0], s[1], s[2], s[2] if s[2] less than s[3], else s[3], s[2] if s[2] less than s[4] else s[4]
        [5, 5, 3, 0, 0]
        [5, 5, 2, 0, 0]
        [5, 5, 1, 0, 0]
        [5, 5, 5, 0, 0]

        gridSize = 5 (dimensions of the grid)
        int rIndex = 1 (5 - 1 = 4, meaning we start at board[4] which is at the right)
        decrease board[gridSize - rIndex] -= 1
        continue doing that

        if board[gridSize - 1] == 0
            rIndex = 2 (move to the 4th column, or index 3 of board)
         */

//


        return boardStates;
    }


}
