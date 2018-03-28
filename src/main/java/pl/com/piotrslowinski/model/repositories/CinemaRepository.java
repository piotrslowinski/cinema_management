package pl.com.piotrslowinski.model.repositories;

import pl.com.piotrslowinski.model.Cinema;

public interface CinemaRepository extends Repository<Cinema> {

    void save(Cinema cinema);

    Cinema get(Long id);
}
