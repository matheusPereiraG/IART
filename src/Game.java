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
    private static boolean end;

    Game() {
        levels = new ArrayList<>();
        currLevel = 1;
        levels.add(new Level());
        for(int i=1; i<=NUM_LEVELS; i++){
            levels.add(new Level("level" + i + ".txt"));
        }
        end = false;
    }

    public void start(){


        //tests
        graph = new Graph(levels.get(currLevel));
        graph.initGraph();

        /*
        while(!end) {
            Printer.headline();
            int option = Printer.mainMenu();
            currLevel = Printer.selectLevel(NUM_LEVELS);

            //sair do jogo
            if(option == 0 || currLevel == 0){
                end = true;
                break;
            }


            switch (option) {
                case 1:
                    startGameHuman();
                    break;
                case 2:
                    startGameComputer();
                    break;
                case 3:
                    startGameComputer();
                    break;
                case 4:
                    startGameComputer();
                    break;
                case 5:
                    startGameComputer();
                    break;
                case 6:
                    startGameComputer();
                    break;
            }
        }
        */

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



    private static void startGameComputer(){
        search = new SolveSearch(levels.get(currLevel));
        NewNode node = search.breadthFirstSearch();

        Printer.solution(node);
    }

}
