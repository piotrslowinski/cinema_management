package pl.com.piotrslowinski.model.commands;

import pl.com.piotrslowinski.model.CreditCardData;
import pl.com.piotrslowinski.model.PaymentType;

public class PaymentCommand implements Command {

    private Long reservationNumber;

    private PaymentType type;

    private CreditCardData creditCardData;

    public Long getReservationNumber() {
        return reservationNumber;
    }

    public void setReservationNumber(Long reservationNumber) {
        this.reservationNumber = reservationNumber;
    }
}
