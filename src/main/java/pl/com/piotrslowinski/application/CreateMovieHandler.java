package pl.com.piotrslowinski.application;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.com.piotrslowinski.model.Movie;
import pl.com.piotrslowinski.model.commands.Command;
import pl.com.piotrslowinski.model.commands.CreateMovieCommand;
import pl.com.piotrslowinski.model.repositories.MovieRepository;

@Component
public class CreateMovieHandler implements Handler<CreateMovieCommand, Void> {

    private MovieRepository movieRepository;

    public CreateMovieHandler(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Transactional
    public Void handle(CreateMovieCommand cmd){
        movieRepository.save(new Movie(cmd.getTitle(), cmd.getDescription(), cmd.getActors(),
                cmd.getGenres(), cmd.getMinAge(), cmd.getLength()));
        return null;
    }

    @Override
    public Class<? extends Command> getSupportedCommandClass() {
        return CreateMovieCommand.class;
    }
}
