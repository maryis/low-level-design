package snakegame;

import java.util.Objects;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

public class Game {
    private Board board;
    private Snake snake;
    private ExecutorService executorService;
    private AtomicBoolean paused;
    private Random random;
    private GameStatus status;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return snake.equals(game.snake) &&
                paused.equals(game.paused) &&
                status == game.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(snake, paused, status);
    }

    public Game(int w, int h) {
        this.board = new Board(w, h);
        init();
    }

    private void init() {
        board.init();
        snake = new Snake(board);
        paused = new AtomicBoolean(false);
    }

    public void startGame() {
        executorService = Executors.newFixedThreadPool(1);
        status = GameStatus.PLAYING;
        board.start();
        executorService.submit(() -> {
            start();
        });
    }

    private void start() {
        synchronized (this) {
            while (paused.get()) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        Direction dir = getDirection();
        Status moveState = snake.move(dir);
        if (moveState == Status.CRASH) {
            stopGame();
            status = GameStatus.CRASHED;
        }
    }

    private void stopGame() {
        executorService.shutdown();
        status = GameStatus.STOPPED;
        paused.getAndSet(true);
        board.stop();
    }

    private Direction getDirection() {
        //get from input instead
        int i = random.nextInt(1);
        int j = random.nextInt(1);
        return Direction.getDir(i, j);
    }

    public GameStatus getUpdate() {
        return status;
    }

    public void pause() {
        paused.getAndSet(true);
    }

    public synchronized void unPause() {
        paused.getAndSet(false);
        notify();
    }
}
