import java.util.Objects;

public class Piece {

    private int x;
    private int y;
    private int numSteps;

    public Piece(int x, int y, int numSteps) {
        this.x = x;
        this.y = y;
        this.numSteps = numSteps;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getNumSteps() {
        return numSteps;
    }

    public void setNumSteps(int numSteps) {
        this.numSteps = numSteps;
    }

    @Override
    public String toString() {
        return "Piece{" +
                "x=" + x +
                ", y=" + y +
                ", numSteps=" + numSteps +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Piece piece = (Piece) o;
        return x == piece.x &&
                y == piece.y;
    }
}
