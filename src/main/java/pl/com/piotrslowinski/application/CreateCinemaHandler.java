package pl.com.piotrslowinski.application;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.com.piotrslowinski.model.Cinema;
import pl.com.piotrslowinski.model.commands.Command;
import pl.com.piotrslowinski.model.commands.CreateCinemaCommand;
import pl.com.piotrslowinski.model.repositories.CinemaRepository;

@Component
public class CreateCinemaHandler implements Handler<CreateCinemaCommand, Void> {

    private CinemaRepository cinemaRepository;


    public CreateCinemaHandler(CinemaRepository cinemaRepository) {
        this.cinemaRepository = cinemaRepository;
    }

    @Transactional
   public Void handle(CreateCinemaCommand cmd){
        cinemaRepository.save(new Cinema(cmd.getName(), cmd.getCity()));
        return null;
   }

    @Override
    public Class<? extends Command> getSupportedCommandClass() {
        return CreateCinemaCommand.class;
    }

}
