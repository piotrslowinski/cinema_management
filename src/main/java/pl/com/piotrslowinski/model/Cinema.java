package pl.com.piotrslowinski.model;

import javax.persistence.*;

@Entity
@Table(name = "cinemas")
public class Cinema {

    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String city;

    public Cinema() {
    }


    public Cinema(String name, String city) {
        this.name = name;
        this.city = city;
    }

}
