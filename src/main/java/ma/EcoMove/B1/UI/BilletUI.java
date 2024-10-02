package main.java.ma.EcoMove.B1.UI;

import main.java.ma.EcoMove.B1.entity.Billet;
import main.java.ma.EcoMove.B1.entity.BilletNode;
import main.java.ma.EcoMove.B1.entity.Contrat;
import main.java.ma.EcoMove.B1.enums.TypeTransport;
import main.java.ma.EcoMove.B1.enums.StatutBillet;
import main.java.ma.EcoMove.B1.service.IService.IBilletService;
import main.java.ma.EcoMove.B1.service.IService.IContratService;
import main.java.ma.EcoMove.B1.service.ReservationService;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class BilletUI {
    private final IBilletService billetService;
    private final IContratService contratService;
    private final ReservationService reservationService ;

    public BilletUI(IBilletService billetService, IContratService contratService,ReservationService reservationService ) {
        this.billetService = billetService ;
        this.contratService = contratService ;
        this.reservationService = reservationService;
    }

    public void showMenu() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1. Create Billet");
            System.out.println("2. View Billet by ID");
            System.out.println("3. View All Billets");
            System.out.println("4. Update Billet");
            System.out.println("5. Delete Billet");
            System.out.println("6. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    createBillet(scanner);
                    break;
                case 2:
                    viewBilletById(scanner);
                    break;
                case 3:
                    viewAllBillets();
                    break;
                case 4:
                    updateBillet(scanner);
                    break;
                case 5:
                    deleteBillet(scanner);
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void createBillet(Scanner scanner) throws SQLException {
        Billet billet = new Billet();
        billet.setId(UUID.randomUUID());

        System.out.println("Enter Contrat ID:");
        UUID contratId = UUID.fromString(scanner.nextLine());
        Contrat contrat = contratService.getContratById(contratId);

        if (contrat == null) {
            System.out.println("Contrat not found.");
            return;
        }
        billet.setContrat(contrat);

        System.out.println("Enter Type of Transport (avion, train, bus):");
        billet.setTypeTransport(TypeTransport.valueOf(scanner.nextLine().toUpperCase()));

        System.out.println("Enter Purchase Price:");
        billet.setPrixAchat(new BigDecimal(scanner.nextLine()));

        System.out.println("Enter Sale Price:");
        billet.setPrixVente(new BigDecimal(scanner.nextLine()));


        System.out.println("Enter Sale Date (yyyy-mm-dd):");
        String saleDateInput = scanner.nextLine();
        if (saleDateInput.isEmpty()) {
            System.out.println("Sale date cannot be empty.");
            return;
        }
        billet.setDateVente(LocalDate.parse(saleDateInput));

        System.out.println("Enter Billet Status (vendu, annule, en attente):");
        billet.setStatutBillet(StatutBillet.valueOf(scanner.nextLine().toUpperCase()));

        System.out.println("Enter Departure Location:");
        billet.setDepart(scanner.nextLine());

        System.out.println("Enter Destination Location:");
        billet.setDestination(scanner.nextLine());

        System.out.println("Enter Departure Date (yyyy-mm-dd):");
        String departureDateInput = scanner.nextLine();
        if (departureDateInput.isEmpty()) {
            System.out.println("Departure date cannot be empty.");
            return;
        }
        billet.setDateDepart(LocalDate.parse(departureDateInput));


        System.out.println("Enter Arrival Date (yyyy-mm-dd):");
        String arrivalDateInput = scanner.nextLine();
        if (arrivalDateInput.isEmpty()) {
            System.out.println("Arrival date cannot be empty.");
            return;
        }
        billet.setDateArrive(LocalDate.parse(arrivalDateInput));


        try {
            billetService.createBillet(billet);
            System.out.println("Billet created successfully!");
        } catch (IllegalArgumentException e) {
            System.out.println("Error creating billet: " + e.getMessage());
        }
    }




    private void viewBilletById(Scanner scanner) throws SQLException {
        System.out.println("Enter Billet ID:");
        UUID id = UUID.fromString(scanner.nextLine());
        Billet billet = billetService.getBilletById(id);
        if (billet != null) {
            System.out.println("id: " + billet.getId());
            System.out.println("Contrat: " +  billet.getContrat().getId());
            System.out.println("PurchasePrice: " + billet.getPrixAchat());
            System.out.println("PrixVente: " + billet.getDateVente());
            System.out.println("Sale Date : " + billet.getDateVente());
            System.out.println("Status : " + billet.getStatutBillet());
            System.out.println("Transport Type: " + billet.getTypeTransport());
            System.out.println("Departure Location: " + billet.getDepart());
            System.out.println("Destination Location: " + billet.getDestination());
            System.out.println("Departure Date: " + billet.getDateDepart());
            System.out.println("Arrival Date: " + billet.getDateArrive());
            System.out.println("-------------");
        } else {
            System.out.println("Billet not found.");
        }
    }

    private void viewAllBillets() throws SQLException {
        List<Billet> billets = billetService.getAllBillets();
        for (Billet billet : billets) {
            if (billet != null) {
            System.out.println("ID: " + billet.getId());
            System.out.println("Contrat: " +  billet.getContrat().getId());
            System.out.println("Purchase Price: " + billet.getPrixAchat());
            System.out.println("PrixVente: " + billet.getPrixVente());
            System.out.println("Sale Date: " + billet.getDateVente());
            System.out.println("Status: " + billet.getStatutBillet());
            System.out.println("Transport Type : " + billet.getTypeTransport());
            System.out.println("Departure Location: " + billet.getDepart());
            System.out.println("Destination Location: " + billet.getDestination());
            System.out.println("Departure Date: " + billet.getDateDepart());
            System.out.println("Arrival Date: " + billet.getDateArrive());
            System.out.println("-------------");
        } else {
            System.out.println("no Billet created yet .");
        }
        }
    }



    private void updateBillet(Scanner scanner) throws SQLException {
        System.out.println("Enter Billet ID to update:");
        UUID id = UUID.fromString(scanner.nextLine());

        Billet billet = billetService.getBilletById(id);
        if (billet == null) {
            System.out.println("Billet not found.");
            return;
        }

        System.out.println("Enter new Type of Transport:");
        billet.setTypeTransport(TypeTransport.valueOf(scanner.nextLine().toUpperCase()));

        System.out.println("Enter new Purchase Price:");
        billet.setPrixAchat(new BigDecimal(scanner.nextLine()));

        System.out.println("Enter new Sale Price:");
        billet.setPrixVente(new BigDecimal(scanner.nextLine()));

        System.out.println("Enter new Sale Date (yyyy-mm-dd):");
        billet.setDateVente(LocalDate.parse(scanner.nextLine()));

        System.out.println("Enter new Billet Status (vendu, annule, en attente):");
        billet.setStatutBillet(StatutBillet.valueOf(scanner.nextLine().toUpperCase()));

        System.out.println("Enter new Departure Location:");
        billet.setDepart(scanner.nextLine());

        System.out.println("Enter new Destination Location:");
        billet.setDestination(scanner.nextLine());

        System.out.println("Enter new Departure Date (yyyy-mm-dd):");
        billet.setDateDepart(LocalDate.parse(scanner.nextLine()));

        System.out.println("Enter new Arrival Date (yyyy-mm-dd):");
        billet.setDateArrive(LocalDate.parse(scanner.nextLine()));

        billetService.updateBillet(billet);
        System.out.println("Billet updated successfully");
    }

    private void deleteBillet(Scanner scanner) throws SQLException {
        System.out.println("Enter Billet ID to delete:");
        UUID id = UUID.fromString(scanner.nextLine());

        billetService.deleteBillet(id);
        System.out.println("Billet deleted successfully!");
    }

    public void search(UUID clientId) {
        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        while (true) {
            System.out.println("Welcome to the Ticket Finder!");
            System.out.println("Please enter your departure point:");
            String depart = scanner.nextLine();
            if (depart.equalsIgnoreCase("exit")) {
                break;
            }

            System.out.println("Please enter your destination:");
            String destination = scanner.nextLine();

            LocalDate dateDepart = null;
            while (dateDepart == null) {
                System.out.println("Please enter your departure date YYYY-MM-DD:");
                String dateInput = scanner.nextLine();
                try {
                    dateDepart = LocalDate.parse(dateInput, formatter);
                } catch (DateTimeParseException e) {
                    System.out.println("Invalid date format. Please enter the date in the format YYYY-MM-DD.");
                }
            }

            List<BilletNode> availableTickets = billetService.searchTickets(depart, destination, dateDepart);

         if (availableTickets == null || availableTickets.isEmpty()) {
          System.out.println("No tickets found for the given criteria.");
          }

                System.out.println("Would you like to reserve the first available ticket? (y/n)");
                String response = scanner.nextLine();
                if (response.equalsIgnoreCase("y")) {
                    BilletNode selectedTicket = availableTickets.get(0);
                    createReservationForTicket(selectedTicket, clientId);
                }
            break;
            }


        scanner.close();
    }





    private void createReservationForTicket(BilletNode ticket, UUID clientId) {
        UUID id = UUID.randomUUID();
        LocalDateTime dateReservation = LocalDateTime.now();


        reservationService.createReservation(id, clientId, dateReservation, "enattente", ticket.getPrixVente());
       System.out.println("Ticket reserved successfully!");
    }



}

