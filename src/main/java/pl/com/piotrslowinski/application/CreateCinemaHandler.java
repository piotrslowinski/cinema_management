package pl.com.piotrslowinski.application;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.com.piotrslowinski.model.Cinema;
import pl.com.piotrslowinski.model.commands.Command;
import pl.com.piotrslowinski.model.commands.CreateCinemaCommand;
import pl.com.piotrslowinski.model.commands.InvalidCommandException;
import pl.com.piotrslowinski.model.commands.Validatable;
import pl.com.piotrslowinski.model.repositories.CinemaRepository;

@Component
public class CreateCinemaHandler implements Handler<CreateCinemaCommand, Void> {

    private CinemaRepository cinemaRepository;


    public CreateCinemaHandler(CinemaRepository cinemaRepository) {
        this.cinemaRepository = cinemaRepository;
    }

    @Transactional
   public Void handle(CreateCinemaCommand cmd){
        validateCinemaPresence(cmd);
        cinemaRepository.save(new Cinema(cmd.getName(), cmd.getCity()));
        return null;
   }

   private void validateCinemaPresence(CreateCinemaCommand cmd){
        if (cinemaRepository.isCinemaPresent(cmd.getName(), cmd.getCity())){
            Validatable.ValidationErrors errors = new Validatable.ValidationErrors();
            errors.add("cinema", "Cinema already exist");
            throw new InvalidCommandException(errors);
        }
   }

    @Override
    public Class<? extends Command> getSupportedCommandClass() {
        return CreateCinemaCommand.class;
    }

}
