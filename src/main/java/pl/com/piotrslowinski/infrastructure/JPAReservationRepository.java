package pl.com.piotrslowinski.infrastructure;

import org.springframework.stereotype.Component;
import pl.com.piotrslowinski.model.Reservation;
import pl.com.piotrslowinski.model.repositories.GenericJPARepository;
import pl.com.piotrslowinski.model.repositories.ReservationRepository;

import javax.persistence.EntityManager;
import java.util.Set;

@Component
public class JPAReservationRepository extends GenericJPARepository<Reservation> implements ReservationRepository {


    @Override
    public Set<Reservation> getReservations(Long showId) {
        //TODO
        return null;
    }
}
