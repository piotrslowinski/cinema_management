package pl.com.piotrslowinski.infrastructure;

import org.springframework.stereotype.Component;
import pl.com.piotrslowinski.model.Show;
import pl.com.piotrslowinski.model.repositories.GenericJPARepository;
import pl.com.piotrslowinski.model.repositories.ShowRepository;

import javax.persistence.EntityManager;

@Component
public class JPAShowRepository extends GenericJPARepository<Show> implements ShowRepository {


}
