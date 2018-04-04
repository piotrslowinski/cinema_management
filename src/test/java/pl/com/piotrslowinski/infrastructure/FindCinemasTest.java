package pl.com.piotrslowinski.infrastructure;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.com.piotrslowinski.application.CinemaDto;
import pl.com.piotrslowinski.application.CinemaFinder;
import pl.com.piotrslowinski.model.Cinema;
import pl.com.piotrslowinski.model.commands.CreateCinemaCommand;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

import static org.junit.Assert.*;
import static org.assertj.core.api.Java6Assertions.assertThat;

@SpringBootTest
@RunWith(SpringRunner.class)
public class FindCinemasTest extends InfrastructureTest {

    @Autowired
    private CinemaFinder cinemaFinder;

    @Autowired
    private EntityManager entityManager;

    private CreateCinemaCommand cmd;


    public void createCinema(String city, String name){
        Cinema cinema = new Cinema("Plaza", "Lublin");
        entityManager.persist(cinema);
    }

    @Transactional
    @Test
    public void shouldFindCinema(){
        //given
        createCinema("Lublin","Plaza");

        //when
        List<CinemaDto> cinemas = cinemaFinder.getAll();
        //then
        assertEquals(1, cinemas.size());
        assertThat(cinemas.get(0).getCity()).isEqualTo("Lublin");
        assertThat(cinemas.get(0).getName()).isEqualTo("Plaza");
    }

    @Transactional
    @Test
    public void shouldReturnAllCinemas(){
        //given
        createCinema("Lublin", "Plaza");
        createCinema("Warszawa", "Syrenka");
        createCinema("Gdynia", "Neptun");

        //when
        List<CinemaDto> cinemas = cinemaFinder.getAll();

        //then
        assertEquals(3, cinemas.size());
    }

    @Test
    public void shouldReturnNoCinemaWhenDbIsEmpty(){
        //when
        List<CinemaDto> cinemas = cinemaFinder.getAll();

        //then
        assertEquals(0, cinemas.size());
        assertThat(cinemas.isEmpty());
    }
}
