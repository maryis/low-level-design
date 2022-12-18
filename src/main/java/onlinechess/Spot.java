package onlinechess;

public class Spot {
    Piece piece;
    int i;
    int j;

    public Spot(Piece piece, int i, int j) {
        this.piece = piece;
        this.i = i;
        this.j = j;
    }

    public int getDistanceX(Spot other) {
        return Math.abs(this.i - other.i);
    }

    public int getDistanceY(Spot other) {
        return Math.abs(this.j - other.j);
    }
}
