package pl.com.piotrslowinski.ui;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.com.piotrslowinski.application.CommandGateway;
import pl.com.piotrslowinski.model.commands.CreateMovieCommand;

@RestController
@RequestMapping("/movies")
public class MovieController {

    private CommandGateway gateway;

    public MovieController(CommandGateway gateway) {
        this.gateway = gateway;
    }

    @PutMapping
    public void addMovie(@RequestBody CreateMovieCommand cmd){
        gateway.execute(cmd);
    }
}
