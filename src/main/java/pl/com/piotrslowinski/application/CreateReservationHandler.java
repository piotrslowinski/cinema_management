package pl.com.piotrslowinski.application;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.com.piotrslowinski.model.CinemaHall;
import pl.com.piotrslowinski.model.Receipt;
import pl.com.piotrslowinski.model.Reservation;
import pl.com.piotrslowinski.model.Show;
import pl.com.piotrslowinski.model.commands.Command;
import pl.com.piotrslowinski.model.commands.CreateReservationCommand;
import pl.com.piotrslowinski.model.repositories.ReservationRepository;
import pl.com.piotrslowinski.model.repositories.ShowRepository;

import java.util.Set;

@Component
public class CreateReservationHandler implements Handler<CreateReservationCommand, ReservationNumberDto> {

    private ReservationRepository reservationRepository;
    private ShowRepository showRepository;

    public CreateReservationHandler(ReservationRepository reservationRepository, ShowRepository showRepository) {
        this.reservationRepository = reservationRepository;
        this.showRepository = showRepository;
    }

    @Override
    @Transactional
    public ReservationNumberDto handle(CreateReservationCommand cmd) {
        Show show = showRepository.get(cmd.getShowId());
        Receipt receipt = show.calculatePrice(cmd.getTickets());
        Reservation reservation = new Reservation(cmd, receipt.getTotalPrice());
        Set<Reservation> reservations = reservationRepository.getReservations(cmd.getShowId());
        CinemaHall cinemaHall = new CinemaHall(reservations);
        cinemaHall.checkReservation(cmd);
        reservationRepository.save(reservation);
        return new ReservationNumberDto(reservation);
    }

    @Override
    public Class<? extends Command> getSupportedCommandClass() {
        return CreateReservationCommand.class;
    }
}
