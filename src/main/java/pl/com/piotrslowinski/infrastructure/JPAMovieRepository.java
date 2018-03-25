package pl.com.piotrslowinski.infrastructure;

import org.springframework.stereotype.Component;
import pl.com.piotrslowinski.model.Movie;
import pl.com.piotrslowinski.model.repositories.MovieRepository;

import javax.persistence.EntityManager;

@Component
public class JPAMovieRepository implements MovieRepository {

    private EntityManager entityManager;

    public JPAMovieRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void save(Movie movie) {
        entityManager.persist(movie);
    }

    @Override
    public Movie get(Long movieId) {
        Movie movie = entityManager.find(Movie.class, movieId);
        if (movie == null)
            throw new NoSuchEntityException();
        return movie;
    }
}
