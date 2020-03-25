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
            catch (EmptyStackException e){ // a fila chegou ao fim
                return new NewNodeNull(level);
            }
        }

    }

    public NewNode iterativeDeepeningSearch() {
        Stack<NewNode> nodesWaiting;  //stack for LIFO (Last In First Out)
        NewNode root = new NewNode(level); //o root é sempre o mesmo
        int maxDepthCounter = 1;
        int currDephtCounter;

        while(true) {
            nodesWaiting = new Stack<>();
            Level.Direction direction = Level.Direction.NULL;
            nodesWaiting.push(root);

            if (level.isFinish()) {
                return root;
            }

            while(true) { //profundidade iterativa (em vez de ciclo while nas funcoes anteriores)
                try {
                    NewNode dad = nodesWaiting.pop();
                    ArrayList<Piece> pieces = dad.getState().getAllPieces();
                    currDephtCounter = dad.getDepth()+1;
                    System.out.println("curr depth = " + currDephtCounter);

                    for (Piece piece : pieces) { //percorre todas as pecas
                        for (int j = 0; j < 4; j++) { // percorre as 4 direcoes possiveis
                            NewNode node = new NewNode(dad, piece, direction = changeDirection(direction), 1);
                            if (this.debug)
                                Printer.nodeInfo(node);
                            if (node.getState().isFinish()) //solucao encontrada
                                return node;
                            if(currDephtCounter<maxDepthCounter) //apenas adiciona o nó a stack se nao estiver no limite de profundidade
                                nodesWaiting.push(node);
                        }
                    }

                    System.out.println("nodes wating:");
                    for(NewNode node : nodesWaiting)
                        System.out.println(node);
                    System.out.println();

                } catch (EmptyStackException e) { // a stack chegou ao fim
                    break;
                }
            }

            maxDepthCounter++; //aumenta a profundidade da proxima tentativa

            if(maxDepthCounter > level.getNumPieces()) //nao existe solucao
                return new NewNodeNull(level);

        }

    }
}
