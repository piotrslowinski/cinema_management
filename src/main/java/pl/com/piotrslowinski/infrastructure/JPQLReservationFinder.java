package pl.com.piotrslowinski.infrastructure;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.com.piotrslowinski.application.ReservationDto;
import pl.com.piotrslowinski.application.ReservationFinder;
import pl.com.piotrslowinski.application.ReservationQuery;
import pl.com.piotrslowinski.model.Reservation;
import pl.com.piotrslowinski.model.Show;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.LinkedList;
import java.util.List;
@Component
@Transactional
public class JPQLReservationFinder implements ReservationFinder {

    private EntityManager entityManager;

    public JPQLReservationFinder(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<ReservationDto> search(ReservationQuery reservationQuery) {
        Query query = entityManager.createQuery("FROM Reservation r " +
                "JOIN r.customer c " +
                "WHERE c.lastName LIKE :lastName AND r.reservationStatus = :status");
        query.setParameter("lastName", reservationQuery.getQuery() + "%");
        query.setParameter("status", reservationQuery.getStatus());
        List<Reservation> reservations = query.getResultList();

        List<ReservationDto> reservationDtos = new LinkedList<>();
        for(Reservation reservation: reservations){
            Show show = entityManager.find(Show.class, reservation.getShowId());
            ReservationDto reservationDto = new ReservationDto(reservation, show);
            reservationDtos.add(reservationDto);
        }
        return reservationDtos;
    }
}
