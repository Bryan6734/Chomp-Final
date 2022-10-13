import java.util.ArrayList;
import java.util.Arrays;

public class Test {

    public static void main(String[] args) {

        ArrayList<int[]> boardStates = generateBoardStates1();

        for (int[] state : boardStates){
            System.out.println(Arrays.toString(state));
        }

        System.out.println(boardStates.size());

    }


    public static ArrayList<int[]> generateBoardStates1(){
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

    public static ArrayList<int[]> generateBoardStates2(){

        ArrayList<int[]> boardStates = new ArrayList<>();

        // Solution 2
        for (int x = 1; x < 4; x++){
            for (int y = 0; y < 4; y++){
                for (int z = 0; z < 4; z++){
                    if (x >= y && y >= z){
                        boardStates.add(new int[] {x, y, z});
                    }
                }
            }
        }

        return boardStates;

    }
}
