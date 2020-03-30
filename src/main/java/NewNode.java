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
    private int cost; //number of interacting pieces, in this case follows the path with greater cost
    private double distanceToSolution;
    private int cellsExpanded; //number of cells expanded in the direction


    NewNode(Level state) {
        try {
            this.state = (Level) state.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        this.root = true;
        NewNode.nodeCounter = 0;
        this.cost = 0;
    }

    NewNode(NewNode dad, Piece piece, Level.Direction operator) {
        this.root = false;
        this.dad = dad;
        this.lastPiece = piece;
        this.lastOperator = operator;
        this.depth = dad.getDepth() + 1;
        NewNode.nodeCounter++;
        try {
            this.state = (Level) dad.getState().clone();
            this.distanceToSolution = this.state.getDistanceToSol(piece);
            this.cellsExpanded= this.state.expandPiece(piece.getPos(), operator);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }

    public NewNode(NewNode dad, Piece piece, Level.Direction operator, Object object) { //constructor para A* (poupa recursos nos outros algoritmos já que nao necessitam de calcular custo) 
        this.root = false;
        this.dad = dad;
        this.lastPiece = piece;
        this.lastOperator = operator;
        this.depth = dad.getDepth() + 1;
        NewNode.nodeCounter++;
        try {
            this.state = (Level) dad.getState().clone();
            this.distanceToSolution = this.state.getDistanceToSol(piece);
            this.cellsExpanded= this.state.expandPiece(piece.getPos(), operator);
            this.cost = dad.getCost() + this.calculateCost();
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

    public int getExpandedCells(){
        return this.cellsExpanded;
    }

    public int getNumberNodes() {
        return NewNode.nodeCounter;
    }

    public double getDistanceToSol() {
        return this.distanceToSolution;
    }

    public int calculateCost() throws CloneNotSupportedException {
        return this.state.getInteractingPieces();
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

    public static Comparator<NewNode> expandComparator = new Comparator<NewNode>() {
        @Override

        public int compare(NewNode n1, NewNode n2) {
            return n2.getExpandedCells() - n1.getExpandedCells();
        }

    };

    public static Comparator<NewNode> costComparator = new Comparator<NewNode>() {
        @Override

        public int compare(NewNode n1, NewNode n2) {
            return n2.getCost() - n1.getCost();
        }

    };

}
