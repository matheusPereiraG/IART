import java.util.Comparator;

public class NewNode {

    private boolean root;
    private Piece lastPiece;
    private Level state;
    private NewNode dad;
    private Level.Direction lastOperator;
    private int depth;
    private static int nodeCounter;

    /* Heuristics */
    private int cost;
    private int priority;


    NewNode(Level state) {
        try {
            this.state = (Level) state.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        this.root = true;
        NewNode.nodeCounter = 0;
        this.cost = 0;
        this.depth = 0;
    }

    NewNode(NewNode dad, Piece piece, Level.Direction operator) { //construtor para pesquica cega
        this.root = false;
        this.dad = dad;
        this.lastPiece = piece;
        this.lastOperator = operator;
        this.depth = dad.getDepth() + 1;
        this.cost = dad.getCost() + 1;
        NewNode.nodeCounter++;
        try {
            this.state = (Level) dad.getState().clone();
            this.state.expandPiece(piece.getPos(), operator);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }

    public static int getNodeCounter(){
        return nodeCounter;
    }

    public NewNode(NewNode dad, Piece piece, Level.Direction operator, int factor) { //constructor para heuristicas
        this.root = false;
        this.dad = dad;
        this.lastPiece = piece;
        this.lastOperator = operator;
        this.depth = dad.getDepth() + 1;
        this.cost = dad.getCost() + 1;
        NewNode.nodeCounter++;
        try {
            this.state = (Level) dad.getState().clone();

            if(factor == 0){  //no algoritmo ganancioso: priority = numCelulasExpandidas - valorPeça
                this.priority = this.state.expandPiece(piece.getPos(), operator) - lastPiece.getNumSteps();
            }
            else{     // no algoritmo A*: priority = (numCelulasExpand - valorPeça) * factor - custo
                this.priority = (this.state.expandPiece(piece.getPos(), operator) - lastPiece.getNumSteps()) * factor - this.cost;
            }

        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
	}

	public Level getState() {
        return state;
    }

    public int getCost() {
        return cost;
    }

    public int getDepth() {
        return depth;
    }

    public NewNode getDad() {
        return dad;
    }

    public Piece getLastPiece() {
        return lastPiece;
    }

    public Level.Direction getLastOperator() {
        return lastOperator;
    }

    public boolean isRoot() {
        return root;
    }

    public static Comparator<NewNode> priorityComparator = (n1, n2) -> n2.getPriority() - n1.getPriority();

    public int getPriority() {
        return priority;
    }
}
