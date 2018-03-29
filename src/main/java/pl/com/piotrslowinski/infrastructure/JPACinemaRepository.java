package pl.com.piotrslowinski.infrastructure;

import org.springframework.stereotype.Component;
import pl.com.piotrslowinski.model.Cinema;
import pl.com.piotrslowinski.model.repositories.CinemaRepository;
import pl.com.piotrslowinski.model.repositories.GenericJPARepository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.Optional;

@Component
public class JPACinemaRepository extends GenericJPARepository<Cinema> implements CinemaRepository {


    @Override
    public boolean isCinemaPresent(String name, String city) {
        return get(name, city).isPresent();
    }

    public Optional<Cinema> get(String name, String city){
        try {
            Cinema cinema = (Cinema) entityManager.createQuery("SELECT c FROM Cinema c WHERE c.name =:name AND c.city =:city")
                    .setParameter("name", name)
                    .setParameter("city", city)
                    .getSingleResult();
            return Optional.of(cinema);
        } catch (NoResultException ex){
            return Optional.empty();

        }
    }
}
