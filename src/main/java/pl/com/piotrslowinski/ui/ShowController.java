package pl.com.piotrslowinski.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.com.piotrslowinski.application.CinemaFinder;
import pl.com.piotrslowinski.application.CinemaHallDto;

@RestController
@RequestMapping("/shows")
public class ShowController {

    @Autowired
    private CinemaFinder cinemaFinder;


    public ShowController(CinemaFinder cinemaFinder) {
        this.cinemaFinder = cinemaFinder;
    }

    @GetMapping("/{showId}/seats")
    public CinemaHallDto getSeats(@PathVariable Long showId){
        return cinemaFinder.getSeats(showId);
    }
}
