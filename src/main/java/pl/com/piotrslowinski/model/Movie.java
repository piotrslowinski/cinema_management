package pl.com.piotrslowinski.model;

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

    private String descripton;

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

    public Movie(String title, String description, Set<String> actors, Set<String> genres, Integer minAge, Integer length) {
        this.title = title;
        this.descripton = description;
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
}
