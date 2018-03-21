package pl.com.piotrslowinski.model.commands;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public interface Validatable {

    void validate(ValidationErrors errors);

    class ValidationErrors{

        private Map<String, String> errors = new HashMap<>();

        public void add(String fieldName, String errorMessage){
           errors.put(fieldName, errorMessage);
        }

        public boolean isValid(){
            return errors.isEmpty();
        }

        public Map<String, String> getErrors() {
            return errors;
        }

        public void setErrors(Map<String, String> errors) {
            this.errors = errors;
        }
        public String getMessage() {
            return String.format("Invalid request parameters");
        }
    }

    default boolean isEmpty(String s){
        return s == null || s.trim().length() == 0;
    }
}
