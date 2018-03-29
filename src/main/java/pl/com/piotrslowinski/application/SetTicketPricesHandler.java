package pl.com.piotrslowinski.application;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.com.piotrslowinski.model.Movie;
import pl.com.piotrslowinski.model.commands.Command;
import pl.com.piotrslowinski.model.commands.SetTicketPricesCommand;
import pl.com.piotrslowinski.model.repositories.MovieRepository;

@Component
public class SetTicketPricesHandler implements Handler<SetTicketPricesCommand, Void> {

    private MovieRepository movieRepository;

    public SetTicketPricesHandler(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Transactional
    @Override
    public Void handle(SetTicketPricesCommand cmd) {
        Movie movie = movieRepository.get(cmd.getMovieId());
        movie.setPrices(cmd);
        movieRepository.save(movie);
        return null;
    }

    @Override
    public Class<? extends Command> getSupportedCommandClass() {
        return SetTicketPricesCommand.class;
    }
}
