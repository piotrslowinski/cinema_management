package pl.com.piotrslowinski.application;

import pl.com.piotrslowinski.model.Show;

import java.time.LocalTime;

public class ShowDto {

    private Long id;

    private LocalTime time;

    public ShowDto(Show show) {
        this.id = show.getId();
        this.time = show.getDate().toLocalTime();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

}
