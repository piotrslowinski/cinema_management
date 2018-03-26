package pl.com.piotrslowinski.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.com.piotrslowinski.infrastructure.JPACinemaFinder;

@RestController
@RequestMapping("/shows")
public class ShowController {

    @Autowired
    private JPACinemaFinder cinemaFinder;

    public ShowController(JPACinemaFinder cinemaFinder) {
        this.cinemaFinder = cinemaFinder;
    }


}
