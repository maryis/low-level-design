package lift;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ElevatorImp implements Elevator {

    private final int key;
    private int location;
    private Direction direction;
    private final Map<Integer, Boolean> localRequests;
    public static int floors;
    private final Scanner scanner;


    public ElevatorImp(int key) {
        location = 1;
        this.key = key;
        localRequests = new HashMap<>();
        for (int i = 0; i < floors; i++) {
            localRequests.put(i, false);
        }
        direction = Direction.UP;
        scanner = new Scanner(System.in);


        Thread process = new Thread(() -> {
            while (true) {
                int nextFloor = getNext();
                if (nextFloor != 0) {
                    goTo(nextFloor);
                }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
            }
        });


        Thread listener = new Thread(() -> {
            System.out.println("next local for " + key +
                    " req(1 to " + floors + ")");
            int floor ;
            while (scanner.hasNext()) {
                floor = scanner.nextInt();

                changeLocalList(floor, Controller.ReqType.ADD);

                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                synchronized (System.out) {
                    localRequests.forEach((k, v) -> System.out.print(k + ":" + v + "---"));

                    System.out.println("next local for " + key +
                            " req(1 to " + floors + ")");
                }
            }
        });

        process.start();
        listener.start();


    }

    public void changeLocalList(int floor, Controller.ReqType reqType) {
        switch (reqType) {
            case ADD:
                localRequests.put(floor - 1, true);
                break;
            case DEL:
                localRequests.put(floor - 1, false);
        }

    }

    public void goTo(int nextFloor) {
        location = nextFloor;
        Controller.getInstance().changeGlobalList(nextFloor, Controller.ReqType.DEL);
        changeLocalList(nextFloor, Controller.ReqType.DEL);
        System.out.println(key + " is in: " + nextFloor);

    }

    public boolean isPressed(int floor) {
        return localRequests.get(floor - 1);
    }

    public int getNext() {
        int next = 0;
        if (direction == Direction.UP) {
            for (int i = location + 1; i <= floors; i++)
                if (this.isPressed(i) || Controller.getInstance().isPressed(i)) {
                    next = i;
                    return next;
                }
                direction= Direction.DOWN;


        } else {
            for (int i = location - 1; i > 0; i--)
                if (this.isPressed(i) || Controller.getInstance().isPressed(i)) {
                    next = i;
                    return next;
                }
                direction= Direction.UP;

        }

        return next;
    }
}
