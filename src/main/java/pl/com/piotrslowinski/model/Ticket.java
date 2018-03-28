package pl.com.piotrslowinski.model;

import javax.persistence.Embeddable;

@Embeddable
public class Ticket {

    private String kind;

    private Integer count;

    public Ticket() {
    }

    public Ticket(String kind, Integer count) {
        this.kind = kind;
        this.count = count;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
