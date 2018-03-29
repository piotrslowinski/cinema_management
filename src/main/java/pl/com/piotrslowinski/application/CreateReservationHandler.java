package pl.com.piotrslowinski.application;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.com.piotrslowinski.model.Reservation;
import pl.com.piotrslowinski.model.Show;
import pl.com.piotrslowinski.model.commands.Command;
import pl.com.piotrslowinski.model.commands.CreateReservationCommand;
import pl.com.piotrslowinski.model.repositories.ReservationRepository;
import pl.com.piotrslowinski.model.repositories.ShowRepository;

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
        Reservation reservation = new Reservation(cmd);
        reservationRepository.save(reservation);
        return new ReservationNumberDto(reservation);
    }

    @Override
    public Class<? extends Command> getSupportedCommandClass() {
        return CreateReservationCommand.class;
    }
}
