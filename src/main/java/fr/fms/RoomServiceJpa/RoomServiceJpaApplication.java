package fr.fms.RoomServiceJpa;

import fr.fms.RoomServiceJpa.dao.RoomRepository;
import fr.fms.RoomServiceJpa.entities.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.Serializable;

@SpringBootApplication
public class RoomServiceJpaApplication implements CommandLineRunner {
    @Autowired
    private RoomRepository roomRepository;

	public static void main(String[] args) {

        SpringApplication.run(RoomServiceJpaApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
//        roomRepository.save(new Room("Cap", 8));
//        roomRepository.save(new Room("Escale", 3));
//        roomRepository.save(new Room("Boussole", 10));
//        roomRepository.save(new Room("Croquette", 8));
//        roomRepository.save(new Room("Mezza-1", 6));
//        roomRepository.save(new Room("Mezza-2", 9));
//        roomRepository.save(new Room("Mezza-3", 5));
//        roomRepository.save(new Room("Connexion", 2));

    }

}
