package fr.fms.RoomServiceJpa.ihm;

import fr.fms.RoomServiceJpa.business.BookingService;
import fr.fms.RoomServiceJpa.entities.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class BookingIHM {

    @Autowired
    private BookingService bookingService;

    private final Scanner scan =  new Scanner(System.in);

    //----------------------menu---------------------------------------
    public void start(){
        int choice = -1;
        while(choice!=9){
            printMenu();
            choice = readInt("Votre choix : ");
            switch (choice){
                case 1 -> showAllRooms();
                case 4 -> addRoom();
                case 6 -> updateRoom();
                case 9 ->  System.out.println("\nAu revoir");
                default -> System.out.println("Choix invalide, veuillez réessayer");
            }
        }

    }
    private void printMenu(){
        System.out.println("\nBienvenu dans notre application de gestion de salles !");
        System.out.println("1: Afficher toutes les salles");
        System.out.println("2: Afficher réservations par salle");
        System.out.println("3: Afficher les salles par capacité");
        System.out.println("****************************");
        System.out.println("4: Ajouter une salle");
        System.out.println("5: Supprimer une salle");
        System.out.println("6: Modifier une salle");
        System.out.println("****************************");
        System.out.println("7: Créer une reservation");
        System.out.println("8: Supprimer une réservation");
        System.out.println("******************************");
        System.out.println("9: Sortir du programme\n\n");

    }
    //-------------------------------salles-----------------------------------
    private void showAllRooms(){
        List<Room> rooms = bookingService.findAll();
        printRoomList(rooms);
    }

    private void printRoomList(List<Room> rooms) {
        if (rooms.isEmpty()) {
            System.out.println("Aucun salle trouvée");
            return;
        }
        System.out.printf("%-5s %-15s %-20s%n",
                "ID", "NOM", "CAPACITÉ");
        System.out.println("-".repeat(35));
        for (Room r : rooms) {
            System.out.printf("%-5d %-15s %-20d%n",
                    r.getId(),
                    r.getName(),
                    r.getCapacity());
        }
        System.out.println("-".repeat(35));
    }
    //----------------------------------------------------------

    private void addRoom(){
        String name = readString("Nom de la salle: ");
        int capacity = readInt("Capacité maximale: ");
        Room room = new Room(name, capacity);
        bookingService.saveRoom(room);
        System.out.printf("Salle %s ajouté", room.getName());
    }
    //-----------------------------------------------------------

    private void updateRoom() {
        showAllRooms();
        Long id = readLong("ID de la salle à modifier : ");
        Room r = bookingService.findById(id);
        if (r == null) { System.out.println("Salle introuvable !"); return; }
        System.out.println("Salle actuel : " + r);
        r.setName(readString("Nouveau nom(" + r.getName() + ") : "));
        r.setCapacity(readInt("Nouvelle capacité (" + r.getCapacity() + ") : "));
        bookingService.saveRoom(r);
        System.out.printf("\nSalle %s mise à jour !",  r.getName());
    }

    //---------------méthodes utilitaires----------------------------------------
    private int readInt(String prompt) {
        System.out.print(prompt);
        try {
            return Integer.parseInt(scan.nextLine());
        } catch (Exception ex) {
            return -1;
        }
    }
    //------------------------------------------------------------

    private String readString(String prompt) {
        System.out.print(prompt);
        try {
            return scan.nextLine();
        } catch (Exception ex) {
            return "";
        }
    }
    //-----------------------------------------------------

    private Long readLong(String prompt) {
        System.out.print(prompt);
        try {
            return Long.parseLong(scan.nextLine());
        }
        catch (Exception ex) {
            return -1L;
        }
    }
}
