package calendar.order;

import calendar.model.Event;

public class SizeComparator extends EventComparator {
    @Override
    public int compare(Event o1, Event o2) {
        return o1.getParticipants().size()-o2.getParticipants().size();
    }
}
