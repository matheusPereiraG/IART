import java.util.ArrayList;
import java.util.List;

public class Graph {
    List<Node> nodes;

    public Graph(List<Node> nodes) {
        this.nodes = nodes;
    }

    public Graph(){
        this.nodes = new ArrayList<>();
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
