package onlinechess;

public class Board {
    Spot[][] spots;
    Status status;

    public Board() {
        init();
        status = Status.PLAYING;
    }

    private void init() {
        spots = new Spot[8][8];
        for(int j=0; j<8; j++) {
            spots[1][j] = new Spot(new Knight(Color.WHITE), 1, j);
            spots[6][j] = new Spot(new Knight(Color.BLACK), 6, j);
        }
        for(int i=2; i<6; i++)
            for(int j=0; j<8; j++)
                spots[i][j] = new Spot(null, i, j);
        // and else
    }

    public Spot getSpot(int i, int j) {
        return spots[i][j];
    }

    public Status calculateState() {
        return Status.PLAYING;
    }
}
