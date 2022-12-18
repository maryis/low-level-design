package onlinechess;

public class Move {
    Spot start;
    Spot end;

    public Move(Spot start, Spot end) {
        this.start = start;
        this.end = end;
    }

    public boolean isValid(Board board) {
        Piece piece = this.start.piece;
        if(piece.isValidMove(board, this))
            return true;
        return false;
    }
}
