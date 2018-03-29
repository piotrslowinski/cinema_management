package pl.com.piotrslowinski.application;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.com.piotrslowinski.model.Movie;
import pl.com.piotrslowinski.model.commands.Command;
import pl.com.piotrslowinski.model.commands.SetTicketPricesCommand;
import pl.com.piotrslowinski.model.repositories.MovieRepository;

@Component
public class SetTicketPricesHandler implements Handler<SetTicketPricesCommand> {

    private MovieRepository movieRepository;

    public SetTicketPricesHandler(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Transactional
    @Override
    public void handle(SetTicketPricesCommand cmd) {
        Movie movie = movieRepository.get(cmd.getMovieId());
        movie.setPrices(cmd);
        movieRepository.save(movie);

    }

    @Override
    public Class<? extends Command> getSupportedCommandClass() {
        return SetTicketPricesCommand.class;
    }
}
