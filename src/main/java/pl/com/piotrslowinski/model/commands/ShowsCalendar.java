package pl.com.piotrslowinski.model.commands;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Set;

public class ShowsCalendar {

    LocalDateTime fromDate, untilDate;

    Set<String> weekDays;

    Set<LocalTime> hours;

    public LocalDateTime getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDateTime fromDate) {
        this.fromDate = fromDate;
    }

    public LocalDateTime getUntilDate() {
        return untilDate;
    }

    public void setUntilDate(LocalDateTime untilDate) {
        this.untilDate = untilDate;
    }

    public Set<String> getWeekDays() {
        return weekDays;
    }

    public void setWeekDays(Set<String> weekDays) {
        this.weekDays = weekDays;
    }

    public Set<LocalTime> getHours() {
        return hours;
    }

    public void setHours(Set<LocalTime> hours) {
        this.hours = hours;
    }
}
