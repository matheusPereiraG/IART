import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Level {
    private static ArrayList<ArrayList<String>> level;
    private static String name;
    private static int width;
    private static int height;
    private ArrayList<Piece> pieces;

    Level(String name){
        this.name = name;
        try {
            File myObj = new File(name);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                ArrayList<String> line = new ArrayList<>();
                String[] cells = data.split(" ");
                for(String cell: cells)
                    line.add(cell);
                level.add(line);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    private static void parseLevel(String levelName){
        try {
            File myObj = new File(levelName);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                ArrayList<String> line = new ArrayList<>();
                String[] cells = data.split(" ");
                for(String cell: cells)
                    line.add(cell);
                level.add(line);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }


    }

    public static ArrayList<String> getLine(int i){
        return level.get(i);
    }

    public static int getWidth() {
        return width;
    }

    public static int getHeight() {
        return height;
    }

    public ArrayList<Piece> getAllPieces() {
        /*
        ArrayList<Piece> lvlPieces = new ArrayList<>();
        for(int i= 0; i < level.size(); i++)
            for(int j = 0; j < level.get(i).size(); j++) {
                String numSteps = level.get(i).get(j);
                if(numSteps.matches("-?\\d+")) {
                    Piece newPiece = new Piece(j+1, i+1, Integer.parseInt(numSteps));
                    lvlPieces.add(newPiece);
                }
            }
            */

        return pieces;
    }
}
