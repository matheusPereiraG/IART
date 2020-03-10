import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

//TODO:
//comparação  entre  métodos  de  pesquisa  não  informada (pesquisa  primeiro  em  largura,
//primeiro  em profundidade,  aprofundamento  progressivo,  custo  uniforme)  e  métodos  de  pesquisa  heurística
// (pesquisa gulosa, A*), com diferentes funções heurísticas.
//0: Finish validMove(), checkGameOver(), movePiece()
//1: Start by making a Graph with nodes and edges
//2: apply algorithms like DFS and BFS
//3: apply the A* algorithm

public class Game {
    private static ArrayList<ArrayList<String>> level;
    private static Graph graph;

    public static void main(String[] args) {
        level = new ArrayList<>();
        parseLevel();
        startGame();

    }

    private static void startGame() {
        //build graph
        initGraph();


        while(true) {
            printBoard();
            ArrayList<Integer> position = selectPiece();
            if(position.isEmpty()) {
                System.out.println("Invalid Position");
                continue;
            }


        }



    }

    private static void initGraph() {
        graph = new Graph();

        int numPieces = Utils.getNumberOfPieces(level);
        int numVertex = 1; //we started counting the root node

        //how much nodes do i need to init?
        for(int i = 0; i < numPieces+1; i++) {
            //the total number of states is given by the formula "(4^k *n) +1", k ∈ [0...n+1] where n is the number of pieces
            numVertex += (Math.pow(4,i) * numPieces);
        }

        //init nodes
        for(int j = 0; j < numVertex; j++) {
            Node newNode = new Node(j);
            graph.addNode(newNode);
        }

        System.out.println(numVertex);



    }

    private static ArrayList<Integer> selectPiece() {
        ArrayList<Integer> pos = new ArrayList<>();

        //ask user to input which piece to select
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Select piece (Line):");
        int xpos = myObj.nextInt();  // Read user input

        System.out.println("Select piece (Col):");
        int ypos = myObj.nextInt();

        if(xpos <= 0 || xpos > level.size()+1 || ypos <= 0 || ypos > level.size()+1) return pos;
        else {
            //check if its a valid piece too
            String piece = level.get(xpos-1).get(ypos-1);
            pos.add(ypos);
            pos.add(xpos);
        }


        return pos;
    }

    private static void printBoard() {
        //Utils.clearScreen();
        printHeader();
        //for(ArrayList<String> line: level) {
        for(int i = 0; i < level.size(); i++){
            printLineBreak();
            printCol(i);
            for(String cell: level.get(i)) {
                System.out.print("|  ");
                if(cell.equals(".")) System.out.print("   ");
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

}
