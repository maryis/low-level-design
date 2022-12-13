package calendar.dao;

import calendar.model.Event;
import calendar.model.Participant;
import calendar.model.TimeSlot;
import calendar.util.DateUtil;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class EventDao extends BasicDao {

    public List<Event> getEventsOfTime(TimeSlot timeSlot, Date date) {
        List<Event> allEvents = getAllByDate(date);
        List<Event> result = new ArrayList<>();
        for(Event event: allEvents) {
            if(event.getTimeSlot().overlap(timeSlot))
                result.add(event);
        }
        return result;
    }

//    public List<TimeSlot> getAvailability(List<Participant> participants, Date day) {
//
//    }

    public List<Event> getAllByDate(Date date) {
        List<Event> allEvents = getAll();
        List<Event> result = new ArrayList<>();
        for(Event event: allEvents) {
            if(DateUtil.getDay(event.getDate()) == DateUtil.getDay(date))
                result.add(event);
        }
        return result;
    }

}
