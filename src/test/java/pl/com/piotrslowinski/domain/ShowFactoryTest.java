package pl.com.piotrslowinski.domain;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import pl.com.piotrslowinski.model.Cinema;
import pl.com.piotrslowinski.model.Movie;
import pl.com.piotrslowinski.model.Show;
import pl.com.piotrslowinski.model.ShowFactory;
import pl.com.piotrslowinski.model.commands.CreateShowsCommand;
import pl.com.piotrslowinski.model.commands.ShowsCalendar;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShowFactoryTest {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private ShowFactory showFactory;

    private Cinema cinema;

    private Movie movie;

    private ShowsCalendar calendar = new ShowsCalendar();

    @Before
    public void setUp() {
        cinema = new Cinema("Kino", "Lublin");
        entityManager.persist(cinema);
        Set<String> actors = new HashSet<>();
        actors.add("Rysiek");
        Set<String> genres = new HashSet<>();
        genres.add("Kryminał");
        movie = new Movie("Test Film", "Great movie", actors, genres, 18, 120);
        entityManager.persist(movie);

        calendar.setFromDate(LocalDateTime.parse("2017-12-28T09:30"));
        calendar.setUntilDate(LocalDateTime.parse("2017-12-31T09:30"));
        Set<String> weekDays = new HashSet<>();
        weekDays.add("Friday");
        weekDays.add("Saturday");
        calendar.setWeekDays(weekDays);
        Set<LocalTime> hours = new HashSet<>();
        hours.add(LocalTime.parse("12:00"));
        calendar.setHours(hours);
    }

    @Test
    @Transactional
    public void shouldCreateShowWithoutCalendar() {
        //given
        CreateShowsCommand cmd = new CreateShowsCommand();
        cmd.setCinemaId(cinema.getId());
        cmd.setMovieId(movie.getId());
        Set<LocalDateTime> datesSet = new HashSet<>();
        datesSet.add(LocalDateTime.parse("2017-12-28T09:30"));
        datesSet.add(LocalDateTime.parse("2017-12-28T12:30"));
        cmd.setDates(datesSet);

        //when
        List<Show> shows = (List<Show>) showFactory.createShows(cmd);

        //then
        Show show1 = new Show(cinema, movie, LocalDateTime.parse("2017-12-28T09:30"));
        Show show2 = new Show(cinema, movie, LocalDateTime.parse("2017-12-28T12:30"));
        assertEquals(2, shows.size());
        //assertTrue( shows.contains(show1));
        assertEquals(show1.getMovie(), shows.get(0).getMovie());
        assertEquals(show1.getCinema(), shows.get(0).getCinema());
        assertEquals(show1.getDate(), shows.get(0).getDate());
        assertEquals(show2.getMovie(), shows.get(1).getMovie());
        assertEquals(show2.getCinema(), shows.get(1).getCinema());
        assertEquals(show2.getDate(), shows.get(1).getDate());
    }

    @Test
    @Transactional
    public void shouldCreateShowWithCalendar(){
        //given
        CreateShowsCommand cmd = new CreateShowsCommand();
        cmd.setCinemaId(cinema.getId());
        cmd.setMovieId(movie.getId());
        cmd.setCalendar(calendar);

        //when
        List<Show> shows = (List<Show>) showFactory.createShows(cmd);

        //then
        Show show1 = new Show(cinema, movie, LocalDateTime.parse("2017-12-29T12:00") );
        Show show2 = new Show(cinema, movie, LocalDateTime.parse("2017-12-30T12:00") );
        assertEquals(2, shows.size());
        assertEquals( show1.getMovie(), shows.get(0).getMovie());
        assertEquals( show1.getCinema(), shows.get(0).getCinema());
        assertEquals( show1.getDate(), shows.get(0).getDate());
        assertEquals( show2.getMovie(), shows.get(1).getMovie());
        assertEquals( show2.getCinema(), shows.get(1).getCinema());
        assertEquals( show2.getDate(), shows.get(1).getDate());

    }
}
