package pl.com.piotrslowinski.acceptance;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.com.piotrslowinski.application.CreateMovieHandler;
import pl.com.piotrslowinski.application.MovieDto;
import pl.com.piotrslowinski.application.MovieFinder;
import pl.com.piotrslowinski.application.SetTicketPricesHandler;
import pl.com.piotrslowinski.model.commands.CreateMovieCommand;
import pl.com.piotrslowinski.model.commands.SetTicketPricesCommand;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AddTicketPricesTest extends AcceptanceTest{

    @Autowired
    private SetTicketPricesHandler setTicketPricesHandler;

    @Autowired
    private MovieFinder movieFinder;

    @Autowired
    private CreateMovieHandler createMovieHandler;

    private SetTicketPricesCommand setTicketPricesCommand;

    private CreateMovieCommand cmd;


    @Before
    public void createMovie(){
        cmd = new CreateMovieCommand();
        cmd.setActors(new HashSet<>(Arrays.asList("John Travolta")));
        cmd.setDescription("Royale with cheese");
        cmd.setGenres(new HashSet<>(Arrays.asList("Dramat")));
        cmd.setMinAge(17);
        cmd.setLength(100);
        cmd.setTitle("Pulp Fiction");
        createMovieHandler.handle(cmd);

    }
    @Test
    public void shouldAddTicketPrices() {
        setTicketPricesCommand = new SetTicketPricesCommand();
        HashMap<String, BigDecimal> prices = new HashMap<>();
        prices.put("regular", BigDecimal.valueOf(20));
        prices.put("student", BigDecimal.valueOf(10));
        prices.put("school", BigDecimal.valueOf(10));
        prices.put("children", BigDecimal.valueOf(5));
        setTicketPricesCommand.setPrices(prices);
        setTicketPricesCommand.setMovieId(1L);
        setTicketPricesHandler.handle(setTicketPricesCommand);

        MovieDto movieDto = movieFinder.get(1L);
        assertThat(setTicketPricesCommand.getPrices().equals(movieDto.getTicketPrices().getPrices()));
    }}
