package pl.com.piotrslowinski.model.repositories;

import pl.com.piotrslowinski.model.Cinema;
import pl.com.piotrslowinski.model.Movie;

public interface MovieRepository {

    void save(Movie movie);

    Movie get(Long movieId);
}
