package pl.com.piotrslowinski.model;

import pl.com.piotrslowinski.model.commands.PaymentCommand;

public interface PaymentFacade {

    PaymentStatus processPayment(PaymentCommand cmd, Reservation reservation);
}
