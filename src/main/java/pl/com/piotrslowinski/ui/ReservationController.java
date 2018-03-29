package pl.com.piotrslowinski.ui;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.com.piotrslowinski.application.CommandGateway;
import pl.com.piotrslowinski.application.ReservationNumberDto;
import pl.com.piotrslowinski.model.commands.CreateReservationCommand;

@RestController
@RequestMapping
public class ReservationController {


    private CommandGateway gateway;

    public ReservationController(CommandGateway gateway) {
        this.gateway = gateway;
    }

    @PutMapping("/reservation")
    public ReservationNumberDto create(@RequestBody CreateReservationCommand cmd){
        ReservationNumberDto reservationNumber = gateway.execute(cmd);
        return reservationNumber;
    }
}

