package onlinechess;

public class King extends Piece {

    public King(Color color) {
        super(color);
    }

    @Override
    public boolean isValidMove(Board board, Move move) {
        return false;
    }
}
