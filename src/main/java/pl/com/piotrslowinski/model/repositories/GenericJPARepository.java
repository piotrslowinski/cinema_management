package pl.com.piotrslowinski.model.repositories;

import pl.com.piotrslowinski.infrastructure.NoSuchEntityException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.lang.reflect.ParameterizedType;

public abstract class GenericJPARepository<T> implements Repository<T> {

    @PersistenceContext
    protected EntityManager entityManager;

    private Class<T> clazz;

    public GenericJPARepository() {
        this.clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public void save(T t){
        entityManager.persist(t);
    }

    public T get(Long id){
        T t = entityManager.find(clazz, id);
        if(t == null)
            throw new NoSuchEntityException();
        return t;
    }
}
