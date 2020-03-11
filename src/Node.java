import java.util.ArrayList;
import java.util.List;

public class Node {
    public int id;
    public List<Edge> children;

    public ArrayList<ArrayList<String>> stateOfTheBoard;
    //need to add sequence of actions?


    public Node(int id) {
        this.id = id;
        children = new ArrayList<>();
    }

    public void addEdge(Edge newEdge){
        children.add(newEdge);
    }

    public int getId() {
        return id;
    }

    public List<Edge> getChildren() {
        return children;
    }

    public ArrayList<ArrayList<String>> getStateOfTheBoard() {
        return stateOfTheBoard;
    }

    public void printInfo(){
        System.out.println("Node ID: " + this.id);

        if(!children.isEmpty()) {
            System.out.println("Children: ");
            for(Edge e: this.children){
                System.out.println("Child Node ID: " + e.getChild().getId());
                System.out.println("Edge Action: " + e.getAction());
            }
        }

    }
}
