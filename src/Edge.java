public class Edge {

    public Node start;
    public Node end;
    public char action;

    public Edge(Node start, Node end, char action) {
        this.start = start;
        this.end = end;
        this.action = action;
    }
}
