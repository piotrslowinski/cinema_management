package pl.com.piotrslowinski.ui;

import org.springframework.web.bind.annotation.*;
import pl.com.piotrslowinski.application.CommandGateway;
import pl.com.piotrslowinski.application.MovieDto;
import pl.com.piotrslowinski.application.MovieFinder;
import pl.com.piotrslowinski.model.commands.CreateMovieCommand;
import pl.com.piotrslowinski.model.commands.SetTicketPricesCommand;

import java.math.BigDecimal;
import java.util.Map;

@RestController
@RequestMapping("/movies")
public class MovieController {

    private CommandGateway gateway;
    private MovieFinder movieFinder;

    public MovieController(CommandGateway gateway, MovieFinder movieFinder) {
        this.gateway = gateway;
        this.movieFinder = movieFinder;
    }

    @PutMapping
    public void addMovie(@RequestBody CreateMovieCommand cmd){
        gateway.execute(cmd);
    }

    @PutMapping("/{movieId}/prices")
    public void setTicketPrices(@PathVariable Long movieId, @RequestBody Map<String, BigDecimal> prices){
        SetTicketPricesCommand cmd = new SetTicketPricesCommand();
        cmd.setMovieId(movieId);
        cmd.setPrices(prices);
        gateway.execute(cmd);
    }

    @GetMapping("/{id}")
    public MovieDto get(@PathVariable Long id) {
        return movieFinder.get(id);
    }
}
