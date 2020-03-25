import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Node {
    private int NodeID;
    private static int count = 0;
    private List<Edge> children;
    private Piece chosenPiece;

    private ArrayList<ArrayList<String>> stateOfTheBoard;
    private HashSet<Piece> seqChosenPieces; //sequence of chosen pieces including the piece of this node


    public Node() {
        this.NodeID = ++count;
        this.children = new ArrayList<>();
        this.seqChosenPieces = new HashSet<>();
        this.seqChosenPieces.clear();

    }

    public void addEdge(Edge newEdge) {
        this.children.add(newEdge);
    }


    public List<Edge> getChildren() {
        return this.children;
    }

    public HashSet<Piece> getSeqChosenPieces() {
        return this.seqChosenPieces;
    }

    public void addToSequence(HashSet<Piece> parentList) {
        HashSet<Piece> aux = parentList;
        if(parentList.isEmpty()) this.seqChosenPieces.add(this.chosenPiece);
        else {
            aux.add(this.chosenPiece);
            this.setSeqChosenPieces(aux);
        }
    }

    private void setSeqChosenPieces(HashSet<Piece> aux) {
        this.seqChosenPieces.clear();
        this.seqChosenPieces = aux;
    }


    public ArrayList<ArrayList<String>> getStateOfTheBoard() {
        return stateOfTheBoard;
    }

    public void printInfo() {
        System.out.println();
        if (this.NodeID != 1)
            System.out.println("Node Piece: " + this.getChosenPiece().toString());
        else System.out.println("ROOT NODE");

        if (!children.isEmpty()) {
            System.out.println("Children: ");
            for (Edge e : this.children) {
                System.out.println("Child Node Piece: " + e.getChild().getChosenPiece().toString());
                System.out.println("Edge Action: " + e.getAction());
            }
        } else System.out.print("LEAF NODE");
        System.out.println();

    }

    public void printSequence(){
        for(Piece p: this.seqChosenPieces)
            System.out.println(p.toString());
        System.out.println();
    }
    public void setChosenPiece(Piece p){
        this.chosenPiece = p;
    }

    public Piece getChosenPiece() {
        return this.chosenPiece;
    }

    public int getNodeID() {
        return this.NodeID;
    }
}
