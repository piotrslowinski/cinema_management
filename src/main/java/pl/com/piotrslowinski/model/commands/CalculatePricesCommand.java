package pl.com.piotrslowinski.model.commands;

import com.sun.deploy.security.ValidationState;
import pl.com.piotrslowinski.model.Ticket;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

    public void validate(Validatable.ValidationErrors errors){
        validatePresence(errors, "showId", showId);
        validatePresence(errors, "tickets", tickets);
        validateNumber(errors, showId);
        validateTicketPresence(errors, tickets);
        validateTicketsUniqueness(errors, tickets);
        validateTicketCount(errors, tickets);
        validateTicketFields(errors, tickets);
        validateTicketType(errors, tickets, "regular");
    }

    private void validateTicketType(Validatable.ValidationErrors errors, Set<Ticket> tickets, String type) {
        if(tickets != null){
            if(!(tickets.stream().map(Ticket::getKind).filter(k -> k.equals(type)).findFirst()).isPresent())
                errors.add("ticketKind", "Invalid ticket kind format");

        }
    }

    private void validateTicketFields(Validatable.ValidationErrors errors, Set<Ticket> tickets) {
        if(tickets != null){
            for (Ticket ticket: tickets){
                if (ticket.getCount() == null)
                    errors.add("count", "ticket count can't be null");
                if (ticket.getKind() == null)
                    errors.add("kind", "ticket kind can't be null");
            }
        }
    }

    private void validateTicketCount(Validatable.ValidationErrors errors, Set<Ticket> tickets) {
        if (tickets != null){
            for (Ticket ticket: tickets) {
                if (ticket.getCount() != null) {
                    if (ticket.getCount() <= 0)
                        errors.add("count", "ticket count must be greater than zero");
                }
            }
        }
    }

    private void validateTicketsUniqueness(Validatable.ValidationErrors errors, Set<Ticket> tickets) {
        if (tickets != null){
            List<String> ticketKinds = tickets.stream().map(Ticket:: getKind).collect(Collectors.toList());
            boolean isTicketKindUnique = new HashSet<String>(ticketKinds).size() == ticketKinds.size();
            if (!isTicketKindUnique){
                errors.add("tickets", "Tickets kinds can't repeat");
            }
        }
    }

    private void validateNumber(Validatable.ValidationErrors errors, Long showId) {
        if( !(tickets == null)){
            if(showId <= 0)
                errors.add("showId", "must be gt zero");
        }
    }


    private void validateTicketPresence(Validatable.ValidationErrors errors, Set<Ticket> tickets) {
        if (tickets != null) {
            if (tickets.isEmpty() || tickets.size() == 0)
                errors.add("tickets", "cannot be empty");
        }

    }
}
