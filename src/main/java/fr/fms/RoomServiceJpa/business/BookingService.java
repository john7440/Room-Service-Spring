package fr.fms.RoomServiceJpa.business;

import fr.fms.RoomServiceJpa.dao.BookingRepository;
import fr.fms.RoomServiceJpa.dao.RoomRepository;
import fr.fms.RoomServiceJpa.entities.Booking;
import fr.fms.RoomServiceJpa.entities.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private RoomRepository roomRepository;

    //----------------------------rooms---------------------
    public List<Room> findAll() {
        return roomRepository.findAll();
    }

    public Room findById(Long id) {
        return roomRepository.findById(id).orElse(null);
    }

    public void saveRoom(Room room) {
        roomRepository.save(room);
    }

    public boolean deleteRoomById(Long id) {
        if (roomRepository.existsById(id)) {
            roomRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Room> findAllByMinCapacity(int capacity) {
        return roomRepository.findAllByCapacityGreaterThan(capacity);
    }
    //---------------------------booking----------------------------
    public List<Booking> findAllBookings() {
        return bookingRepository.findAll();
    }
    public List<Booking> findAllByRoomName(String roomName) {
        return bookingRepository.findByRoomName(roomName);
    }

    public boolean hasConflict(Long roomId, LocalDate date, LocalTime start, LocalTime end) {
        List<Booking> conflicts = bookingRepository.findByRoomIdAndDateAndStartTimeLessThanAndEndTimeGreaterThan(
                roomId, date, end, start
        );
        return !conflicts.isEmpty();
    }
    //-----------------------------------------------------------------------------------------------

    public Booking saveBooking(Booking booking){
        if (hasConflict(booking.getRoom().getId(), booking.getDate(), booking.getStartTime(), booking.getEndTime())) {
            System.out.println("Conflit horaire: salle déjà réservées sur ce créneau");
            return null;
        }
        return bookingRepository.save(booking);
    }
    //----------------------------------------------------------

    public boolean deleteBookingById(Long id) {
        if (bookingRepository.existsById(id)) {
            bookingRepository.deleteById(id);
            return true;
        }
        return false;
    }
    //-------------------------------------------------------

    public List<Booking> findAllByDate(LocalDate date) {
        return bookingRepository.findByDate(date);
    }



}
