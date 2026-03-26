package fr.fms.RoomServiceJpa;

import fr.fms.RoomServiceJpa.dao.BookingRepository;
import fr.fms.RoomServiceJpa.dao.RoomRepository;
import fr.fms.RoomServiceJpa.ihm.BookingIHM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class RoomServiceJpaApplication implements CommandLineRunner {
    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private BookingIHM bookingIHM;

	public static void main(String[] args) {
        SpringApplication.run(RoomServiceJpaApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
//        Room r1 = roomRepository.save(new Room("Cap", 8));
//        Room r2 =roomRepository.save(new Room("Escale", 3));
//        Room r3 =roomRepository.save(new Room("Boussole", 10));
//        Room r4 =roomRepository.save(new Room("Croquette", 8));
//
//        bookingRepository.save(new Booking(r1, LocalDate.of(2026, 3, 26), LocalTime.of(8, 0),  LocalTime.of(10, 0)));
//        bookingRepository.save(new Booking(r1, LocalDate.of(2026, 3, 26), LocalTime.of(14, 0), LocalTime.of(16, 0)));
//        bookingRepository.save(new Booking(r1, LocalDate.of(2026, 3, 27), LocalTime.of(9, 0),  LocalTime.of(11, 0)));
//
//        bookingRepository.save(new Booking(r2, LocalDate.of(2026, 3, 26), LocalTime.of(10, 0), LocalTime.of(12, 0)));
//        bookingRepository.save(new Booking(r2, LocalDate.of(2026, 3, 27), LocalTime.of(8, 0),  LocalTime.of(18, 0)));
//
//        bookingRepository.save(new Booking(r3, LocalDate.of(2026, 3, 26), LocalTime.of(11, 0), LocalTime.of(13, 0)));
//        bookingRepository.save(new Booking(r3, LocalDate.of(2026, 3, 28), LocalTime.of(15, 0), LocalTime.of(17, 0)));
//
//        bookingRepository.save(new Booking(r4, LocalDate.of(2026, 3, 27), LocalTime.of(9, 0),  LocalTime.of(11, 0)));
//        bookingRepository.save(new Booking(r4, LocalDate.of(2026, 3, 28), LocalTime.of(13, 0), LocalTime.of(15, 0)));

        bookingIHM.start();
    }

}
