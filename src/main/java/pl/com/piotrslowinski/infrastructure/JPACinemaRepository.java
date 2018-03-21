package pl.com.piotrslowinski.infrastructure;

import org.springframework.stereotype.Component;
import pl.com.piotrslowinski.model.Cinema;
import pl.com.piotrslowinski.model.repositories.CinemaRepository;

import javax.persistence.EntityManager;

@Component
public class JPACinemaRepository implements CinemaRepository {

    private EntityManager entityManager;

    public JPACinemaRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void save(Cinema cinema) {
        entityManager.persist(cinema);
    }

    @Override
    public Cinema get(Long id) {
        Cinema cinema = entityManager.find(Cinema.class, id);
        if (cinema == null)
            throw new NoSuchEntityException();
        return null;
    }
}
