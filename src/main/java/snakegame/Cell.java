package snakegame;

public class Cell {
    private int x;
    private int y;
    private CellType cellType;

    public Cell(int i, int j, CellType cellType) {
        this.x = i;
        this.y = j;
        this.cellType = cellType;
    }

    public void setCellType(CellType cellType) {
        this.cellType = cellType;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public CellType getCellType() {
        return cellType;
    }
}
