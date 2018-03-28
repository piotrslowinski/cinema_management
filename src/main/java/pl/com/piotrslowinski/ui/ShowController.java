package pl.com.piotrslowinski.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.com.piotrslowinski.application.CinemaFinder;

@RestController
@RequestMapping("/shows")
public class ShowController {

    @Autowired
    private CinemaFinder cinemaFinder;


    public ShowController(CinemaFinder cinemaFinder) {
        this.cinemaFinder = cinemaFinder;
    }
}
