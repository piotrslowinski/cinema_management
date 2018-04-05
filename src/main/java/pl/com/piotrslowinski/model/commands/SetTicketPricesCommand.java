package pl.com.piotrslowinski.model.commands;

import java.math.BigDecimal;
import java.util.Map;
import java.util.stream.Collectors;

public class SetTicketPricesCommand implements Command {

    private Long movieId;

    private Map<String, BigDecimal> prices;


    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public Map<String, BigDecimal> getPrices() {
        return prices;
    }

    public void setPrices(Map<String, BigDecimal> prices) {
        this.prices = prices;
    }

    public void validate(Validatable.ValidationErrors errors){
        validateObligatoryFields(prices, errors, "student");
        validateObligatoryFields(prices, errors, "regular");
        validateObligatoryFields(prices, errors);
        validateNegativeValues(prices, errors);
    }

    private void validateNegativeValues(Map<String, BigDecimal> prices, Validatable.ValidationErrors errors) {
        for(BigDecimal price: prices.values()){
            if(price.compareTo(BigDecimal.ZERO) < 0){
                errors.add("ticketsRequiredPrices", "price must be greater than zero");
            }
        }
    }

    private void validateObligatoryFields(Map<String, BigDecimal> prices, Validatable.ValidationErrors errors,
                                          String fieldName) {
        if(prices.keySet().stream().filter(key -> key.toLowerCase().equals(fieldName)).
                collect(Collectors.toList()).isEmpty()){
            errors.add(fieldName,"Field with name" + fieldName + "is required.");
        }
    }

    private void validateObligatoryFields(Map<String, BigDecimal> prices, Validatable.ValidationErrors errors){
        for(String kind: prices.keySet()){
            if(kind.trim().length() == 0){
                errors.add("ticketPricesKind", "field can't be empty");
            }
        }
    }
}
