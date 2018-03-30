package pl.com.piotrslowinski.model;

import javax.persistence.Embeddable;

@Embeddable
public class Customer {

    private String firstName;

    private String lastName;

    private String email;

    private String phone;

    public Customer() {
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }
}
