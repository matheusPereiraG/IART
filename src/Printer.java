import javax.swing.*;
import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Printer {

    public static void headline(){
        System.out.println();
        System.out.println();
        System.out.println("::::::::::::::::::::::::::::::::::");
        System.out.println("::             ZHED             ::");
        System.out.println("::::::::::::::::::::::::::::::::::");
        System.out.println();
    }

    public static int mainMenu(){
        System.out.println("1. Utilizador joga.");
        System.out.println("2. Computador joga 'pesquisa primeiro em largura'.");
        System.out.println("3. Computador joga 'pesquisa primeiro em profundidade'.");
        System.out.println("4. Computador joga 'aprofundamento progressivo'.");
        System.out.println("5. Computador joga 'heuristica: pesquisa gulosa'.");
        System.out.println("6. Computador joga 'heuristica: A*'.");
        System.out.println("0. Sair do jogo.");
        System.out.println();
        System.out.println("Intruduza a sua opção:");

        Scanner scanner = new Scanner(System.in);
        int option;
        try {
            option = scanner.nextInt();
        }
        catch (InputMismatchException e){
            System.out.println("Input inválido.");
            System.out.println();
            System.out.println();
            option = mainMenu();
        }
        if (option < 0 || option > 6){
            System.out.println("Input inválido.");
            System.out.println();
            System.out.println();
            option = mainMenu();
        }
        return option;
    }

    public static int selectLevel(int numLevels) {
        System.out.println();
        System.out.println("Selecione o nivel que deseja jogar (1-" + numLevels + "):");

        Scanner scanner = new Scanner(System.in);
        int level;
        try {
            level = scanner.nextInt();
        }
        catch (InputMismatchException e){
            System.out.println("Input inválido.");
            level = selectLevel(numLevels);
        }
        if (level < 0 || level > numLevels){
            System.out.println("Input inválido.");
            level = selectLevel(numLevels);
        }
        return level;

    }

    public static void board(Level level) {

        boardHeader(level.getWidth());
        //for(ArrayList<String> line: level) {
        for(int i = 0; i < level.getHeight(); i++){
            boardLineBreak(level.getHeight());
            boardCol(i);
            for(String cell: level.getLine(i)) {
                System.out.print("|  ");
                if(cell.equals(".")) System.out.print("   ");
                else System.out.print(cell + "  ");
            }
            System.out.print("|  ");
            System.out.println();
        }
        boardLineBreak(level.getWidth());
    }

    public static void compactBoard(Level level) {

        for(int i = 0; i < level.getHeight(); i++){
            for(String cell: level.getLine(i)) {
                System.out.print(" " + cell );
            }
            System.out.println();
        }
    }

    private static void boardCol(int colNum) {
        System.out.print(colNum +1 + " ");

    }

    private static void boardHeader(int size) {
        System.out.print("  ");
        for(int i = 1; i <= size; i++)
            System.out.print("   " + i + "  ");
        System.out.println();
    }

    private static void boardLineBreak(int size) {
        System.out.print("  ");
        for(int i = 0; i < size * (6+1); i++)
            System.out.print("―");
        System.out.println();
    }

    public static Position selectPiece() {
        Position pos;
        int xpos;
        int ypos;
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object

        try {
            System.out.println("Select piece (Line):");
            xpos = myObj.nextInt();  // Read user input

            System.out.println("Select piece (Col):");
            ypos = myObj.nextInt();
            return new Position(xpos, ypos);
        }
        catch (InputMismatchException e){
            System.out.println("Input inválido.");
            System.out.println();
            System.out.println();
            pos = selectPiece();
        }

        return pos;
    }

    public static int selectDirection() {
        int dir;
        Scanner myObj = new Scanner(System.in);
        try {
            System.out.println("Select direction");
            System.out.println("1.Up; 2.Down; 3.Left; 4.Right.");
            System.out.println("Your option:");
            dir = myObj.nextInt();
        }
        catch (InputMismatchException e){
            System.out.println("Input inválido.");
            System.out.println();
            System.out.println();
            dir = selectDirection();
        }

        if(dir<1 || dir>4){
            System.out.println("Input inválido.");
            System.out.println();
            System.out.println();
            dir = selectDirection();
        }

        return dir;
    }

    public static void youWon(int level) {
        System.out.println();
        System.out.println("::::::::::::::::::::::::::::::::::");
        System.out.println("::       CONGRATULATIONS!       ::");
        System.out.println("::       YOU WON LEVEL "+ level +"        ::");
        System.out.println("::::::::::::::::::::::::::::::::::");
        System.out.println();
    }

    public static void nodeInfo(NewNode node){

        if (!node.isRoot()){
            System.out.println();
            System.out.println("Node: " + node);
            System.out.println("Dad: " + node.getDad());
            System.out.println("Last piece: " + node.getLastPiece());
            System.out.println("Last operator: " + node.getLastOperator());
            System.out.println("Depth: " + node.getDepth());
            System.out.println("Cost: " + node.getCost());
            System.out.println("State:");

            compactBoard(node.getState());
            node = node.getDad();
        }
        else {
            System.out.println();
            System.out.println("Root node:");
            compactBoard(node.getState());
        }
    }

    public static void solution(NewNode node){
        if(node.getClass() == NewNodeNull.class){
            System.out.println();
            System.out.println("::::::::::::::::::::::::::::::::::");
            System.out.println("::   This level is impossible!  ::");
            System.out.println("::::::::::::::::::::::::::::::::::");
            System.out.println();
            System.out.println();
            return;
        }

        NewNode currNode = node;
        nodeInfo(currNode);
        do{
            currNode = currNode.getDad();
            nodeInfo(currNode);
        }while (!currNode.isRoot());

    }
}
