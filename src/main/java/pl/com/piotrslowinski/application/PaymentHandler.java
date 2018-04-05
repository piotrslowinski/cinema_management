package pl.com.piotrslowinski.application;

import jdk.internal.org.objectweb.asm.Handle;
import org.springframework.stereotype.Component;

import pl.com.piotrslowinski.model.PaymentFacade;
import pl.com.piotrslowinski.model.PaymentStatus;
import pl.com.piotrslowinski.model.Reservation;
import pl.com.piotrslowinski.model.ReservationStatus;
import pl.com.piotrslowinski.model.commands.Command;
import pl.com.piotrslowinski.model.commands.PaymentCommand;
import pl.com.piotrslowinski.model.repositories.Repository;

import javax.transaction.Transactional;

@Component
@Transactional
public class PaymentHandler implements Handler<PaymentCommand, PaymentStatus> {

    private PaymentFacade paymentFacade;

    private Repository repository;

    public PaymentHandler(PaymentFacade paymentFacade, Repository<Reservation> repository) {
        this.paymentFacade = paymentFacade;
        this.repository = repository;
    }

    public PaymentStatus handle(PaymentCommand cmd){
        Reservation reservation = (Reservation) repository.get(cmd.getReservationNumber());
        PaymentStatus paymentStatus = new PaymentStatus();
        if(!isPaidOrFailed(reservation)){
            return cancelPayment(paymentStatus, "Reservation is already paid");
        }
        paymentStatus = paymentFacade.processPayment(cmd, reservation);

        if(!paymentStatus.isSuccess())
            reservation.markAsPaymentFailed();

        reservation.markAsPaid();
        return paymentStatus;
    }

    private PaymentStatus cancelPayment(PaymentStatus paymentStatus, String reason){
        paymentStatus.setSuccess(false);
        paymentStatus.setMessage(reason);
        return paymentStatus;
    }

    private boolean isPaidOrFailed(Reservation reservation){
        return reservation.getReservationStatus().compareTo(ReservationStatus.PENDING) == 0 ||
                reservation.getReservationStatus().compareTo(ReservationStatus.PAYMENT_FAILED) == 0;
    }

    @Override
    public Class<? extends Command> getSupportedCommandClass() {
        return PaymentCommand.class;
    }
}
