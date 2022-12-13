package calendar.service;

import calendar.dao.TeamDao;
import calendar.dao.UserDao;
import calendar.exception.NotFoundException;
import calendar.model.Team;
import calendar.model.TimeSlot;
import calendar.model.User;

import java.util.Optional;

public class UserService {
    private UserDao userDao;
    private TeamService teamService;

    public UserService(UserDao userDao, TeamService teamService) {
        this.userDao = userDao;
        this.teamService = teamService;
    }

    User createUser(String name, TimeSlot slot) {
        User user = new User(name, slot);
        userDao.saveOrUpdate(name, user);
        return user;
    }

    Optional<User> createUser(String name, TimeSlot slot, Team team) {
        Team fetchedTeam = teamService.getTeam(team.getName()).orElse(null);
        if(fetchedTeam==null)
            return Optional.empty();
        User user = new User(name, slot, team);
        userDao.saveOrUpdate(name, user);
        try {
            teamService.addParticipant(fetchedTeam.getName(), user);
        } catch (NotFoundException e) {
            return Optional.empty();
        }
        return Optional.of(user);
    }

}
