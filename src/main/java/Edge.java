import java.util.ArrayList;

public class Edge {

    public Node parent;
    public Node child;
    public char action;

    public Edge(Node parent, Node child, char action) {
        this.parent = parent;
        this.child = child;
        this.action = action;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public Node getChild() {
        return child;
    }

    public void setChild(Node child) {
        this.child = child;
    }

    public char getAction() {
        return action;
    }

    public void setAction(char action) {
        this.action = action;
    }
}
