import java.util.ArrayList;
import java.util.List;

public class Node {
    public int id;
    public List<Edge> connections;
    public ArrayList<ArrayList<String>> stateOfTheBoard;
    //need to add sequence of actions?


    public Node(int id) {
        this.id = id;
    }

    public void addConnection(Edge newEdge){
        connections.add(newEdge);
    }
}
