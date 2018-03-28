package pl.com.piotrslowinski.application;

import java.time.LocalDate;
import java.util.List;

public interface MovieFinder {

    List<MovieDto> getFromDay(Long cinemaId, LocalDate parse);

}
