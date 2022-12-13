package calendar.model;

import java.util.List;

public class Team extends Participant{
    private List<User> participants;

    public Team(String name, List<User> participants) {
        this.name = name;
        this.participants = participants;
    }

    public void addMember(User user) {
        this.participants.add(user);
    }

    @Override
    public boolean isTeam() {
        return true;
    }

    @Override
    public List<User> getMembers() {
        return participants;
    }
}
