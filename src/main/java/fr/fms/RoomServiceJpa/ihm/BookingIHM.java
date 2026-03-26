package fr.fms.RoomServiceJpa.ihm;

import fr.fms.RoomServiceJpa.business.BookingService;
import fr.fms.RoomServiceJpa.entities.Booking;
import fr.fms.RoomServiceJpa.entities.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
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
        while(choice!=11){
            printMenu();
            choice = readInt("Votre choix : ");
            switch (choice){
                case 1 -> showAllRooms();
                case 2 -> showBookingByName();
                case 3 -> showAllBookings();
                case 4 -> addRoom();
                case 5 -> deleteRoom();
                case 6 -> updateRoom();
                case 7 -> addBooking();
                case 8 -> deleteBooking();
                case 9 -> showRoomByDate();
                case 10 -> showRoomByCapacity();
                case 11 ->  System.out.println("\nAu revoir");
                default -> System.out.println("Choix invalide, veuillez réessayer");
            }
        }
    }

    private void printMenu(){
        System.out.println("\nBienvenu dans notre application de gestion de salles !");
        System.out.println("1: Afficher toutes les salles");
        System.out.println("2: Afficher réservations par salle");
        System.out.println("3: Afficher toutes les réservations");
        System.out.println("***************************");
        System.out.println("4: Ajouter une salle");
        System.out.println("5: Supprimer une salle");
        System.out.println("6: Modifier une salle");
        System.out.println("****************************");
        System.out.println("7: Créer une reservation");
        System.out.println("8: Supprimer une réservation");
        System.out.println("****************************");
        System.out.println("9: Voir réservations à une date précise");
        System.out.println("10: Voir salles en fonction de la capacité désirée");
        System.out.println("******************************");
        System.out.println("11: Sortir du programme\n\n");

    }
    //-------------------------------salles-----------------------------------

    /**
     * Method to display a list of all the rooms
     */
    private void showAllRooms(){
        List<Room> rooms = bookingService.findAll();
        printRoomList(rooms);
    }

    /**
     * Method to find all booking for a specific date
     */
    private void showRoomByDate(){
        LocalDate date = readLocalDate("Date de reservation: ");
        List<Booking> rooms = bookingService.findAllByDate(date);
        printBookingList(rooms);

    }

    /**
     * Method to find all room with an equal or greater capacity
     */
    private void showRoomByCapacity(){
        int capacity = readInt("Capacité minimale souhaitée: ");
        List<Room> rooms = bookingService.findAllByMinCapacity(capacity);
        printRoomList(rooms);
    }
    //----------------------------------------------------------------------

    /**
     * Method to display a formated list of rooms
     * @param rooms : the list of rooms
     */
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

    /**
     * Method to add a new room
     */
    private void addRoom(){
        String name = readString("Nom de la salle: ");
        int capacity = readInt("Capacité maximale: ");
        Room room = new Room(name, capacity);
        bookingService.saveRoom(room);
        System.out.printf("Salle %s ajouté", room.getName());
    }
    //-----------------------------------------------------------

    /**
     *  Method to modify a room (name and capacity)
     */
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
    //---------------------------------------------------------------------

    /**
     * Method to delete a room (if it exists)
     */
    private void deleteRoom(){
       showAllRooms();
        Long id = readLong("\nID de la salle à supprimer: ");
        if (bookingService.deleteRoomById(id)){
            System.out.println("\nSalle supprimé !");
        }
        else{
            System.out.println("\nSalle introuvable");
        }
    }
    //----------------------------------------------------------------------
    //-----------------------------reservations*-----------------------

    /**
     * Method to show all the bookings
     */
    private void showAllBookings(){
        List<Booking> bookings = bookingService.findAllBookings();
        printBookingList(bookings);
    }

    /**
     * Method to find all booking for a specific room (search by name)
     */
    private void showBookingByName(){
        showAllRooms();
        String name = readString("Nom de la salle: ");
        List<Booking> bookings = bookingService.findAllByRoomName(name);
        printBookingList(bookings);}

    //-----------------------------------------------------------------

    /**
     * Method to display a formated list of bookings
     * @param bookings : the list of Bookings to display
     */
    private void printBookingList(List<Booking> bookings) {
        if (bookings.isEmpty()) {
            System.out.println("Aucune réservation trouvée");
            return;
        }
        System.out.printf("%-5s %-15s %-20s %-10s %-15s %-10s%n",
                "ID", "SALLE", "DATE", "DEBUT", "FIN","CAPACITÉ");
        System.out.println("-".repeat(70));
        for (Booking b : bookings) {
            System.out.printf("%-5d %-15s %-20s %-10s %-15s%n",
                    b.getId(),
                    b.getRoom().getName(),
                    b.getDate(),
                    b.getStartTime(),
                    b.getEndTime());
        }
        System.out.println("-".repeat(70));
    }
    //----------------------------------------------------------

    /**
     * Method to verify and add a new booking if it's valid
     */
    private void addBooking(){
        showAllRooms();
        Long roomId = readLong("ID de la salle à réserver: ");
        Room room = bookingService.findById(roomId);
        if (room == null) {
            System.out.println("Salle introuvable");
            return;
        }
        LocalDate date = readLocalDate("Date de la réservation (AAAA-MM-JJ) : ");
        if (date == null) {
            return;
        }
        LocalTime startTime = readLocalTime("Heure de début (HH:MM):");
        if (startTime == null) {
            return;
        }
        LocalTime endTime = readLocalTime("Heure de fin (HH:MM):");
        if (endTime == null) {
            return;
        }
        // Validation horaires (8-18h)
        if (startTime.isBefore(LocalTime.of(8,0)) ||  endTime.isAfter(LocalTime.of(18,0)) || !endTime.isAfter(startTime)) {
            System.out.println("Horaires invalide ! Les réservations ne sont possible que entre 08:00 et 18:00");
            return;
        }

        Booking booking = new Booking(room, date, startTime, endTime);
        Booking savedBooking = bookingService.saveBooking(booking);
        if (savedBooking != null) {
            System.out.println("Réservation créée avec succès !");
        }
    }

    /**
     * Method to delete a booking if it exists
     */
    private void deleteBooking(){
        showAllBookings();
        Long id = readLong("ID de la réservation à supprimer: ");
        if (bookingService.deleteBookingById(id)){
            System.out.println("\nRéservation correctement supprimée");
        } else {
            System.out.println("Réservation non trouvée");
        }
    }
    //------------------------------------------------------------------------
    //---------------méthodes utilitaires----------------------------------------

    /**
     * Method to verify an Integer
     * @param prompt : the prompt to display
     * @return : a validated int/ -1
     */
    private int readInt(String prompt) {
        System.out.print(prompt);
        try {
            return Integer.parseInt(scan.nextLine());
        } catch (Exception ex) {
            return -1;
        }
    }
    //------------------------------------------------------------

    /**
     * Method to verify a String
     * @param prompt : the prompt to display
     * @return : a validated string / ""
     */
    private String readString(String prompt) {
        System.out.print(prompt);
        try {
            return scan.nextLine();
        } catch (Exception ex) {
            return "";
        }
    }
    //-----------------------------------------------------

    /**
     * Method to verify a Long
     * @param prompt : the prompt to display
     * @return : a validated Long / -1L
     */
    private Long readLong(String prompt) {
        System.out.print(prompt);
        try {
            return Long.parseLong(scan.nextLine());
        }
        catch (Exception ex) {
            return -1L;
        }
    }
    //----------------------------------------------------

    /**
     * Method to verify a Date
     * @param prompt : the prompt to display
     * @return : a validated Date / null
     */
    private LocalDate readLocalDate(String prompt){
        System.out.print(prompt);
        try {
            return LocalDate.parse(scan.nextLine());
        } catch (Exception ex) {
            System.out.println("Format invalide, utilisez YYYY-MM-DD");
            return null;
        }
    }
    //---------------------------------------------------------------------

    /**
     * Method to verify a LocalTime
     * @param prompt : the prompt to display
     * @return : a validated LocalTime / null
     */
    private LocalTime readLocalTime(String prompt) {
        System.out.print(prompt);
        try {
            return LocalTime.parse(scan.nextLine());
        } catch (Exception ex) {
            System.out.println("Format invalide, utilisez HH:MM");
            return null;
        }
    }
}
