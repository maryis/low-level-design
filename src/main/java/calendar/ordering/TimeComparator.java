package calendar.ordering;

import calendar.model.Event;

public class TimeComparator extends EventComparator {

    @Override
    public int compare(Event o1, Event o2) {
        if(o1.getDate().equals(o1.getDate()))
            return o1.getTimeSlot().compareTo(o2.getTimeSlot());
        return o1.getDate().compareTo(o2.getDate());
    }
}
