package pl.com.piotrslowinski.infrastructure;

import org.springframework.stereotype.Component;
import pl.com.piotrslowinski.model.Reservation;
import pl.com.piotrslowinski.model.repositories.GenericJPARepository;
import pl.com.piotrslowinski.model.repositories.ReservationRepository;

import javax.persistence.EntityManager;
@Component
public class JPAReservationRepository extends GenericJPARepository<Reservation> implements ReservationRepository {


}
