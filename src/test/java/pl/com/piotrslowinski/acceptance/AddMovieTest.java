package pl.com.piotrslowinski.acceptance;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.com.piotrslowinski.application.CreateMovieHandler;
import pl.com.piotrslowinski.application.MovieDto;
import pl.com.piotrslowinski.application.MovieFinder;
import pl.com.piotrslowinski.model.commands.CreateMovieCommand;

import java.util.Arrays;
import java.util.HashSet;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AddMovieTest extends AcceptanceTest {


    @Autowired
    private CreateMovieHandler createMovieHandler;

    @Autowired
    private MovieFinder movieFinder;

    private CreateMovieCommand cmd;

    public void createMovie(){
        cmd = new CreateMovieCommand();
        cmd.setActors(new HashSet<>(Arrays.asList("John Travolta")));
        cmd.setDescription("Royale with cheese");
        cmd.setGenres(new HashSet<>(Arrays.asList("Sensacyjny")));
        cmd.setMinAge(17);
        cmd.setTitle("Pulp Fiction");
        createMovieHandler.handle(cmd);
    }

    @Test
    public void shouldAddMovie(){
        //when
        createMovie();

        //then
        MovieDto movieDto = movieFinder.get(1L);
        assertEquals(cmd.getActors(), movieDto.getActors());
        assertEquals(cmd.getGenres(), movieDto.getGenres());
        assertEquals("Royale with cheese", movieDto.getDescription());
        assertEquals(cmd.getMinAge(), movieDto.getMinAge());
        assertEquals("Pulp Fiction", movieDto.getTitle());
    }
}
