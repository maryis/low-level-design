package calendar.service;

import calendar.dao.TeamDao;
import calendar.exception.NotFoundException;
import calendar.model.Participant;
import calendar.model.Team;
import calendar.model.User;

import java.util.List;
import java.util.Optional;

public class TeamService {
    private TeamDao teamDao;

    public TeamService(TeamDao teamDao) {
        this.teamDao = teamDao;
    }

    public Optional<Team> getTeam(String name) {
        return teamDao.get(name);
    }

    public Team addParticipant(String team, User user) throws NotFoundException {
        Team fetchedTeam = teamDao.get(team).orElse(null);
        if(fetchedTeam == null)
            throw new NotFoundException("team not found");
        return addParticipant(fetchedTeam, user);
    }

    public Team addParticipant(Team team, User user) throws NotFoundException {
        Team result = teamDao.addParticipant(team, user).orElse(null);
        if (result == null)
            throw new NotFoundException(String.format("Team %s not found", team.getName()));
        return result;
    }

    public Team createTeam(List<User> users, String name) {
        Team team = new Team(name, users);
        teamDao.saveOrUpdate(name, team);
        return team;
    }
}
