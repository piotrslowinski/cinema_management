package pl.com.piotrslowinski.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "shows")
public class Show {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany
    private Cinema cinema;

    @ManyToMany(fetch = FetchType.EAGER)
    private Movie movie;

    private LocalDateTime date;

    public Show() {
    }

    public Show(Cinema cinema, Movie movie, LocalDateTime date) {
        this.cinema = cinema;
        this.movie = movie;
        this.date = date;
    }
}
