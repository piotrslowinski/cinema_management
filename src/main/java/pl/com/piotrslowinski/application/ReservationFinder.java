package pl.com.piotrslowinski.application;

import java.util.List;

public interface ReservationFinder {

    List<ReservationDto> search(ReservationQuery query);
}
