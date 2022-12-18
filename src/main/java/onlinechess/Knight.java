package onlinechess;

public class Knight extends Piece {
    public Knight(Color color) {
        super(color);
    }

    @Override
    public boolean isValidMove(Board board, Move move) {
        Spot start = board.getSpot(move.i, move.j);
        Spot end = board.getSpot(move.i1, move.j1);
        if(start.getDistanceX(end)>1 || start.getDistanceY(end)>1)
            return false;
        return true;
    }
}
