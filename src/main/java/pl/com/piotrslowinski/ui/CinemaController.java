package pl.com.piotrslowinski.ui;

import org.springframework.web.bind.annotation.*;
import pl.com.piotrslowinski.application.*;
import pl.com.piotrslowinski.model.commands.CreateCinemaCommand;
import pl.com.piotrslowinski.model.commands.CreateShowsCommand;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/cinemas")
public class CinemaController {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy/MM/dd");

    private CommandGateway gateway;
    private CinemaFinder cinemaFinder;
    private MovieFinder movieFinder;

    public CinemaController(CommandGateway gateway, CinemaFinder cinemaFinder, MovieFinder movieFinder) {
        this.gateway = gateway;
        this.cinemaFinder = cinemaFinder;
        this.movieFinder = movieFinder;
    }

    @PutMapping
    public void create(@RequestBody CreateCinemaCommand cmd){
        gateway.execute(cmd);
    }

    @PutMapping("/{cinemaId}/shows")
    public void createShows(@PathVariable Long cinemaId, @RequestBody CreateShowsCommand cmd){
        cmd.setCinemaId(cinemaId);
        gateway.execute(cmd);
    }

    @GetMapping
    public List<CinemaDto> getAll(){
        return cinemaFinder.getAll();
    }

    @GetMapping("/{cinemaId}/movies")
    public List<MovieDto> getShows(@PathVariable("cinemaId") Long cinemaId, @RequestParam String date){
        return movieFinder.getFromDay(cinemaId, LocalDate.parse(date, FORMATTER));
    }
}
