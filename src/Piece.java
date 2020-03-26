import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

public class Piece implements Cloneable{

    private Position pos;
    private int numSteps;

    public Piece(int x, int y, int numSteps) {
        this.pos = new Position(x,y);
        this.numSteps = numSteps;
    }

    public Position getPos() {
        return pos;
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
                "x=" + pos.getX() + "," +
                "y=" + pos.getY() + "," +
                "numSteps=" + numSteps +
                "}";
    }

    @Override
    public Object clone() throws CloneNotSupportedException{
        Piece p = (Piece)super.clone();

        p.pos = getPos();

        return p;

    }
}
