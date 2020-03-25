import java.util.ArrayList;
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
            Node childNode = new Node(pieces.get(k));
            Edge e = new Edge(rootNode,childNode,'n'); //null action
            rootNode.addEdge(e);
            childNode.addToSequence(pieces.get(k));
            addNode(childNode);
        }

        addNode(rootNode);

        List<Node> e = this.getLeafs();

        boolean stop = true;

        while(stop) {

            //stop condition here?

            List<Node> leafs = this.getLeafs();

            for(int i = 0; i < leafs.size(); i++) {
                List<Piece> pieceSequence = leafs.get(i).getSeqChosenPieces();
                ArrayList<Piece> possibleOutcomes = Utils.getPossibleOutcomes(pieces,pieceSequence);

                if(possibleOutcomes.isEmpty()) stop = false;

                for(int j=0; j < possibleOutcomes.size(); j++) {
                    Node newChildNode1 = new Node(possibleOutcomes.get(j));
                    Node newChildNode2 = new Node(possibleOutcomes.get(j));
                    Node newChildNode3 = new Node(possibleOutcomes.get(j));
                    Node newChildNode4 = new Node(possibleOutcomes.get(j));

                    Edge e1 = new Edge(leafs.get(i),newChildNode1,'U');
                    Edge e2 = new Edge(leafs.get(i),newChildNode2,'D');
                    Edge e3 = new Edge(leafs.get(i),newChildNode3,'L');
                    Edge e4 = new Edge(leafs.get(i),newChildNode4,'R');

                    leafs.get(i).addEdge(e1);
                    leafs.get(i).addEdge(e2);
                    leafs.get(i).addEdge(e3);
                    leafs.get(i).addEdge(e4);

                    addNode(newChildNode1);
                    addNode(newChildNode2);
                    addNode(newChildNode3);
                    addNode(newChildNode4);
                }
            }
        }
        for(int t = 0; t < nodes.size(); t++) {
            getNode(t).printInfo();
        }
    }

    public void addNode(Node newNode){
        nodes.add(newNode);
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public Node getNode(int iterator){
        return nodes.get(iterator);
    }

    public List<Node> getLeafs() {
        List<Node> toReturn = new ArrayList<>();
        for(Node n: this.nodes) {
            if(n.getChildren().size() == 0) toReturn.add(n);
        }
        return toReturn;
    }

    public int getNumVertex(){
        return nodes.size();
    }
}
