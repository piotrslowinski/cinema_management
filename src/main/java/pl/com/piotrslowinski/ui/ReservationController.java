package pl.com.piotrslowinski.ui;

import org.springframework.web.bind.annotation.*;
import pl.com.piotrslowinski.application.CommandGateway;
import pl.com.piotrslowinski.application.ReservationNumberDto;
import pl.com.piotrslowinski.model.Receipt;
import pl.com.piotrslowinski.model.commands.CalculatePricesCommand;
import pl.com.piotrslowinski.model.commands.CreateReservationCommand;

@RestController
@RequestMapping
public class ReservationController {


    private CommandGateway gateway;

    public ReservationController(CommandGateway gateway) {
        this.gateway = gateway;
    }

    @PutMapping("/reservations")
    public ReservationNumberDto create(@RequestBody CreateReservationCommand cmd){
        ReservationNumberDto reservationNumber = gateway.execute(cmd);
        return reservationNumber;
    }

    @PostMapping("/reservations/price_calculator")
    public Receipt calculatePrices(@RequestBody CalculatePricesCommand cmd){
        return gateway.execute(cmd);
    }
}

