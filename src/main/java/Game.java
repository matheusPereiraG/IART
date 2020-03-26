import java.util.ArrayList;


//TODO:
//comparação  entre  métodos  de  pesquisa  não  informada (pesquisa  primeiro  em  largura,
//primeiro  em profundidade,  aprofundamento  progressivo,  custo  uniforme)  e  métodos  de  pesquisa  heurística
// (pesquisa gulosa, A*), com diferentes funções heurísticas.
//0: Finish validMove(), checkGameOver(), movePiece()
//1: Start by making a Graph with nodes and edges
//2: apply algorithms like DFS and BFS
//3: apply the A* algorithm

public class Game {
    private static ArrayList<Level> levels;
    private static int currLevel;
    private static int NUM_LEVELS = 4;
    private static boolean DEBUG = false;

    Game() {
        levels = new ArrayList<>();
        currLevel = 1;
        levels.add(new Level());
        for(int i=1; i<=NUM_LEVELS; i++){
            levels.add(new Level("level" + i + ".txt"));
        }
    }

    private void changeDebugMode(){
        DEBUG = !DEBUG;
        if(DEBUG)
            System.out.println("Debug mode on.");
        else System.out.println("Debug mode off.");
    }

    public void start(){
        while(true) {
            Printer.headline();
            int option = Printer.mainMenu();

            if(option == 0) //sair do jogo
                return;

            if(option == 9) {
                changeDebugMode();
                continue;
            }

            currLevel = Printer.selectLevel(NUM_LEVELS);

            if(currLevel == 0) //sair do jogo
                return;

            levels.get(currLevel).reset();

            if(option==1)
                startGameHuman();
            else if (option < 5)
                startSolveSearch(option);
            else if (option < 7)
                startHeuristics(option);
            else System.out.println("Invalid input!");
        }
    }

    private void startHeuristics(int option) {

    }

    private void startSolveSearch(int option) {
        SolveSearch search = new SolveSearch(levels.get(currLevel));
        search.debbugMode(DEBUG);
        NewNode node;

        switch (option) {
            case 2:
                node = search.breadthFirstSearch();
                break;
            case 3:
                node = search.depthFirstSearch();
                break;
            case 4:
                node = search.iterativeDeepeningSearch();
                break;
            default:
                return;
        }

        if(levels.get(currLevel).isFinish())
            Printer.youWon(currLevel);
        Printer.solution(node);
    }

    private static void startGameHuman() {
        while(!levels.get(currLevel).isFinish()) {
            Printer.board(levels.get(currLevel));
            //Printer.compactBoard(levels.get(currLevel));
            levels.get(currLevel).selectPiece();
        }
        Printer.board((levels.get(currLevel)));
        Printer.youWon(currLevel);
    }
}
