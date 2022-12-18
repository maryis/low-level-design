package onlinechess;


public abstract class Piece {
    Color color;

    public Piece(Color color) {
        this.color = color;
    }

    public abstract boolean isValidMove(Board board, Move move);
}
