package pl.com.piotrslowinski.model.commands;

public class CreateCinemaCommand implements Command {

    private String name, city;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void validate(Validatable.ValidationErrors errors){
        validatePresence(errors, "name", name);
        validatePresence(errors, "city", city);
    }


}
