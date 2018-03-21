package pl.com.piotrslowinski.ui;

import org.springframework.web.bind.annotation.*;
import pl.com.piotrslowinski.application.CinemaDto;
import pl.com.piotrslowinski.application.CinemaFinder;
import pl.com.piotrslowinski.application.CreateCinemaHandler;
import pl.com.piotrslowinski.model.commands.CreateCinemaCommand;

import java.util.List;

@RestController
@RequestMapping("/cinemas")
public class CinemaController {

    private CreateCinemaHandler handler;
    private CinemaFinder cinemaFinder;

    public CinemaController(CreateCinemaHandler handler, CinemaFinder cinemaFinder) {
        this.handler = handler;
        this.cinemaFinder = cinemaFinder;
    }

    @PutMapping
    public void create(@RequestBody CreateCinemaCommand cmd){
        handler.handle(cmd);
    }

    @GetMapping
    public List<CinemaDto> getAll(){
        return cinemaFinder.getAll();
    }
}
