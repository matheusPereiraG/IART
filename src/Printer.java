import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Printer {

    public static void headline(){
        System.out.println("::::::::::::::::::::::::::");
        System.out.println("::         ZHED         ::");
        System.out.println("::::::::::::::::::::::::::");
        System.out.println();
    }

    public static int mainMenu(){
        System.out.println("1. Utilizador joga.");
        System.out.println("2. Computador joga 'pesquisa primeiro em largura'.");
        System.out.println("3. Computador joga 'pesquisa primeiro em profundidade'.");
        System.out.println("4. Computador joga 'aprofundamento progressivo'.");
        System.out.println("5. Computador joga 'heuristica: pesquisa gulosa'.");
        System.out.println("6. Computador joga 'heuristica: A*'.");
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
        if (option < 1 || option > 6){
            System.out.println("Input inválido.");
            System.out.println();
            System.out.println();
            option = mainMenu();
        }
        return option;
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

}
