import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
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
    private static ArrayList<Level> levels;
    private static SolveSearch search;
    private static int currLevel;
    private static int NUM_LEVELS = 1;

    Game() {
        levels = new ArrayList<>();
        currLevel = 1;
        levels.add(new Level());
        for(int i=1; i<=NUM_LEVELS; i++){
            levels.add(new Level("level" + i + ".txt"));
        }
    }

    public void start(){
        while(true) {
            Printer.headline();
            int option = Printer.mainMenu();

            if(option == 0) //sair do jogo
                return;

            currLevel = Printer.selectLevel(NUM_LEVELS);

            if(currLevel == 0) //sair do jogo
                return;

            levels.get(currLevel).reset();
            switch (option) {
                case 1:
                    startGameHuman();
                    break;
                case 2:
                    startGameComputerBreadthFirst();
                    break;
                case 3:
                    startGameComputerDepthFirst();
                    break;
                case 4:
                    //startGameComputer();
                    break;
                case 5:
                    //startGameComputer();
                    break;
                case 6:
                    //startGameComputer();
                    break;
            }
        }
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



    private static void startGameComputerBreadthFirst(){
        search = new SolveSearch(levels.get(currLevel));
        search.debbugMode();
        NewNode node = search.breadthFirstSearch();
        Printer.solution(node);
    }

    private static void startGameComputerDepthFirst(){
        search = new SolveSearch(levels.get(currLevel));
        search.debbugMode();
        NewNode node = search.depthFirstSearch();
        Printer.solution(node);

    }

}
