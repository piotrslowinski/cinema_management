package pl.com.piotrslowinski.application;

import pl.com.piotrslowinski.model.Movie;


import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class MovieDto {

    private Long id;

    private String title;

    private String description;

    private Set<String> actors;

    private Set<String> genres;

    private Integer minAge;

    private Integer length;

    private List<ShowDto> shows ;

    public MovieDto(Movie movie) {
        this.id = movie.getId();
        this.title = movie.getTitle();
        this.description = movie.getDescription();
        this.actors = movie.getActors();
        this.genres = movie.getGenres();
        this.minAge = movie.getMinAge();
        this.length = movie.getLength();
        this.shows = movie.getShows().stream()
                .map(ShowDto::new)
                .sorted(Comparator.comparing(ShowDto::getTime))
                .collect(Collectors.toList());
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<String> getActors() {
        return actors;
    }

    public void setActors(Set<String> actors) {
        this.actors = actors;
    }

    public Set<String> getGenres() {
        return genres;
    }

    public void setGenres(Set<String> genres) {
        this.genres = genres;
    }

    public Integer getMinAge() {
        return minAge;
    }

    public void setMinAge(Integer minAge) {
        this.minAge = minAge;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public List<ShowDto> getShows() {
        return shows;
    }

    public void setShows(List<ShowDto> shows) {
        this.shows = shows;
    }
}
