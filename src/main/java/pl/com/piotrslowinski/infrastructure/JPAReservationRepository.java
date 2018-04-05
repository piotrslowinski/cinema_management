package pl.com.piotrslowinski.infrastructure;

import org.springframework.stereotype.Component;
import pl.com.piotrslowinski.model.Reservation;
import pl.com.piotrslowinski.model.repositories.GenericJPARepository;
import pl.com.piotrslowinski.model.repositories.ReservationRepository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.HashSet;
import java.util.Set;

@Component
public class JPAReservationRepository extends GenericJPARepository<Reservation> implements ReservationRepository {

    private EntityManager entityManager;

    public JPAReservationRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Set<Reservation> getReservations(Long showId) {
        Query query = entityManager.createQuery("FROM Reservation r WHERE r.showId =:showId");
        query.setParameter("showId", showId);
        Set<Reservation> result = new HashSet<>();
        result.addAll(query.getResultList());
        return result;

    }

    public void save(Reservation reservation){
        entityManager.persist(reservation);
    }
}
