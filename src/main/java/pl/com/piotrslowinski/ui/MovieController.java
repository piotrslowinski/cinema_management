package pl.com.piotrslowinski.ui;

import org.springframework.web.bind.annotation.*;
import pl.com.piotrslowinski.application.CommandGateway;
import pl.com.piotrslowinski.model.commands.CreateMovieCommand;
import pl.com.piotrslowinski.model.commands.SetTicketPricesCommand;

import java.math.BigDecimal;
import java.util.Map;

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

    @PutMapping("/{movieId}/prices")
    public void setTicketPrices(@PathVariable Long movieId, @RequestBody Map<String, BigDecimal> prices){
        SetTicketPricesCommand cmd = new SetTicketPricesCommand();
        cmd.setMovieId(movieId);
        cmd.setPrices(prices);
        gateway.execute(cmd);
    }
}
