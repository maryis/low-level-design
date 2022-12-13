package calendar.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class Event {
    private String name;
    private TimeSlot timeSlot;
    private Date date;
    private List<Participant> participants;

    public Event(String name, TimeSlot timeSlot, Date date) {
        this(name, timeSlot, date, new ArrayList<>());
    }

    public Event(String name, TimeSlot timeSlot, Date date, List<Participant> participants) {
        this.name = name;
        this.timeSlot = timeSlot;
        this.date = date;
        this.participants = participants;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TimeSlot getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(TimeSlot timeSlot) {
        this.timeSlot = timeSlot;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<Participant> getParticipants() {
        return participants;
    }

    public void setParticipants(List<Participant> participants) {
        this.participants = participants;
    }

    public void addMember(Participant participant) {
        participants.add(participant);
    }
}
