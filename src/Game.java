import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    private static ArrayList<ArrayList<String>> level;

    public static void main(String[] args) {
        level = new ArrayList<ArrayList<String>>();
        parseLevel();
        printBoard();
        startGame();

    }

    private static void startGame() {

    }

    private static void printBoard() {
        Utils.clearScreen();
        for(ArrayList<String> line: level) {
            //printLineBreak();
            for(String cell: line) {
                System.out.print("|");
                if(cell.equals("B")) System.out.print(" ");
                else System.out.print(cell);
            }

            System.out.println();
        }
    }

    private static void printLineBreak() {
        for(int i = 0; i < level.size()*2; i++)
            System.out.print("―");
        System.out.println();
    }


    private static void parseLevel(){
        try {
            File myObj = new File("lvl1.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                ArrayList<String> line = new ArrayList<>();
                String[] cells = data.split("-");
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

}
