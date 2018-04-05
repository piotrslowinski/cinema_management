package pl.com.piotrslowinski.model.commands;

import com.fasterxml.jackson.annotation.JsonFormat;
import pl.com.piotrslowinski.model.repositories.CinemaRepository;
import pl.com.piotrslowinski.model.repositories.MovieRepository;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.Set;

public class CreateShowsCommand implements Command {

    Long movieId, cinemaId;

    @JsonFormat(pattern = "yyyy/MM/dd HH:mm")
    Set<LocalDateTime> dates;

    ShowsCalendar calendar;

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public Long getCinemaId() {
        return cinemaId;
    }

    public void setCinemaId(Long cinemaId) {
        this.cinemaId = cinemaId;
    }

    public Set<LocalDateTime> getDates() {
        return dates;
    }

    public void setDates(Set<LocalDateTime> dates) {
        this.dates = dates;
    }

    public ShowsCalendar getCalendar() {
        return calendar;
    }

    public void setCalendar(ShowsCalendar calendar) {
        this.calendar = calendar;
    }

    public void validate(Validatable.ValidationErrors errors){
        validatePresence(errors, "movieId", movieId);
        validatePresence(errors, "cinemaId", cinemaId);

        if(dates == null && calendar == null){
            errors.add("dates & calendar", "enter dates or calendar");
        }

        if(dates != null && calendar != null){
            errors.add("dates & calendar", "enter dates or calendar, not both");
        }

        if(dates == null && dates.isEmpty()){
            errors.add("dates", "dates can't be empty");
        }

        if (calendar != null){
            validatePresence(errors,"calendar: fromDate", calendar.getFromDate());
            validatePresence(errors, "calendar: untilDate", calendar.getUntilDate());

            if (calendar.getHours() == null || calendar.getHours().isEmpty() ) {
                errors.add("calendar: hours ", "hours set is empty or blank");
            }
            if (calendar.getWeekDays() == null || calendar.getWeekDays().isEmpty()) {
                errors.add("calendar: weekDays ", "weekDays set is empty or blank");
            }

            validateCorrectnessWeekOfDays(errors, calendar.getWeekDays());
        }
    }

    private void validateCorrectnessWeekOfDays(Validatable.ValidationErrors errors, Set<String> weekDays) {
        if (weekDays != null && !weekDays.stream().allMatch(day -> validateDayOfWeek(day))){
            errors.add("calendar: weekDays ", "weekDays names must be real");
        }
    }

    private boolean validateDayOfWeek(String day) {
        try {
            DayOfWeek.valueOf(day.toUpperCase());
        } catch (IllegalArgumentException e){
            return false;
        }
        return true;
    }
}
