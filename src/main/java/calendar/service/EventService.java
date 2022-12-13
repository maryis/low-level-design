package calendar.service;

import calendar.dao.EventDao;
import calendar.exception.NoAvailableTime;
import calendar.exception.WrongParameters;
import calendar.model.*;
import calendar.order.EventComparator;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class EventService {
    private EventDao eventDao;

    public EventService(EventDao eventDao) {
        this.eventDao = eventDao;
    }

    public List<Event> getEventsForUser(User user, TimeSlot timeSlot, Date date) {
        Team userTeam = user.getTeam();
        List<Event> allEvents = getEvents(timeSlot, date);
        return allEvents.stream().filter(event -> isUserInEvent(event, user)).collect(Collectors.toList());
    }

    public List<Event> getEvents(TimeSlot timeSlot, Date date) {
        return eventDao.getEventsOfTime(timeSlot, date);
    }

    public List<Event> getEvents(Date date) {
        return eventDao.getAllByDate(date);
    }

    public List<Event> getSortedEvents(Date date, EventComparator comparator) {
        List<Event> result =  eventDao.getAllByDate(date);
        result.sort(comparator);
        return result;
    }

    public boolean isUserInEvent(Event event, User user) {
        if (event.getParticipants().stream()
                .flatMap(participant -> participant.getMembers().stream())
                .anyMatch(u -> u.equals(user))) {
            return true;
        }
        return false;
    }

    public Event creatEvent(String name, List<Participant> participants, TimeSlot timeSlot, Date date) throws NoAvailableTime {
        if(participants == null || participants.isEmpty())
            throw new WrongParameters("Not able to create event");
        for(Participant p : participants)
            for(User user : p.getMembers())
                if(!getEventsForUser(user, timeSlot, date).isEmpty())
                    throw new NoAvailableTime("Not able to create event");

        Event event = new Event(name, timeSlot, date, participants);
        eventDao.saveOrUpdate(name, event);
        return event;
    }

    public List<TimeSlot> getAvailability(List<Participant> participants, Date date) {
        List<TimeSlot> result = new ArrayList<>();
        result.add(new TimeSlot("00:00", "23:59"));
        List<Event> allEvents = getEvents(date);
        for(Event event : allEvents) {
            for(Participant p : participants)
                for(User user: p.getMembers())
                    if(isUserInEvent(event, user)) {
                        exclude(result, event.getTimeSlot());
                        break;
                    }
        }
        return result;
    }

    private void exclude(List<TimeSlot> result, TimeSlot timeSlot) {

    }
}
