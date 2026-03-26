package fr.fms.RoomServiceJpa.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Entity that represents a Booking (id,room,date,startTime,endTime)
 */
@Entity
public class Booking implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;

    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;

    //-------------------constructors----------------------------------------
    public Booking() {}

    public Booking(Room room, LocalDate date, LocalTime startTime, LocalTime endTime) {
        this.room = room;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    //-----------------------getter and setter--------------------
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    //-----------------------------------affichage----------------------------------
    @Override
    public String toString() {
        return ("Room: " + room + "\n" + "Date: " + date + "\n" + "StartTime: " + startTime + "\n" + "EndTime: " + endTime);
    }
}
