import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Utils {


    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static ArrayList<Piece> getlvlPieces(ArrayList<ArrayList<String>> level) {
        ArrayList<Piece> lvlPieces = new ArrayList<>();
        for(int i= 0; i < level.size(); i++)
            for(int j = 0; j < level.get(i).size(); j++) {
                String numSteps = level.get(i).get(j);
                if(numSteps.matches("-?\\d+")) {
                    Piece newPiece = new Piece(j+1, i+1, Integer.parseInt(numSteps));
                    lvlPieces.add(newPiece);
                }
            }
        return lvlPieces;
    }

    public static ArrayList<Piece> getPossibleOutcomes(ArrayList<Piece> pieces, List<Piece> pieceSequence) {
        ArrayList<Piece> possiblePieces = new ArrayList<>();

        for(int i = 0; i < pieces.size(); i++) {
            if(!pieceSequence.contains(pieces.get(i))) possiblePieces.add(pieces.get(i));
        }

        return possiblePieces;
    }
}
