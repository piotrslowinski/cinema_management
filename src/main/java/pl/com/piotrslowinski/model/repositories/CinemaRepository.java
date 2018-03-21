package pl.com.piotrslowinski.model.repositories;

import pl.com.piotrslowinski.model.Cinema;

public interface CinemaRepository {

    void save(Cinema cinema);

    Cinema get(Long id);
}
