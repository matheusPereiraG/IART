import java.util.Comparator;

public class NewNode {

    private boolean root;
    private Piece lastPiece;
    private Level state;
    private NewNode dad;
    private Level.Direction lastOperator;
    private int depth;
    private int cost;
    private static int nodeCounter;
    private double distanceToSolution;

    NewNode(Level state) {
        try {
            this.state = (Level) state.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        this.root = true;
        NewNode.nodeCounter = 0;
    }

    NewNode(NewNode dad, Piece piece, Level.Direction operator, int cost) {
        this.root = false;
        this.dad = dad;
        this.lastPiece = piece;
        this.lastOperator = operator;
        this.depth = dad.getDepth() + 1;
        this.cost = dad.getCost() + cost;
        NewNode.nodeCounter++;
        try {
            this.state = (Level) dad.getState().clone();
            this.distanceToSolution = this.state.getDistanceToSol(piece);
            this.state.expandPiece(piece.getPos(), operator);
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

    public int getNumberNodes() {
        return NewNode.nodeCounter;
    }

    public double getDistanceToSol() {
        return this.distanceToSolution;
    }

    public static Comparator<NewNode> distanceComparator = new Comparator<NewNode>() {
        @Override

        public int compare(NewNode n1, NewNode n2) {
            return (int)n2.getDistanceToSol() - (int)n1.getDistanceToSol();
        }

    };
	public static Comparator<NewNode> depthComparator = new Comparator<NewNode>() {
        @Override
        public int compare(NewNode n1, NewNode n2) {
            return n2.getDepth() - n1.getDepth();
        }
    };

}
