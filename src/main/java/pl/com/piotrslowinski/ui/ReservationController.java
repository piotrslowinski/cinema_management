package pl.com.piotrslowinski.ui;

import org.springframework.web.bind.annotation.*;
import pl.com.piotrslowinski.application.*;
import pl.com.piotrslowinski.model.PaymentFacade;
import pl.com.piotrslowinski.model.PaymentStatus;
import pl.com.piotrslowinski.model.Receipt;
import pl.com.piotrslowinski.model.commands.CalculatePricesCommand;
import pl.com.piotrslowinski.model.commands.CreateReservationCommand;
import pl.com.piotrslowinski.model.commands.GenerateTicketsCommand;
import pl.com.piotrslowinski.model.commands.PaymentCommand;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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

    @GetMapping("reservations/{reservationNumber}/tickets")
    public void getTickets(@PathVariable Long reservationNumber, HttpServletResponse response) throws IOException {
        GenerateTicketsCommand cmd = new GenerateTicketsCommand();
        cmd.setReservationNumber(reservationNumber);
        byte[] pdfData = gateway.execute(cmd);
        String fileName = String.format("Reservation_%d.pdf", reservationNumber);
        response.setContentType("application/pdf");
        response.addHeader("Content-disposition", "attachment; filename=" + fileName);
        response.setContentLength(pdfData.length);
        response.setContentLength(pdfData.length);
        response.getOutputStream().write(pdfData);
        response.getOutputStream().flush();

    }
}

