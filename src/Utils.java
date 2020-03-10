import java.lang.reflect.Array;
import java.util.ArrayList;

public class Utils {


    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static int getNumberOfPieces(ArrayList<ArrayList<String>> level) {
        int counter = 0;

        for(int i= 0; i < level.size(); i++)
            for(int j = 0; j < level.get(i).size(); j++) {
                String piece = level.get(i).get(j);
                if(piece.matches("-?\\d+")) {
                    counter++;
                }
            }


        return counter;
    }
}
