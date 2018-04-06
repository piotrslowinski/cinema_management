package pl.com.piotrslowinski.ui;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.com.piotrslowinski.infrastructure.NoSuchEntityException;
import pl.com.piotrslowinski.model.commands.InvalidCommandException;

@ControllerAdvice
public class ErrorHandlers {



    @ResponseStatus(code = HttpStatus.NOT_FOUND,
            reason = "Entity with given id does not exist")
    @ExceptionHandler(NoSuchEntityException.class)
    public void handleEntityNotFound() {

    }

    @ExceptionHandler(InvalidCommandException.class)
    public ResponseEntity handleInvalidCommand(InvalidCommandException ex) {
        return new ResponseEntity(ex.getErrors(), HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
