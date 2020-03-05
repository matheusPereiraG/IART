import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

//TODO:
//comparação  entre  métodos  de  pesquisa  não  informada (pesquisa  primeiro  em  largura,
//primeiro  em profundidade,  aprofundamento  progressivo,  custo  uniforme)  e  métodos  de  pesquisa  heurística
// (pesquisa gulosa, A*), com diferentes funções heurísticas.

public class Game {
    private static ArrayList<ArrayList<String>> level;

    public static void main(String[] args) {
        level = new ArrayList<>();
        parseLevel();
        printBoard();
        //startGame();

    }

    private static void startGame() {
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Select piece (X):");
        String xpos = myObj.nextLine();  // Read user input
        System.out.println("X is: " + xpos);  // Output user input
    }

    private static void printBoard() {
        Utils.clearScreen();
        printHeader();
        //for(ArrayList<String> line: level) {
        for(int i = 0; i < level.size(); i++){
            printLineBreak();
            printCol(i);
            for(String cell: level.get(i)) {
                System.out.print("|  ");
                if(cell.equals("B")) System.out.print("   ");
                else System.out.print(cell + "  ");
            }
            System.out.println();
        }
    }

    private static void printCol(int colNum) {
        System.out.print(colNum +1 + " ");

    }

    private static void printHeader() {
        System.out.print("  ");
        for(int i = 1; i <= level.size(); i++)
            System.out.print("   " + i + "  ");
        System.out.println();
    }

    private static void printLineBreak() {
        System.out.print("  ");
        for(int i = 0; i < level.size()*6; i++)
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
