package pl.com.piotrslowinski.model;

import pl.com.piotrslowinski.model.commands.SetTicketPricesCommand;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "movies")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> actors;

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> genres;

    @Column(name = "min_age")
    private Integer minAge;

    private Integer length;

    @OneToMany
    @JoinColumn(name = "movie_id")
    private Set<Show> shows = new HashSet<>();

    @Embedded
    private TicketPrices ticketPrices;

    public Movie(String title, String description, Set<String> actors, Set<String> genres, Integer minAge, Integer length) {
        this.title = title;
        this.description = description;
        this.actors = actors;
        this.genres = genres;
        this.minAge = minAge;
        this.length = length;
    }

    public Movie() {
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Set<String> getActors() {
        return actors;
    }

    public Set<String> getGenres() {
        return genres;
    }

    public Integer getMinAge() {
        return minAge;
    }

    public Integer getLength() {
        return length;
    }

    public Set<Show> getShows() {
        return shows;
    }

    public TicketPrices getTicketPrices() {
        return ticketPrices;
    }

    public void setTicketPrices(TicketPrices ticketPrices) {
        this.ticketPrices = ticketPrices;
    }

    public void setPrices(SetTicketPricesCommand cmd) {
        if(ticketPrices != null){
            ticketPrices.setPrices(cmd.getPrices());
        } else {
            ticketPrices = new TicketPrices(cmd.getPrices());
        }

    }

    public Receipt calculatePrice(Set<Ticket> tickets) {
        return ticketPrices.calculatePrice(tickets);
    }
}
