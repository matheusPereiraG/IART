import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class Level {
    private ArrayList<ArrayList<String>> level;
    private String name;
    private int width;
    private int height;
    private ArrayList<Piece> pieces;

    Level(String name) {
        this.name = name;
        level = new ArrayList<>();


        //open file
        try {
            File myObj = new File(name);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] cells = data.split(" ");
                ArrayList<String> line = new ArrayList<>(Arrays.asList(cells));
                level.add(line);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred loading level " + name + ".");
            e.printStackTrace();
            return;
        }


        this.height = level.size();
        this.width = level.get(0).size();

        // get pieces
        pieces = new ArrayList<>();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                String numSteps = level.get(i).get(j);
                if (numSteps.matches("-?\\d+")) {
                    Piece newPiece = new Piece(j+1, i+1, Integer.parseInt(numSteps));
                    pieces.add(newPiece);
                }
            }
        }
    }



    public ArrayList<String> getLine(int i){
        return level.get(i);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public ArrayList<Piece> getAllPieces() {

        return pieces;
    }
}
