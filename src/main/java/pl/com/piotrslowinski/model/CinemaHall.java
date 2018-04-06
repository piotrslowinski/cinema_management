package pl.com.piotrslowinski.model;

import pl.com.piotrslowinski.model.commands.CreateReservationCommand;
import pl.com.piotrslowinski.model.commands.InvalidCommandException;
import pl.com.piotrslowinski.model.commands.Validatable;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
        checkSeatsAvailability(errors, cmd.getSeats());
        seatsAreInTheSameRow(errors, cmd.getSeats());
        seatAreNextToEachOther(errors, cmd.getSeats());

        if (!errors.isValid())
            throw new InvalidCommandException(errors);

    }

    private void seatsAreInTheSameRow(Validatable.ValidationErrors errors, Set<Seat> seats) {
        List<Integer> tmpList = seats.stream().map(s -> s.getRow()).collect(Collectors.toList());
        boolean allRowsEqual = new HashSet<Integer>(tmpList).size() <= 1;
        if (!allRowsEqual) {
            checkAbilityToReserveSeatsToAnotherPlace(errors, seats);
        }
    }

    private void seatAreNextToEachOther(Validatable.ValidationErrors errors, Set<Seat> seats) {
        List<Integer> seatsNumbers = seats.stream().map(s -> s.getSeat()).sorted().collect(Collectors.toList());
        for (int i = seatsNumbers.get(0); i < seatsNumbers.size() - 1; i++) {
            if (seatsNumbers.get(i) + 1 != seatsNumbers.get(i + 1)) {
                checkAbilityToReserveSeatsToAnotherPlace(errors, seats);
            }
        }
    }

    private void checkAbilityToReserveSeatsToAnotherPlace(Validatable.ValidationErrors errors, Set<Seat> seats) {
        if (seatsAreAvailebleInOtherPlace(seats.size())) {
            errors.add("Seats", "The seats next to each other are available in a other row ");
        }
    }

    private boolean seatsAreAvailebleInOtherPlace(int seatsCount) {
        for (int i = 0; i < seats.length; i++) {
            int tmpCount = 0;
            for (int k = 0; k < seats[i].length; k++) {
                if (seats[i][k])
                    tmpCount = 0;
                else
                    tmpCount += 1;
            }
            if (tmpCount >= seatsCount)
                return true;
        }
        return false;
    }


    private void checkSeatsAvailability(Validatable.ValidationErrors errors, Set<Seat> seats) {
        for (Seat seat : seats) {
            Integer row = seat.getRow();
            Integer seatNo = seat.getSeat();
            if (this.seats[row - 1][seatNo - 1]) {
                errors.add("Seat no " + seatNo + " in row " + row + " ", "is already reserved ");
            }
        }

    }
}
