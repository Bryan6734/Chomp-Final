import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Test2 {

    ArrayList<Board> winningBoards = new ArrayList<>();
    ArrayList<Board> losingBoards = new ArrayList<>();
    HashMap<int[], int[]> correctMoves = new HashMap<>();

    HashMap<int[], int[]> hashmapMoves = new HashMap<>();
    HashMap<String, int[]> betterHashMapMoves = new HashMap<>();

    public static void main(String[] args) {

        Test2 test = new Test2();

    }

    public Test2(){
        unserializeHashMap();
        serializeHashMap();

    }

    private void unserializeHashMap(){
        try {
            FileInputStream fis = new FileInputStream("hashmapMoves.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);
            hashmapMoves = (HashMap<int[], int[]>) ois.readObject();

            for (int[] key : hashmapMoves.keySet()){
                betterHashMapMoves.put(Arrays.toString(key), hashmapMoves.get(key));
            }

            ois.close();
            fis.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void serializeHashMap(){
        try {
            FileOutputStream fos
                    = new FileOutputStream("stringHashmapMoves.ser");

            ObjectOutputStream oos
                    = new ObjectOutputStream(fos);

            oos.writeObject(betterHashMapMoves);
            oos.close();
            fos.close();

            System.out.println("HashMap done");
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public void readStartingBoards(){
        System.out.println("reading starting boards");

        try {
            FileInputStream fis = new FileInputStream("winningBoards.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);
            winningBoards = (ArrayList<Board>) ois.readObject();
            ois.close();
            fis.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            FileInputStream fis = new FileInputStream("losingBoards.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);
            losingBoards = (ArrayList<Board>) ois.readObject();
            ois.close();
            fis.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}