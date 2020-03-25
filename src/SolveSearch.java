import java.util.*;

public class SolveSearch {
    Level level;
    private boolean debug;

    SolveSearch(Level level) {
        this.level = level;
        this.debug = false;
    }

    public void debbugMode(){
        this.debug = true;
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

    public NewNode breadthFirstSearch() {
        Queue<NewNode> nodesWaiting = new LinkedList<>(); //queue for FIFO (First In First Out)
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
                        nodesWaiting.add(node);
                    }
                }
            }
            catch (NoSuchElementException e){ // a fila chegou ao fim
                return new NewNodeNull(level);
            }
        }
    }


    public NewNode depthFirstSearch() {
        Stack<NewNode> nodesWaiting = new Stack<>(); //stack for LIFO (Last In First Out)
        NewNode root = new NewNode(level);
        Level.Direction direction = Level.Direction.NULL;
        nodesWaiting.push(root);

        if (level.isFinish()){
            return root;
        }

        while(true){
            try {
                NewNode dad = nodesWaiting.pop();

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
                        nodesWaiting.push(node);
                    }
                }
            }
            catch (NoSuchElementException e){ // a fila chegou ao fim
                return new NewNodeNull(level);
            }
        }

    }
}
