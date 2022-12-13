package calendar.dao;

import calendar.model.Participant;
import calendar.model.Team;
import calendar.model.User;

import java.util.Optional;

public class TeamDao extends BasicDao<Team> {
    public Optional<Team> addParticipant(Team team, User user) {
        Team fetchedTeam = get(team.getName()).orElse(null);
        if(fetchedTeam==null)
            return Optional.empty();
        fetchedTeam.getMembers().add(user);
        return Optional.of(fetchedTeam);
    }
}
