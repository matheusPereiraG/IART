import java.util.ArrayList;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;

public class SolveSearch {
    Level level;
    Queue<NewNode> nodesWaiting;
    private boolean debug;

    SolveSearch(Level level) {
        this.level = level;
        nodesWaiting = new LinkedList<>();
        this.debug = false;
    }

    public void debugMode(){
        this.debug = true;
    }

    public NewNode breadthFirstSearch() {
        NewNode root = new NewNode(level);
        Level.Direction direction = Level.Direction.NULL;
        nodesWaiting.add(root);

        if (level.isFinish()){
            return root;
        }

        while(true){
            try {
                NewNode dad = nodesWaiting.remove();

                ArrayList<Piece> pieces = dad.getState().getAllPieces();


                for (Piece piece : pieces) { //percorre todas as pecas
                    for (int j = 0; j < 4; j++) { // percorre as 4 direcoes possiveis
                        NewNode node = new NewNode(dad, piece, direction = changeDirection(direction), 1);
                        if(this.debug) {
                            Printer.nodeInfo(node);
                        }
                        if (node.getState().isFinish()) {
                            return node;
                        }
                        //System.out.println(node.getDad());
                        nodesWaiting.add(node);
                    }
                }
            }
            catch (NoSuchElementException e){ // a fila chegou ao fim
                return null;
            }
        }
    }

    private Level.Direction changeDirection(Level.Direction dir){
        switch (dir){
            case NULL:
            case RIGHT:
                return Level.Direction.UP;
            case UP:
                return Level.Direction.DOWN;
            case DOWN:
                return Level.Direction.LEFT;
            case LEFT:
                return Level.Direction.RIGHT;
        }
        return Level.Direction.NULL;
    }


}
