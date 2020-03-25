import java.util.ArrayList;
import java.util.List;

public class Node {
    public Piece chosenPiece;
    public List<Edge> children;

    public ArrayList<ArrayList<String>> stateOfTheBoard;
    public ArrayList<Piece> seqChosenPieces; //sequence of chosen pieces till this node


    public Node(Piece p) {
        this.chosenPiece = p;
        children = new ArrayList<>();
        seqChosenPieces = new ArrayList<>();
    }

    public Node(){
        this.chosenPiece = null;
        children = new ArrayList<>();
        seqChosenPieces = new ArrayList<>();
    }

    public void addEdge(Edge newEdge){
        children.add(newEdge);
    }

    public void setSeqChosenPieces(ArrayList<Piece> seqChosenPieces) {
        this.seqChosenPieces = seqChosenPieces;
    }

    public Piece getChosenPiece() {
        return chosenPiece;
    }

    public List<Edge> getChildren() {
        return children;
    }

    public ArrayList<Piece> getSeqChosenPieces() {
        return seqChosenPieces;
    }

    public void addToSequence(Piece p){
        if(this.seqChosenPieces == null) {
            this.seqChosenPieces = new ArrayList<>();
            this.seqChosenPieces.add(p);
        }
        else this.seqChosenPieces.add(p);
    }

    public ArrayList<ArrayList<String>> getStateOfTheBoard() {
        return stateOfTheBoard;
    }

    public void printInfo(){
        System.out.println();
        if(this.chosenPiece != null)
                System.out.println("Node Piece: " + this.chosenPiece.toString());
        else System.out.println("ROOT NODE");

        if(!children.isEmpty()) {
            System.out.println("Children: ");
            for(Edge e: this.children){
                System.out.println("Child Node Piece: " + e.getChild().getChosenPiece().toString());
                System.out.println("Edge Action: " + e.getAction());
            }
        }
        else System.out.print("LEAF NODE");
        System.out.println();

    }
}
