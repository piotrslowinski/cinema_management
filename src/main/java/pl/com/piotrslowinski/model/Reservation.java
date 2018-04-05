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
    private Customer customer;

    @ElementCollection
    private Set<Ticket> tickets;

    @ElementCollection
    private Set<Seat> seats;

    @Enumerated(EnumType.STRING)
    private ReservationStatus reservationStatus;
    private BigDecimal totalCost;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH} )
    private Set<PaymentTransaction> transactions;

    public Reservation() {
    }

    public Reservation(CreateReservationCommand cmd, BigDecimal totalCost) {
        this.showId = cmd.getShowId();
        this.customer = cmd.getCustomer();
        this.tickets = cmd.getTickets();
        this.seats = cmd.getSeats();
        this.reservationStatus = ReservationStatus.PENDING;
        this.totalCost = totalCost;
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
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
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

    public void addTransaction(PaymentTransaction transaction) {
        this.transactions.add(transaction);
    }

    public void markAsPaymentFailed() {
        this.reservationStatus = ReservationStatus.PAYMENT_FAILED;
    }

    public void markAsPaid() {
        this.reservationStatus = ReservationStatus.PAID;
    }
}
