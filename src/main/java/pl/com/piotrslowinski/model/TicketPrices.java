package pl.com.piotrslowinski.model;

import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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

    public Receipt calculatePrice(Set<Ticket> tickets) {
        Receipt receipt = new Receipt();
        for (Ticket ticket: tickets){
            if (!getTicketPrice(ticket.getKind()).equals(TICKET_KIND_NOT_EXIST)){
                receipt.addReceiptLine(ticket.getKind(), ticket.getCount(), getTicketPrice(ticket.getKind()));
            }
        }

        receipt.calculateTotalPrice();
        return receipt;
    }

    private BigDecimal getTicketPrice(String kind) {
        if(prices.containsKey(kind))
            return prices.get(kind);
        return TICKET_KIND_NOT_EXIST;
    }
}
