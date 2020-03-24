public class Position {
    int x;
    int y;

    Position(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position pos = (Position) o;
        return x == pos.x &&
                y == pos.y;
    }
}
