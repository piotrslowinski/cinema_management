package pl.com.piotrslowinski.application;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import pl.com.piotrslowinski.model.commands.Command;

import java.util.Map;
import java.util.Optional;

@Component
public class CommandGateway {

    private ApplicationContext applicationContext;


    public CommandGateway(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public void execute(Command command){
        validate(command);
        Handler handler = handlerFor(command);
        handler.handle(command);
    }

    private  void validate(Command command){
       //TODO =
    }

    private Handler handlerFor(Command command){
        Map<String, Handler> handlers = applicationContext.getBeansOfType(Handler.class);
        Optional<Handler> handlerFor = handlers.values().stream().filter((h) -> h.canHandle(command)).findFirst();
        return handlerFor.orElseThrow( () ->
                new IllegalArgumentException("No handler found for " + command.getClass()));
    }
}
