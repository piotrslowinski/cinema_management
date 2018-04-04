package pl.com.piotrslowinski.infrastructure;

import org.junit.After;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.support.TransactionTemplate;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class InfrastructureTest {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private TransactionTemplate tt;

    @After
    public void cleanUp(){
        tt.execute((c) -> {

            em.createNativeQuery("DELETE FROM movie_actors").executeUpdate();
            em.createNativeQuery("DELETE FROM movie_genres").executeUpdate();
            em.createNativeQuery("DELETE FROM movie_prices").executeUpdate();
            em.createNativeQuery("DELETE FROM movies").executeUpdate();
            em.createNativeQuery("DELETE FROM shows").executeUpdate();
            em.createNativeQuery("DELETE FROM cinemas").executeUpdate();
        return null;
        });
    }

}
