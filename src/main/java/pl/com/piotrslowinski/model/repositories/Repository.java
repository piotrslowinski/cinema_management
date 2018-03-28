package pl.com.piotrslowinski.model.repositories;

public interface Repository<T> {

    void save(T t);

    T get(Long id);
}
