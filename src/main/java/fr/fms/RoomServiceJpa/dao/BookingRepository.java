package fr.fms.RoomServiceJpa.dao;

import fr.fms.RoomServiceJpa.entities.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByRoomName(String name);
    List<Booking> findByDate(LocalDate date);
    List<Booking> findByRoomCapacityGreaterThanEqual(int capacity);
    List<Booking> findByRoomIdAndDateAndStartTimeLessThanAndEndTimeGreaterThan(
            Long roomId,
            LocalDate date,
            LocalTime endTime,
            LocalTime startTime
    );
}
