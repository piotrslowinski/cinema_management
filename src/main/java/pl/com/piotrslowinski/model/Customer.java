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
}
