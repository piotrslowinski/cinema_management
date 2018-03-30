package pl.com.piotrslowinski.model;

import pl.com.piotrslowinski.model.commands.CreateReservationCommand;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "reservations")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long showId;

    @Embedded
    private Customer Customer;

    @ElementCollection
    private Set<Ticket> tickets;

    @ElementCollection
    private Set<Seat> seats;

    @Enumerated(EnumType.STRING)
    private ReservationStatus reservationStatus;
    private BigDecimal totalCost;

    public Reservation() {
    }

    public Reservation(CreateReservationCommand cmd) {
        this.showId = cmd.getShowId();
        Customer = cmd.getCustomer();
        this.tickets = cmd.getTickets();
        this.seats = cmd.getSeats();
        this.reservationStatus = ReservationStatus.PENDING;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getShowId() {
        return showId;
    }

    public void setShowId(Long showId) {
        this.showId = showId;
    }

    public Customer getCustomer() {
        return Customer;
    }

    public void setCustomer(Customer customer) {
        Customer = customer;
    }

    public Set<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(Set<Ticket> tickets) {
        this.tickets = tickets;
    }

    public Set<Seat> getSeats() {
        return seats;
    }

    public void setSeats(Set<Seat> seats) {
        this.seats = seats;
    }

    public ReservationStatus getReservationStatus() {
        return reservationStatus;
    }

    public void setReservationStatus(ReservationStatus reservationStatus) {
        this.reservationStatus = reservationStatus;
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }
}
