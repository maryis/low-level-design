package lift;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public interface Elevator {

    enum Direction {
        UP,
        DOWN
    }



    void changeLocalList(int floor, Controller.ReqType reqType);

    void goTo(int nextFloor) ;

    boolean isPressed(int floor) ;

    int getNext() ;


}
