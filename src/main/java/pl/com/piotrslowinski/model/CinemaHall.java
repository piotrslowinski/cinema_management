package pl.com.piotrslowinski.model;

import pl.com.piotrslowinski.model.commands.CreateReservationCommand;
import pl.com.piotrslowinski.model.commands.Validatable;

import java.util.Set;

public class CinemaHall {

    private final int ROWS = 10;
    private final int SEATS = 15;

    private boolean[][] seats = new boolean[ROWS][SEATS];

    public CinemaHall(Set<Reservation> currentReservations){
        for(Reservation reservation : currentReservations){
            Set<Seat> reservedSeats = reservation.getSeats();
            for(Seat seat: reservedSeats){
                seats[seat.getRow() - 1][seat.getSeat() - 1] = true;
            }
        }
    }

    public CinemaHall(boolean[][] seats){
        this.seats = seats;
    }

    public int getROWS() {
        return ROWS;
    }

    public int getSEATS() {
        return SEATS;
    }

    public boolean[][] getSeats() {
        return seats;
    }

    public void checkReservation(CreateReservationCommand cmd) {
        Validatable.ValidationErrors errors = new Validatable.ValidationErrors();
        //TODO
    }
}
