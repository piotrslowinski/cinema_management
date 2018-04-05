package pl.com.piotrslowinski.domain;

import org.junit.Test;
import pl.com.piotrslowinski.model.Movie;
import pl.com.piotrslowinski.model.commands.SetTicketPricesCommand;

import java.math.BigDecimal;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class SetTicketPricesTest{

        private final Movie movie = new Movie();
        private final HashMap<String, BigDecimal> prices = new HashMap<>();
        private final  HashMap<String, BigDecimal> prices2 = new HashMap<>();
        private final SetTicketPricesCommand cmd = new SetTicketPricesCommand();

        @Test
        public void shouldSetTicketPrices() {
            prices.put("regular", BigDecimal.valueOf(20));
            prices.put("student", BigDecimal.valueOf(10));
            prices.put("school", BigDecimal.valueOf(10));
            prices.put("children", BigDecimal.valueOf(5));

            cmd.setMovieId(1L);
            cmd.setPrices(prices);
            movie.setPrices(cmd);

            assertEquals(prices, movie.getTicketPrices().getPrices());
        }

        @Test
        public void shouldOverridePreviouslyPrices(){
            prices.put("regular", BigDecimal.valueOf(20));
            prices.put("student", BigDecimal.valueOf(10));
            prices.put("school", BigDecimal.valueOf(10));
            prices.put("children", BigDecimal.valueOf(5));

            cmd.setMovieId(1L);
            cmd.setPrices(prices);
            movie.setPrices(cmd);
            prices.put("regular", BigDecimal.valueOf(50));
            prices.put("student", BigDecimal.valueOf(10));
            prices.put("school", BigDecimal.valueOf(30));
            prices.put("children", BigDecimal.valueOf(5));
            cmd.setMovieId(1L);
            cmd.setPrices(prices2);
            movie.setPrices(cmd);

            assertEquals(prices2, movie.getTicketPrices().getPrices());
        }

}

