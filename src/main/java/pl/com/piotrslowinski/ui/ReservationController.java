package pl.com.piotrslowinski.ui;

import org.springframework.web.bind.annotation.*;
import pl.com.piotrslowinski.application.*;
import pl.com.piotrslowinski.model.PaymentFacade;
import pl.com.piotrslowinski.model.PaymentStatus;
import pl.com.piotrslowinski.model.Receipt;
import pl.com.piotrslowinski.model.commands.CalculatePricesCommand;
import pl.com.piotrslowinski.model.commands.CreateReservationCommand;
import pl.com.piotrslowinski.model.commands.PaymentCommand;

import java.util.List;

@RestController
@RequestMapping
public class ReservationController {


    private CommandGateway gateway;
    private ReservationFinder reservationFinder;

    public ReservationController(CommandGateway gateway, ReservationFinder reservationFinder) {
        this.gateway = gateway;
        this.reservationFinder = reservationFinder;
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

    @GetMapping("/reservations")
    List<ReservationDto> search(ReservationQuery query){
        return reservationFinder.search(query);
    }

    @PutMapping("/reservations/{reservationNumber}/payment")
    public PaymentStatus pay(@PathVariable Long reservationNumber, @RequestBody PaymentCommand cmd){
        cmd.setReservationNumber(reservationNumber);
        return gateway.execute(cmd);
    }
}

