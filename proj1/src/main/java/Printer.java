import javax.swing.*;
import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.util.*;

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
        System.out.println("6. Computador joga 'heuristica: A* com fator 3'.");
        System.out.println("7. Computador joga 'heuristica: A* com fator 4'.");
        System.out.println("8. Computador joga 'heuristica: A* com fator 5'.");
        System.out.println("9. Ativar/Desativar mensagens debug.");
        System.out.println("0. Sair do jogo.");
        System.out.println();
        System.out.println("Intruduza a sua opção:");

        Scanner scanner = new Scanner(System.in);
        int option;
        try {
            option = scanner.nextInt();
            System.out.println(option);
        }
        catch (InputMismatchException e){
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
            System.out.println(level);
        }
        catch (InputMismatchException e){
            System.out.println("Input inválido.");
            level = selectLevel(numLevels);
        }
        if (level < 1 || level > numLevels){
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
            System.out.println("Selecione a linha:");
            xpos = myObj.nextInt();  // Read user input
            System.out.println(xpos);

            System.out.println("Selecione a coluna:");
            ypos = myObj.nextInt();
            System.out.println(ypos);
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
            System.out.println("== Selecione a direção ==");
            System.out.println("(1->Cima; 2->Baixo; 3->Esquerda; 4->Direita)");
            System.out.println("Direção:");
            dir = myObj.nextInt();
            System.out.println(dir);
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
        System.out.println("::           PARABÉNS!          ::");
        System.out.println("::       PASSOU O NÍVEL "+ level +"       ::");
        System.out.println("::::::::::::::::::::::::::::::::::");
        System.out.println();
    }

    public static void nodeInfo(NewNode node){

        if (!node.isRoot()){
            System.out.println();
            System.out.println("Nó: " + node);
            System.out.println("Pai: " + node.getDad());
            System.out.println("Última peça: " + node.getLastPiece());
            System.out.println("Último operador: " + node.getLastOperator());
            System.out.println("Profundidade: " + node.getDepth());
            System.out.println("Custo: " + node.getCost());
            System.out.println("Prioridade: " +node.getPriority());
            System.out.println("Estado:");

            compactBoard(node.getState());
            node = node.getDad();
        }
        else {
            System.out.println();
            System.out.println("Root node: " + node);
            compactBoard(node.getState());
        }
    }

    public static void solution(NewNode node){
        if(node.getClass() == NewNodeNull.class){
            System.out.println();
            System.out.println("::::::::::::::::::::::::::::::::::");
            System.out.println("::       Nível impossivel!      ::");
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
        
        System.out.println();

    }

    public static void printNodesQueue(Queue<NewNode> nodesWaiting){
        System.out.println();
        System.out.println("::::::::::::::::::::::");
        System.out.println("::   nós à espera   ::");

        for (NewNode node : nodesWaiting)
            System.out.println(":: "+node+" ::");
        System.out.println("::::::::::::::::::::::");
        System.out.println();
    }

    public static void printNodesStack(Stack<NewNode> nodesWaiting){
        System.out.println();
        System.out.println("::::::::::::::::::::::");
        System.out.println("::   nós à espera   ::");

        for (NewNode node : nodesWaiting)
            System.out.println(":: "+node+" ::");
        System.out.println("::::::::::::::::::::::");
        System.out.println();
    }

    public static void performance(int level, int option, long time) {
        System.out.println();
        System.out.println("== Informação de performance ==" );
        System.out.println("Nivel jogado: " + level);
        switch(option){
            case 2:
                System.out.println("Algoritmo utilizado: pesquisa primeiro em largura");
                break;
            case 3:
                System.out.println("Algoritmo utilizado: pesquisa primeiro em profundidade");
                break;
            case 4:
                System.out.println("Algoritmo utilizado: pesquisa com aprofundamento progressivo");
                break;
            case 5:
                System.out.println("Algoritmo utilizado: heuristica-> pesquisa gulosa");
                break;
            case 6:
                System.out.println("Algoritmo utilizado: heuristica-> A* com fator 3");
            case 7:
                System.out.println("Algoritmo utilizado: heuristica-> A* com fator 4");
            case 8:
                System.out.println("Algoritmo utilizado: heuristica-> A* com fator 5");
                break;

        }
        System.out.println("Tempo gasto (ms): " + time);
        System.out.println("Quantidade de nós criados: " + NewNode.getNodeCounter());
    }
}
