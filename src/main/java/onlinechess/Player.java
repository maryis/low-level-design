package onlinechess;

public abstract class Player {
    String name;
    Color color;

    public Player(String name, Color color) {
        this.name = name;
        this.color = color;
    }

    public void move(Board board, Move move) {
        if(move.isValid(board)){
            //apply move on board
        }
    }

    public abstract Move nextMove(Board board);
}
