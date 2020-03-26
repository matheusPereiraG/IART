public class NewNode {

    private boolean root;
    private Piece lastPiece;
    private Level state;
    private NewNode dad;
    private Level.Direction lastOperator;
    private int depth;
    private int cost;

    NewNode(Level state) {
        try {
            this.state = (Level)state.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        this.root = true;
    }

    NewNode(NewNode dad, Piece piece, Level.Direction operator, int cost) {
        this.root = false;
        this.dad = dad;
        this.lastPiece = piece;
        this.lastOperator = operator;
        this.depth = dad.getDepth()+1;
        this.cost = dad.getCost()+cost;
        try {
            this.state = (Level)dad.getState().clone();
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
}
