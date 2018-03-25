package pl.com.piotrslowinski.model;

import pl.com.piotrslowinski.model.commands.CreateShowsCommand;
import pl.com.piotrslowinski.model.repositories.CinemaRepository;
import pl.com.piotrslowinski.model.repositories.MovieRepository;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Set;

public class ShowFactory {

    private CinemaRepository cinemaRepository;

    private MovieRepository movieRepository;

    public ShowFactory(CinemaRepository cinemaRepository, MovieRepository movieRepository) {
        this.cinemaRepository = cinemaRepository;
        this.movieRepository = movieRepository;
    }

    public Collection<Show> createShows(CreateShowsCommand cmd){
        Cinema cinema = cinemaRepository.get(cmd.getCinemaId());
        Movie movie = movieRepository.get(cmd.getMovieId());
        Collection<Show> showList = new LinkedList<>();
        if(cmd.getCalendar() == null){
            createShowWithoutCalendar(cmd, cinema, movie, showList);
        } else {
            LocalDateTime fromDate = cmd.getCalendar().getFromDate();
            LocalDateTime toDate = cmd.getCalendar().getUntilDate();
            Set<String> weekDays = cmd.getCalendar().getWeekDays();
            Set<LocalTime> hours = cmd.getCalendar().getHours();
            createShowWithCalendar(cinema, movie, showList, fromDate, toDate, weekDays, hours);
        }

        return showList;
    }

    private void createShowWithCalendar(Cinema cinema, Movie movie, Collection<Show> showList, LocalDateTime fromDate,
                                        LocalDateTime toDate, Set<String> weekDays, Set<LocalTime> hours) {
        for (LocalDateTime date = fromDate; date.isBefore(toDate.plusDays(1L)); date = date.plusDays(1L)){
            for(String day : weekDays){
                if(isTheSameDay(date, day)){
                    for(LocalTime hour : hours){
                        Show show = new Show(cinema, movie, LocalDateTime.of(date.toLocalDate(), hour));
                        showList.add(show);
                    }
                }
            }
        }
    }

    private boolean isTheSameDay(LocalDateTime date, String day) {
        return date.getDayOfWeek().name().equals(day.toUpperCase());
    }

    private void createShowWithoutCalendar(CreateShowsCommand cmd, Cinema cinema, Movie movie, Collection<Show> showList) {
        cmd.getDates().stream().forEach(dateTime -> {
            Show show = new Show(cinema, movie, dateTime);
            showList.add(show);
        });
    }


}
