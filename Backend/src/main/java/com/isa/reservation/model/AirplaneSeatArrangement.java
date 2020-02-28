package com.isa.reservation.model;


import javax.persistence.*;
import java.io.Serializable;

@Table
@Entity
public class AirplaneSeatArrangement implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String name;

    @Column
    private int seatRows;

    @Column
    private int seatColumns;

    public AirplaneSeatArrangement(){}

    public String getName() {  return name; }

    public void setName(String name) { this.name = name; }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public int getSeatRows() {
        return seatRows;
    }

    public void setSeatRows(int seatRows) {
        this.seatRows = seatRows;
    }

    public int getSeatColumns() {
        return seatColumns;
    }

    public void setSeatColumns(int seatColumns) {
        this.seatColumns = seatColumns;
    }
}
