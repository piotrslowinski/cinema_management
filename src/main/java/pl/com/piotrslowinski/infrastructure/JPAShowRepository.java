package pl.com.piotrslowinski.infrastructure;

import org.springframework.stereotype.Component;
import pl.com.piotrslowinski.model.Show;
import pl.com.piotrslowinski.model.repositories.ShowRepository;

import javax.persistence.EntityManager;

@Component
public class JPAShowRepository implements ShowRepository {

    private EntityManager entityManager;

    public JPAShowRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void save(Show show) {
        entityManager.persist(show);
    }
}
