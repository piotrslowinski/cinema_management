package pl.com.piotrslowinski.application;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.com.piotrslowinski.model.ShowFactory;
import pl.com.piotrslowinski.model.commands.Command;
import pl.com.piotrslowinski.model.commands.CreateShowsCommand;
import pl.com.piotrslowinski.model.repositories.ShowRepository;

@Component
public class CreateShowsHandler implements Handler<CreateShowsCommand, Void> {

    private ShowRepository showRepository;
    private ShowFactory showFactory;

    public CreateShowsHandler(ShowRepository showRepository, ShowFactory showFactory) {
        this.showRepository = showRepository;
        this.showFactory = showFactory;
    }

    @Transactional
    @Override
    public Void handle(CreateShowsCommand command) {
        showFactory.createShows(command).stream().forEach(show -> showRepository.save(show));
        return null;
    }

    @Override
    public Class<? extends Command> getSupportedCommandClass() {
        return CreateShowsCommand.class;
    }
}
