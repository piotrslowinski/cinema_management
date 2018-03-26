package pl.com.piotrslowinski.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "cinemas")
public class Cinema {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String city;

    @OneToMany(mappedBy = "cinema", fetch = FetchType.EAGER)
    private Collection<Show> shows = new ArrayList<>();

    public Cinema() {
    }


    public Cinema(String name, String city) {
        this.name = name;
        this.city = city;
    }

}
