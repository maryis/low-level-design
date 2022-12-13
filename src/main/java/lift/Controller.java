package lift;

import java.util.*;

public class Controller {

    public enum ReqType {
        ADD,
        DEL
    }

    private static final int floors = 5;
    private final Map<Integer, Boolean> globalRequests;
    private static final int liftCnt = 2;
    private final List<Elevator> elevatorList;
    private static Controller controller;
    private final Scanner scanner;

    public static synchronized Controller getInstance() {
        if (controller == null)
            controller = new Controller();
        return controller;
    }

    public boolean isPressed(int floor) {
        return globalRequests.get(floor - 1);
    }

    private Controller() {

        globalRequests = new HashMap<>();
        elevatorList = new ArrayList<>();
        ElevatorImp.floors = floors;

        scanner = new Scanner(System.in);
        for (int i = 0; i < floors; i++) {
            globalRequests.put(i, false);
        }
        for (int i = 0; i < liftCnt; i++) {
            elevatorList.add(new ElevatorImp(i));
        }
        Thread scheduler=new Thread(new Listener());
        scheduler.start();
    }

    private class Listener implements Runnable {

        public void run() {

                System.out.println("next global req(1 to " + floors + ")");
                int floor ;
                while (scanner.hasNext()) {
                    floor = scanner.nextInt();
                    changeGlobalList(floor, ReqType.ADD);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (System.out) {
                        globalRequests.forEach((k, v) -> System.out.print(k + ":" + v + "---"));
                        System.out.println("next global req(1 to " + floors + ")");

                    }
                }
            }

    }


    public synchronized void changeGlobalList(int floor, ReqType reqType) {
        switch (reqType) {
            case ADD:
                globalRequests.put(floor - 1, true);
                break;
            case DEL:
                globalRequests.put(floor - 1, false);
        }

    }

    public void showLifts(){
        elevatorList.forEach(Object::toString);
    }

}
