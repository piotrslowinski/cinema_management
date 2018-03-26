package pl.com.piotrslowinski.ui;

import org.springframework.web.bind.annotation.*;
import pl.com.piotrslowinski.application.CinemaDto;
import pl.com.piotrslowinski.application.CinemaFinder;
import pl.com.piotrslowinski.application.CommandGateway;
import pl.com.piotrslowinski.application.CreateCinemaHandler;
import pl.com.piotrslowinski.model.commands.CreateCinemaCommand;
import pl.com.piotrslowinski.model.commands.CreateShowsCommand;

import java.util.List;

@RestController
@RequestMapping("/cinemas")
public class CinemaController {

    private CommandGateway gateway;
    private CinemaFinder cinemaFinder;

    public CinemaController(CommandGateway gateway, CinemaFinder cinemaFinder) {
        this.gateway = gateway;
        this.cinemaFinder = cinemaFinder;
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
}
