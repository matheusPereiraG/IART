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

            //this.distanceToSolution = this.state.getDistanceToSol(piece);

            //////
            this.state.expandPiece(piece.getPos(), operator);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }

    public static int getNodeCounter(){
        return nodeCounter;
    }

    public NewNode(NewNode dad, Piece piece, Level.Direction operator, int cost) { //constructor para heuristicas
        this.root = false;
        this.dad = dad;
        this.lastPiece = piece;
        this.lastOperator = operator;
        this.depth = dad.getDepth() + 1;
        this.cost = dad.getCost() + cost;
        NewNode.nodeCounter++;
        try {
            this.state = (Level) dad.getState().clone();
            //this.distanceToSolution = this.state.getDistanceToSol(piece);


            this.state.expandPiece(piece.getPos(), operator);

        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        this.priority = this.calculatePriority();
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

    /*
    public int calculateCost() throws CloneNotSupportedException {
        return this.state.getInteractingPieces();
    }
    */


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

    public static Comparator<NewNode> priorityComparator = new Comparator<NewNode>() {
        @Override

        public int compare(NewNode n1, NewNode n2) {
            return n2.getPriority() - n1.getPriority();
        }

    };


    public int calculatePriority() {
        int priority = 0;
        Level l;

        Level.Direction dir = Level.Direction.NULL;

        for(Piece p: this.state.getAllPieces()) {           //para cada uma das peças que faltam expandir neste nó
            for(int i=0; i < 4 ; i++){                      //vamos ver se ajudamos a que elas cheguem "mais longe"
                try {
                    l = (Level) this.state.clone();         //criamos um level temporario
                } catch (CloneNotSupportedException e) {
                    e.printStackTrace();
                    return 0;
                }

                // expandimos esse level para cada uma das peças que faltam,
                // nas 4 direções, e comparamos o número de células expandidas
                // com o valor da peça
                int numberCellsExpanded = l.expandPiece(p.getPos(), dir = Level.changeDirection(dir));
                int pieceValue = p.getNumSteps();
                if(numberCellsExpanded > pieceValue){
                    priority += numberCellsExpanded - pieceValue;                             //se esta condição for verdade, significa que neste nó expandimos uma peça numa boa direção!
                }
            }
        }

        /*
          priority = 0 => nó desprezável
          priority > 0 => bom nó
         */

        return priority;
    }

    public int getPriority() {
        return priority;
    }
}
