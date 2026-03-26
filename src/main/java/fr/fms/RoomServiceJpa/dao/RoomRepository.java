package fr.fms.RoomServiceJpa.dao;

import fr.fms.RoomServiceJpa.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RoomRepository extends JpaRepository<Room,Long> {
    Optional<Room> findById(Long id);
    List<Room> findAllByOrderByCapacityAsc();
}
