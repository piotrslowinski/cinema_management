package pl.com.piotrslowinski.application;

import com.fasterxml.jackson.annotation.JsonFormat;
import pl.com.piotrslowinski.model.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

public class ReservationDto {

    private Long number;

    private ReservedShowDto show;

    private ReservedMovieDto movie;

    private Set<Receipt.ReceiptLine> tickets;

    private Set<Seat> seats;

    private CustomerDto customer;

    private ReservationStatus status;

    private BigDecimal totalPrice;

    public ReservationDto(Reservation reservation, Show show) {
        this.number = reservation.getId();
        this.show = new ReservedShowDto(show);
        this.movie = new ReservedMovieDto(show.getMovie());
        this.tickets = show.calculatePrice(reservation.getTickets()).getTickets();
        this.seats = reservation.getSeats();
        this.customer = new CustomerDto(reservation.getCustomer());
        this.status = reservation.getReservationStatus();
        this.totalPrice = reservation.getTotalCost();
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public ReservedShowDto getShow() {
        return show;
    }

    public void setShow(ReservedShowDto show) {
        this.show = show;
    }

    public ReservedMovieDto getMovie() {
        return movie;
    }

    public void setMovie(ReservedMovieDto movie) {
        this.movie = movie;
    }

    public Set<Receipt.ReceiptLine> getTickets() {
        return tickets;
    }

    public void setTickets(Set<Receipt.ReceiptLine> tickets) {
        this.tickets = tickets;
    }

    public Set<Seat> getSeats() {
        return seats;
    }

    public void setSeats(Set<Seat> seats) {
        this.seats = seats;
    }

    public CustomerDto getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDto customer) {
        this.customer = customer;
    }

    public ReservationStatus getStatus() {
        return status;
    }

    public void setStatus(ReservationStatus status) {
        this.status = status;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    private class ReservedShowDto {

        private Long id;

        @JsonFormat(pattern = "yyyy/MM/dd HH:mm")
       private LocalDateTime time;

        public ReservedShowDto(Show show) {
            this.id = show.getId();
            this.time = show.getDate();
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public LocalDateTime getTime() {
            return time;
        }

        public void setTime(LocalDateTime time) {
            this.time = time;
        }
    }


    private class ReservedMovieDto {

        private Long id;

        private String title;

        public ReservedMovieDto(Movie movie) {
            this.id = movie.getId();
            this.title = movie.getTitle();
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }

    private class CustomerDto {

        private String firstName;

        private String lastName;

        private String email;

        private String phone;

        public CustomerDto(Customer customer) {
            this.firstName = customer.getFirstName();
            this.lastName = customer.getLastName();
            this.email = customer.getEmail();
            this.phone = customer.getPhone();
        }


    }
}
