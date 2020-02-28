package com.isa.reservation.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.omg.CORBA.PRIVATE_MEMBER;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Table
@Entity(name = "ROOMRESERVATIONS")
public class RoomReservation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_reservation_id")
    private Long id;

    @Column
    private Date arrivalDate;

    @Column
    private Date departureDate;

    @Column
    private int numberOfNights;

    @Column
    private int numberOfBeds;
/*
    @JsonManagedReference
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    //  @JoinColumn(name="reservations_room", referencedColumnName="room_id", nullable=false)
    private Room room;
*/

    @Column
    private Long roomId;

    @OneToMany(cascade={CascadeType.ALL}, fetch=FetchType.EAGER, orphanRemoval = true)
    //@JoinColumn(name = "room_reservation",referencedColumnName = "room_reservation_id")
    private Set<Room> rooms;

    public RoomReservation(){
        rooms = new HashSet<Room>();
    }


    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public int getNumberOfNights() {
        return numberOfNights;
    }

    public void setNumberOfNights(int numberOfNights) {
        this.numberOfNights = numberOfNights;
    }

    public int getNumberOfBeds() {
        return numberOfBeds;
    }

    public void setNumberOfBeds(int numberOfBeds) {
        this.numberOfBeds = numberOfBeds;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Date getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public Set<Room> getRooms() {
        return rooms;
    }

    public void setRooms(Set<Room> rooms) {
        this.rooms = rooms;
    }
    /*
    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public int getNumberOfNights() {
        return numberOfNights;
    }

    public void setNumberOfNights(int numberOfNights) {
        this.numberOfNights = numberOfNights;
    }
    */
}
