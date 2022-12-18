package onlinechess;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class Game {
    Player[] players;
    Board board;
    int currentPlayer;
    //we keep current thread active to receive commands from outside
    //and we do playing in another infinite thread
    AtomicBoolean isPaused;
    ExecutorService executorService;

    public Game() {
        init();
    }

    private void init() {
        players[0] = new Human("human", Color.WHITE);
        players[1] = new Computer("computer", Color.BLACK);
        board = new Board();
        currentPlayer = 0;
    }

    //@Async
    public void start() {
        executorService.submit(() -> {
            while (!isEnded()) {
                synchronized (this) {
                    if (isPaused.get() == true) {
                        try {
                            this.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            System.err.println("interupted!");
                        }
                    }
                }
                nextMove(currentPlayer);
            }
        });
    }

    public void stop() {
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(200, TimeUnit.MILLISECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void nextMove(int currentPlayer) {
        Player current = players[currentPlayer];
        Move nextMove = current.nextMove(board);
        current.move(board, nextMove);
        updateStatus();
        switchCurrentPlayer();
    }

    public void pause() {
        isPaused.set(true);
    }

    public synchronized void unPause() {
        isPaused.set(false);
        this.notify();
    }

    public Status GetState() {
        return board.status;
    }

    private void updateStatus() {
        if(!isPaused.get())
            board.calculateState();
    }

    private void switchCurrentPlayer() {
        if(currentPlayer == 0) {
            currentPlayer = 1;
            return;
        }
        currentPlayer = 0;
    }

    private boolean isEnded() {
        return board.status != Status.PLAYING;
    }
}
