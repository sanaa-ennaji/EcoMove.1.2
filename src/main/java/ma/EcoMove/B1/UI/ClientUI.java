package main.java.ma.EcoMove.B1.UI;

import main.java.ma.EcoMove.B1.dao.BilletDAO;
import main.java.ma.EcoMove.B1.dao.ContratDAO;
import main.java.ma.EcoMove.B1.dao.PartenaireDAO;
import main.java.ma.EcoMove.B1.entity.Client;
import main.java.ma.EcoMove.B1.service.BilletService;
import main.java.ma.EcoMove.B1.service.ContratService;
import main.java.ma.EcoMove.B1.service.IService.IClientService;
import main.java.ma.EcoMove.B1.service.PartenaireService;

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
        this.billetUI = new BilletUI(billetService , contratService);

    }

    public void login (){
        System.out.println("--------------Client Login ----------------");
        System.out.print("enter your email to login: ");
        String email = scanner.nextLine().trim();
        Optional<Client> clientOpt = clientService.findByEmail(email);
        if (clientOpt.isPresent()){
            System.out.println("welcome back ," + clientOpt.get().getNom() + clientOpt.get().getPrenom());
            billetUI.searsh();

        }else {
            System.out.println("Not found 404");
            System.out.print("would you like to create an account ? (Y/N): ");
            String response = scanner.nextLine();
            if (response.equalsIgnoreCase("y")) {
                registerClient();
                billetUI.searsh();


            } else {

                System.out.println("exit");


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
    }

}
