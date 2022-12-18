package snakegame;

import java.util.Random;

public class Board {
    private Cell[][] cells;
    private int width;
    private int height;
    private boolean generateFood;
    private static final int INTERVAL_MS = 200;
    private Random random;

    public Board(int n, int m) {
        this.width = n;
        this.height = m;
        this.generateFood = false;
        this.random = new Random();
        this.cells =new Cell[n][m];
        init();
    }

    public void setGenerateFood(boolean generateFood) {
        this.generateFood = generateFood;
    }

    public void init() {
        for(int i = 0; i<width; i++)
            for (int j=0; j<height; j++)
                cells[i][j] = new Cell(i, j, CellType.EMPTY);
    }

    public Cell createSnake() {
        int i = random.nextInt(width);
        int j = random.nextInt(height);
        cells[i][j].setCellType(CellType.SNAKE);
        return cells[i][j];
    }

    public void start() {
        this.generateFood = true;
        generateFood();
    }

    public void stop() {
        this.generateFood = false;
    }

    private void generateFood() {
        int i, j;
        while (generateFood) {
            do {
                i = random.nextInt(width);
                j = random.nextInt(height);
            } while (cells[i][j].getCellType() != CellType.EMPTY);
            cells[i][j].setCellType(CellType.FOOD);
            try {
                wait(INTERVAL_MS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public Cell getNext(Cell cell, Direction dir) {
        int i = cell.getX() + dir.getX();
        int j = cell.getY() + dir.getY();
        if(!isValidCell(i, j))
            throw new InvalidMoveException("Not valid move");
        return cells[i][j];
    }

    private boolean isValidCell(int i, int j) {
        return !(i<0 || j<0 || i>=width || j>=height);
    }
}
