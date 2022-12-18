package onlinechess;

import java.util.Random;

public class Computer extends Player {
    Random random;

    public Computer(String name, Color color) {
        super(name, color);
        random = new Random();
    }

    @Override
    public Move nextMove(Board board) {
        int i = random.nextInt(8);
        int i1 = random.nextInt(8);
        int j = random.nextInt(8);
        int j1 = random.nextInt(8);
        return new Move(board.getSpot(i, j), board.getSpot(i1, j1));
    }
}
