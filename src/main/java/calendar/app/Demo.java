package calendar.app;

import calendar.dao.EventDao;
import calendar.dao.TeamDao;
import calendar.dao.UserDao;
import calendar.exception.NoAvailableTime;
import calendar.exception.NotFoundException;
import calendar.exception.WrongParameters;
import calendar.model.Participant;
import calendar.model.TimeSlot;
import calendar.model.User;
import calendar.service.EventService;
import calendar.service.TeamService;
import calendar.service.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class Demo {

    UserDao userDao;
    UserService userService;
    EventService eventService;
    EventDao eventDao;
    TeamService teamService;
    TeamDao teamDao;

    @Before
    public void init() {
        eventDao = new EventDao();
        eventService = new EventService(eventDao);
        teamDao = new TeamDao();
        teamService = new TeamService(teamDao);
    }

    @Test (expected = WrongParameters.class)
    public void testCreateEventWithEmptyList() throws NoAvailableTime {
        List<Participant> participants = Collections.emptyList();
        eventService.creatEvent("event1", participants, new TimeSlot("10:10", "12:20"), new Date());
    }

    @Test
    public void testCreateEvent() throws NoAvailableTime {
        List<Participant> participants = new ArrayList<>();
        User user = new User("ali", new TimeSlot("08:10", "16:20"));
        participants.add(user);
        eventService.creatEvent("event1", participants, new TimeSlot("10:10", "12:20"), new Date());

        Assert.assertEquals(1,
                eventService.getEventsForUser(user, new TimeSlot("10:10", "12:20"), new Date()).size());
    }

    @Test
    public void testCreateTeam()  {
        List<User> participants = new ArrayList<>();
        User user1 = new User("ali1", new TimeSlot("08:10", "16:20"));
        User user2 = new User("ali2", new TimeSlot("08:10", "16:20"));
        participants.add(user1);
        participants.add(user2);
        teamService.createTeam(participants, "team1");

        Assert.assertEquals(true,
                teamService.getTeam("team1").isPresent());

    }

    @Test
    public void testAddUserTeam() throws NotFoundException {
        List<User> participants = new ArrayList<>();
        User user1 = new User("ali1", new TimeSlot("08:10", "16:20"));
        User user2 = new User("ali2", new TimeSlot("08:10", "16:20"));
        participants.add(user1);
        participants.add(user2);
        teamService.createTeam(participants, "team1");
        teamService.addParticipant("team1", new User("ali2", new TimeSlot("08:10", "16:20")));

        Assert.assertEquals(true,
                teamService.getTeam("team1").isPresent());

    }
}
