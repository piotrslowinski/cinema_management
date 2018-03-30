package pl.com.piotrslowinski.application;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.com.piotrslowinski.model.Receipt;
import pl.com.piotrslowinski.model.Show;
import pl.com.piotrslowinski.model.commands.CalculatePricesCommand;
import pl.com.piotrslowinski.model.commands.Command;
import pl.com.piotrslowinski.model.repositories.ShowRepository;

@Component
public class CalculatePricesHandler implements Handler<CalculatePricesCommand, Receipt> {

    private ShowRepository showRepository;

    public CalculatePricesHandler(ShowRepository showRepository) {
        this.showRepository = showRepository;
    }

    @Transactional
    @Override
    public Receipt handle(CalculatePricesCommand command) {
        Show show = showRepository.get(command.getShowId());
        Receipt receipt = show.calculatePrice(command.getTickets());
        return receipt;
    }

    @Override
    public Class<? extends Command> getSupportedCommandClass() {
        return CalculatePricesCommand.class;
    }
}
