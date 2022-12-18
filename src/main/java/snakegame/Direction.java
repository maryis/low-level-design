package snakegame;

public enum Direction {
    UP(-1,0),
    DOWN(1,0),
    WEST(0,1),
    EAST(0,-1);

    int x;
    int y;

    private Direction(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public static Direction getDir(int x, int y) {
        if (x == -1 && y == 0)
            return UP;
        ////
        return EAST;
    }
}
