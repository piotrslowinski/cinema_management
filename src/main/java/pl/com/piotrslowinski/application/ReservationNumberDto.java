package pl.com.piotrslowinski.application;

import pl.com.piotrslowinski.model.Reservation;

public class ReservationNumberDto {

    private String reservationNumber;

    public ReservationNumberDto(Reservation reservation) {
        this.reservationNumber = reservation.getId().toString();
    }

    public String getReservationNumber() {
        return reservationNumber;
    }

    public void setReservationNumber(String reservationNumber) {
        this.reservationNumber = reservationNumber;
    }
}
