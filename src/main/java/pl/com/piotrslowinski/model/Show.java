package pl.com.piotrslowinski.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "shows")
public class Show {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Cinema cinema;

    @ManyToOne(fetch = FetchType.EAGER)
    private Movie movie;

    private LocalDateTime date;

    public Show() {
    }

    public Show(Cinema cinema, Movie movie, LocalDateTime date) {
        this.cinema = cinema;
        this.movie = movie;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cinema getCinema() {
        return cinema;
    }

    public void setCinema(Cinema cinema) {
        this.cinema = cinema;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Receipt calculatePrice(Set<Ticket> tickets) {
        return movie.calculatePrice(tickets);
    }
}
