package pl.com.piotrslowinski.model;

import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Embeddable
public class TicketPrices {

    public static final BigDecimal TICKET_KIND_NOT_EXIST = BigDecimal.valueOf(-1);

    @ElementCollection(fetch = FetchType.EAGER)
    private Map<String, BigDecimal> prices = new HashMap<>();

    @OneToOne
    private Movie movie;

    public TicketPrices() {
    }

    public TicketPrices(Map<String, BigDecimal> prices) {
        this.prices = prices;
    }

    public Map<String, BigDecimal> getPrices() {
        return prices;
    }

    public void setPrices(Map<String, BigDecimal> prices) {
        this.prices = prices;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }
}
