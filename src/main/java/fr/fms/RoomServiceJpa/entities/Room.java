package fr.fms.RoomServiceJpa.entities;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;

/**
 *  Represents a Room we can book
 */
@Entity
public class Room  implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int capacity;

    //----------------------------constructors----------------------
    public Room() {}

    public Room(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
    }

    //-------------------getter and setter-----------------------------------
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    //-----------------------------affichage-----------------------------------
    @Override
    public String toString() {
        return "ID: "+ id + "Room :" + name+ " Capacity: " + capacity;
    }
}
