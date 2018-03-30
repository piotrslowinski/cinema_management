package pl.com.piotrslowinski.acceptance;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.com.piotrslowinski.application.CinemaDto;
import pl.com.piotrslowinski.application.CinemaFinder;
import pl.com.piotrslowinski.application.CreateCinemaHandler;
import pl.com.piotrslowinski.model.commands.CreateCinemaCommand;
import pl.com.piotrslowinski.model.commands.InvalidCommandException;

import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.junit.Assert.assertEquals;


@SpringBootTest
@RunWith(SpringRunner.class)
public class CreateCinemaTest extends AcceptanceTest {

    @Autowired
    private CreateCinemaHandler createCinemaHandler;

    @Autowired
    private CinemaFinder cinemaFinder;

    private CreateCinemaCommand createCinemaCommand;


    public void createCinema(String city, String name){
        createCinemaCommand = new CreateCinemaCommand();
        createCinemaCommand.setCity(city);
        createCinemaCommand.setName(name);
        createCinemaHandler.handle(createCinemaCommand);
    }

    @Test
    public void shouldCreateCinema(){
        //when
        createCinema("Lublin", "Plaza");

        //then
        CinemaDto cinemaDto = cinemaFinder.getAll().get(0);
        assertThat(createCinemaCommand.getCity()).isEqualTo(cinemaDto.getCity());
        assertThat(createCinemaCommand.getName()).isEqualTo(cinemaDto.getName());
    }

    @Test(expected = InvalidCommandException.class)
    public void shouldNotAllowToPersistTheSameCinemaTwice(){
        //given
        createCinema("Lublin", "Olimp");

        //when
        createCinema("Lublin", "Olimp");

    }

    @Test
    public void shouldSaveCinemasAsList(){
        createCinema("Lublin","Olimp");
        createCinema("Lublin", "Plaza");
        createCinema("Warszawa", "Syrenka");

        //then
        List<CinemaDto> cinemaList = cinemaFinder.getAll();
        assertEquals(3, cinemaList.size());
    }

    
}
