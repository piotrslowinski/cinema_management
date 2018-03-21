package pl.com.piotrslowinski.ui;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.com.piotrslowinski.application.CreateCinemaHandler;
import pl.com.piotrslowinski.model.commands.CreateCinemaCommand;

@RestController
@RequestMapping("/cinemas")
public class CinemaController {

    private CreateCinemaHandler handler;

    public CinemaController(CreateCinemaHandler handler) {
        this.handler = handler;
    }

    @PutMapping
    public void create(@RequestBody CreateCinemaCommand cmd){
        handler.handle(cmd);
    }
}
