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
        int numVertex = 1;

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


        //now for each different piece generate all other outcomes depending on the selected piece
        for(int i = 0; i < rootNode.getChildren().size(); i++) {
            Node childNode = rootNode.getChildren().get(i).getChild();

            List<Piece> pieceSequence = childNode.getSeqChosenPieces();

            ArrayList<Piece> possibleOutcomes = Utils.getPossibleOutcomes(pieces,pieceSequence); //returns list of pieces there are yet to be selected

            //now for each outcome generate 4 actions
            for(int j = 0; j < possibleOutcomes.size();j++) {
                Node newChildNode1 = new Node(possibleOutcomes.get(j));
                Node newChildNode2 = new Node(possibleOutcomes.get(j));
                Node newChildNode3 = new Node(possibleOutcomes.get(j));
                Node newChildNode4 = new Node(possibleOutcomes.get(j));

                Edge e1 = new Edge(childNode,newChildNode1,'U');
                Edge e2 = new Edge(childNode,newChildNode2,'D');
                Edge e3 = new Edge(childNode,newChildNode3,'L');
                Edge e4 = new Edge(childNode,newChildNode4,'R');

                childNode.addEdge(e1);
                childNode.addEdge(e2);
                childNode.addEdge(e3);
                childNode.addEdge(e4);

                addNode(newChildNode1);
                addNode(newChildNode2);
                addNode(newChildNode3);
                addNode(newChildNode4);

            }

        }



        for(int t = 0; t < nodes.size(); t++) {
            getNode(t).printInfo();
        }
/*
        //init nodes
        for(int j = 1; j < numVertex; j = j+5) { //i think the leaves have children too
            Node newParentNode = new Node(j);

            Node newChildNodeUp = new Node(j+1);
            Node newChildNodeDown = new Node(j+2);
            Node newChildNodeLeft = new Node(j+3);
            Node newChildNodeRight = new Node(j+4);

            Edge e1 = new Edge(newParentNode,newChildNodeUp,'U');
            Edge e2 = new Edge(newParentNode,newChildNodeDown,'D');
            Edge e3 = new Edge(newParentNode,newChildNodeLeft,'L');
            Edge e4 = new Edge(newParentNode,newChildNodeRight,'R');

            graph.addNode(newParentNode);
            graph.addNode(newChildNodeDown);
            graph.addNode(newChildNodeLeft);
            graph.addNode(newChildNodeRight);
            graph.addNode(newChildNodeUp);

            //TODO: set adj vertexes

            newParentNode.addEdge(e1);
            newParentNode.addEdge(e2);
            newParentNode.addEdge(e3);
            newParentNode.addEdge(e4);
        }





        for(int t = 0; t < graph.getNodes().size(); t++) {
            graph.getNode(t).printInfo();
        }
        */
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

    public int getNumVertex(){
        return nodes.size();
    }
}
