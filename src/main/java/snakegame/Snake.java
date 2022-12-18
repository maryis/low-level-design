package snakegame;

import java.util.LinkedList;

public class Snake {
    private Board board;
    private LinkedList<Cell> body;

    public Snake(Board board) {
        this.board = board;
        this.body = new LinkedList<>();
        Cell head = board.createSnake();
        body.add(head);
    }

    public Status move(Direction dir) {
        Status result = null;
        Cell next = board.getNext(body.getFirst(), dir);
        if(next.getCellType() == CellType.SNAKE)
            result = Status.CRASH;
        else if(next.getCellType() == CellType.EMPTY) {
            body.getFirst().setCellType(CellType.EMPTY);
            result = Status.SAME;
        } else {
            result = Status.BIGGER;
        }
        next.setCellType(CellType.SNAKE);
        return result;
    }
}
