package pl.com.piotrslowinski.model;

import javax.persistence.Embeddable;

@Embeddable
public class Seat {

    private Integer row;

    private Integer seat;

    public Seat() {
    }

    public Seat(Integer row, Integer seat) {
        this.row = row;
        this.seat = seat;
    }

    public Integer getRow() {
        return row;
    }

    public void setRow(Integer row) {
        this.row = row;
    }

    public Integer getSeat() {
        return seat;
    }

    public void setSeat(Integer seat) {
        this.seat = seat;
    }
}
