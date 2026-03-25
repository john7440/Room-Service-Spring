package fr.fms.RoomServiceJpa.dao;

import fr.fms.RoomServiceJpa.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room,Long> {
    Room findById(long id);
    Room findByName(String name);
    List<Room> findAllByOrderByCapacityAsc();
}
