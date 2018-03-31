package pl.com.piotrslowinski.infrastructure;

import pl.com.piotrslowinski.model.PaymentFacade;
import pl.com.piotrslowinski.model.PaymentStatus;
import pl.com.piotrslowinski.model.Reservation;
import pl.com.piotrslowinski.model.commands.PaymentCommand;

public class StripePaymentFacade implements PaymentFacade {

    
    @Override
    public PaymentStatus processPayment(PaymentCommand cmd, Reservation reservation) {
        return null;
    }
}
