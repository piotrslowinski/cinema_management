package pl.com.piotrslowinski.infrastructure;

import org.springframework.stereotype.Component;
import pl.com.piotrslowinski.model.Movie;
import pl.com.piotrslowinski.model.repositories.GenericJPARepository;
import pl.com.piotrslowinski.model.repositories.MovieRepository;

import javax.persistence.EntityManager;

@Component
public class JPAMovieRepository extends GenericJPARepository<Movie> implements MovieRepository {


}
