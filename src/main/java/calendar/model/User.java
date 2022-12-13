package calendar.model;

import java.util.Collections;
import java.util.List;

public class User extends Participant{
    private TimeSlot timeSlot;
    private Team team;

    public User(String name, TimeSlot timeSlot) {
        this(name, timeSlot, null);
    }

    public User(String name, TimeSlot timeSlot, Team team) {
        this.name = name;
        this.timeSlot = timeSlot;
        this.team = team;
    }

    public TimeSlot getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(TimeSlot timeSlot) {
        this.timeSlot = timeSlot;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    @Override
    public boolean isTeam() {
        return false;
    }

    @Override
    public List<User> getMembers() {
        return Collections.singletonList(this);
    }
}
