package main.java.ma.EcoMove.B1.UI;

import main.java.ma.EcoMove.B1.entity.Client;
import main.java.ma.EcoMove.B1.service.IService.IClientService;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Scanner;

public class ClientUI {

    private final IClientService clientService ;
    private final Scanner scanner = new Scanner(System.in);

    public ClientUI (IClientService clientService){
        this.clientService = clientService;
    }

    public void login (){
        System.out.println("--------------Client Login ----------------");
        System.out.print("enter your email to login: ");
        String email = scanner.nextLine().trim();
        Optional<Client> clientOpt = clientService.findByEmail(email);
        if (clientOpt.isPresent()){
            System.out.println("welcome back ," + clientOpt.get().getNom() + clientOpt.get().getPrenom());

        }else {
            System.out.println("Not found 404");
            System.out.print("would you like to create an account ? (Y/N): ");
            String response = scanner.nextLine();
            if (response.equalsIgnoreCase("y")) {
                registerClient();


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
