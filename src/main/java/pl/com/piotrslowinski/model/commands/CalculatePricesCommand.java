package pl.com.piotrslowinski.model.commands;

import pl.com.piotrslowinski.model.Ticket;

import java.util.Set;

public class CalculatePricesCommand implements Command{

    private Long showId;

    private Set<Ticket> tickets;

    public Long getShowId() {
        return showId;
    }

    public void setShowId(Long showId) {
        this.showId = showId;
    }

    public Set<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(Set<Ticket> tickets) {
        this.tickets = tickets;
    }
}
