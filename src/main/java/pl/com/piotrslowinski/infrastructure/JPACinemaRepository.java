package pl.com.piotrslowinski.infrastructure;

import org.springframework.stereotype.Component;
import pl.com.piotrslowinski.model.Cinema;
import pl.com.piotrslowinski.model.repositories.CinemaRepository;
import pl.com.piotrslowinski.model.repositories.GenericJPARepository;

import javax.persistence.EntityManager;

@Component
public class JPACinemaRepository extends GenericJPARepository<Cinema> implements CinemaRepository {



}
