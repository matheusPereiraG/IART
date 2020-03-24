import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
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

        ArrayList<Piece> pieces = Utils.getlvlPieces(level);
        int numVertex = 1;

        //init root node
        Node rootNode = new Node();

        for(int k= 0; k < pieces.size(); k++) {
            Node childNode = new Node(pieces.get(k));
            Edge e = new Edge(rootNode,childNode,'n'); //null action
            rootNode.addEdge(e);
            childNode.addToSequence(pieces.get(k));
            graph.addNode(childNode);
        }

        graph.addNode(rootNode);


        //now for each different piece generate all other outcomes depending on the selected piece
        for(int i = 0; i < rootNode.getChildren().size(); i++) {
            Node childNode = rootNode.getChildren().get(i).getChild();

            List<Piece> pieceSequence = childNode.getSeqChosenPieces();

            ArrayList<Piece> possibleOutcomes = Utils.getPossibleOutcomes(pieces,pieceSequence); //returns list of pieces there are yet to be selected
        
            //for(int j = 0; j < )

        }



        for(int t = 0; t < graph.getNodes().size(); t++) {
            graph.getNode(t).printInfo();
        }
/*
        //init nodes
        for(int j = 1; j < numVertex; j = j+5) { //i think the leaves have children too
            Node newParentNode = new Node(j);

            Node newChildNodeUp = new Node(j+1);
            Node newChildNodeDown = new Node(j+2);
            Node newChildNodeLeft = new Node(j+3);
            Node newChildNodeRight = new Node(j+4);

            Edge e1 = new Edge(newParentNode,newChildNodeUp,'U');
            Edge e2 = new Edge(newParentNode,newChildNodeDown,'D');
            Edge e3 = new Edge(newParentNode,newChildNodeLeft,'L');
            Edge e4 = new Edge(newParentNode,newChildNodeRight,'R');

            graph.addNode(newParentNode);
            graph.addNode(newChildNodeDown);
            graph.addNode(newChildNodeLeft);
            graph.addNode(newChildNodeRight);
            graph.addNode(newChildNodeUp);

            //TODO: set adj vertexes

            newParentNode.addEdge(e1);
            newParentNode.addEdge(e2);
            newParentNode.addEdge(e3);
            newParentNode.addEdge(e4);
        }





        for(int t = 0; t < graph.getNodes().size(); t++) {
            graph.getNode(t).printInfo();
        }
        */
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
