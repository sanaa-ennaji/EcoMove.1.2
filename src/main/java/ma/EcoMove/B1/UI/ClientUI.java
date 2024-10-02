package main.java.ma.EcoMove.B1.UI;

import main.java.ma.EcoMove.B1.dao.BilletDAO;
import main.java.ma.EcoMove.B1.dao.ContratDAO;
import main.java.ma.EcoMove.B1.dao.PartenaireDAO;
import main.java.ma.EcoMove.B1.dao.ReservationDAO;
import main.java.ma.EcoMove.B1.entity.Client;
import main.java.ma.EcoMove.B1.service.BilletService;
import main.java.ma.EcoMove.B1.service.ContratService;
import main.java.ma.EcoMove.B1.service.IService.IClientService;
import main.java.ma.EcoMove.B1.service.PartenaireService;
import main.java.ma.EcoMove.B1.service.ReservationService;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Scanner;

public class ClientUI {

    private final IClientService clientService ;
    private final Scanner scanner = new Scanner(System.in);
    private final ContratUI contratUI;
    private final BilletUI billetUI;
    private final PartenaireUI partenaireUI;

    public ClientUI (IClientService clientService){
        PartenaireDAO partenaireDAO = new PartenaireDAO();
        PartenaireService partenaireService = new PartenaireService(partenaireDAO);
        this.partenaireUI = new PartenaireUI(partenaireService);
        this.clientService = clientService;
        ContratDAO contratDAO = new ContratDAO();
        ContratService contratService = new ContratService(contratDAO);
        this.contratUI= new ContratUI(contratService , partenaireService);

        BilletDAO billetDAO = new BilletDAO();
        BilletService billetService = new BilletService(billetDAO);
         ReservationDAO  reservationDAO = new  ReservationDAO();
         ReservationService reservationService = new ReservationService(reservationDAO);

        this.billetUI = new BilletUI(billetService , contratService , reservationService);

    }

    public void login() {
        System.out.println("--------------Client Login ----------------");
        System.out.print("Enter your email to login: ");
        String email = scanner.nextLine().trim();
        Optional<Client> clientOpt = clientService.findByEmail(email);

        if (clientOpt.isPresent()) {
            Client client = clientOpt.get();
            System.out.println("Welcome back, " + client.getNom() + " " + client.getPrenom());

            billetUI.search(client.getId());

        } else {
            System.out.println("Not found 404");
            System.out.print("Would you like to create an account? (Y/N): ");
            String response = scanner.nextLine();

            if (response.equalsIgnoreCase("y")) {
                registerClient();
            } else {
                System.out.println("Exit");
            }
        }
    }


    private void registerClient() {
        System.out.print("email: ");
        String email = scanner.nextLine();
        System.out.print("nom: ");
        String nom = scanner.nextLine();

        System.out.print("prenom: ");
        String prenom = scanner.nextLine();

        System.out.print("phone number: ");
        String telephone = scanner.nextLine();


        Client newClient = clientService.registerClient(nom, prenom, email, telephone, LocalDate.now());

        System.out.println("Account created successfull ! Welcome, " + newClient.getNom() + "!");
        billetUI.search(newClient.getId());
    }

}
