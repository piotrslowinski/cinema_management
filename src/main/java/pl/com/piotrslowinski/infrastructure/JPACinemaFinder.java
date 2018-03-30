package pl.com.piotrslowinski.infrastructure;

import org.springframework.stereotype.Component;
import pl.com.piotrslowinski.application.CinemaDto;
import pl.com.piotrslowinski.application.CinemaFinder;
import pl.com.piotrslowinski.application.CinemaHallDto;
import pl.com.piotrslowinski.model.CinemaHall;
import pl.com.piotrslowinski.model.Reservation;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class JPACinemaFinder implements CinemaFinder {

    private EntityManager entityManager;

    public JPACinemaFinder(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<CinemaDto> getAll() {
        List<CinemaDto> results = entityManager.createQuery("SELECT NEW " +
                "pl.com.piotrslowinski.application.CinemaDto(c.id, c.name, c.city) " +
                "FROM Cinema c").getResultList();
        return results;
    }

    @Override
    public CinemaHallDto getSeats(Long showId) {
        Query query = entityManager.createQuery(" FROM Reservation r WHERE r.showId = :showId");
        query.setParameter("showId", showId);
        Set<Reservation> reservations = new HashSet<>();
        reservations.addAll(query.getResultList());
        return new CinemaHallDto(new CinemaHall(reservations));
    }
}
