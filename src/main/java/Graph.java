import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Graph {
    Level level;
    List<Node> nodes;
    //private Map<Node, List<Node>> adjVertices;

    public Graph(Level level, List<Node> nodes) {
        this.level = level;
        this.nodes = nodes;
    }

    public Graph(Level level){
        this.level = level;
        this.nodes = new ArrayList<>();
    }

    public void initGraph(){
        ArrayList<Piece> pieces = level.getAllPieces();

        //init root node
        Node rootNode = new Node();

        for(int k= 0; k < pieces.size(); k++) {
            Node childNode = new Node();
            Edge e = new Edge(rootNode,childNode,'n'); //null action
            childNode.setChosenPiece(pieces.get(k));
            HashSet<Piece> rootList = rootNode.getSeqChosenPieces();
            childNode.addToSequence(rootList);
            rootNode.addEdge(e);
            addNode(childNode);
        }

        addNode(rootNode);

        boolean stop = true;
        int graphDepth = pieces.size();
        int counter = 0;

        while(stop) {

            //stop condition here?
            counter++;
            if(counter == graphDepth) stop = false;


            List<Node> leafs = this.getLeafs();
            System.out.println(leafs.size());
            //for(Node n: leafs) n.printSequence();

            for(int i = 0; i < leafs.size(); i++) {
                HashSet<Piece> pieceSequence = leafs.get(i).getSeqChosenPieces();
                ArrayList<Piece> possibleOutcomes = Utils.getPossibleOutcomes(pieces,pieceSequence);

                System.out.println("SEQUENCE: ");
                for(Piece c: pieceSequence) System.out.println(c.toString());
                System.out.println("OUTCOMES:");
                for(Piece p: possibleOutcomes) System.out.println(p.toString());


                for(int j=0; j < possibleOutcomes.size(); j++) {
                    Piece chosenPiece = possibleOutcomes.get(j);
                    Node newChildNode1 = new Node();
                    Node newChildNode2 = new Node();
                    Node newChildNode3 = new Node();
                    Node newChildNode4 = new Node();

                    newChildNode1.setChosenPiece(chosenPiece);
                    newChildNode2.setChosenPiece(chosenPiece);
                    newChildNode3.setChosenPiece(chosenPiece);
                    newChildNode4.setChosenPiece(chosenPiece);

                    Edge e1 = new Edge(leafs.get(i),newChildNode1,'U');
                    Edge e2 = new Edge(leafs.get(i),newChildNode2,'D');
                    Edge e3 = new Edge(leafs.get(i),newChildNode3,'L');
                    Edge e4 = new Edge(leafs.get(i),newChildNode4,'R');

                    newChildNode1.addToSequence(pieceSequence);
                    newChildNode2.addToSequence(pieceSequence);
                    newChildNode3.addToSequence(pieceSequence);
                    newChildNode4.addToSequence(pieceSequence);

                    leafs.get(i).addEdge(e1);
                    leafs.get(i).addEdge(e2);
                    leafs.get(i).addEdge(e3);
                    leafs.get(i).addEdge(e4);

                    //add new children
                    addNode(newChildNode1);
                    addNode(newChildNode2);
                    addNode(newChildNode3);
                    addNode(newChildNode4);

                    //finally update parent node in the array
                    this.setNode(leafs.get(i));
                }
            }


        }
        for(int t = 0; t < nodes.size(); t++) {
            this.nodes.get(t).printInfo();
        }
    }

    public void addNode(Node newNode){
        nodes.add(newNode);
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public void setNode(Node n){
        for(int i = 0; i < this.nodes.size(); i++) {
            if(this.nodes.get(i).getNodeID() == n.getNodeID())
                this.nodes.set(i,n);
        }
    }

    public List<Node> getLeafs() {
        List<Node> toReturn = new ArrayList<>();
        for(Node n: this.nodes) {
            if(n.getChildren().isEmpty()) toReturn.add(n);
        }
        return toReturn;
    }

    public int getNumVertex(){
        return nodes.size();
    }
}
