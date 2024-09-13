package main.java.ma.EcoMove.B1.UI;

import main.java.ma.EcoMove.B1.entity.Client;
import main.java.ma.EcoMove.B1.service.IService.IClientService;

import java.util.Optional;
import java.util.Scanner;

public class ClientUI {

    private final IClientService clientService ;
    private final Scanner scanner = new scanner(System.in);

    public ClientUI (IClientService clientService){
        this.clientService = clientService;
    }

    public void login (){
        System.out.println("--------------Client Login ----------------");
        System.out.println("your email sir:");
        String email = scanner.nextLine();
        Optional<Client> clientOpt = clientService.findByEmail(email);
        if (clientOpt.isPresent()){
            System.out.println("welcome back ," + clientOpt.get().getNom() + clientOpt.get().getPrenom());

        }else {
            System.out.println("Not found 404");
            System.out.println("would you like to create an account ? (y/n):");
            String response = scanner.nextLine();
            if (response.equalsIgnoreCase("y")) {
                System.out.println("nom");
                String nom = scanner.nextLine();

                System.out.println("prenom");
                String prenom = scanner.nextLine();

                System.out.print("phone number");
                String telephone = scanner.nextLine();

                Client newClient = new Client();
                newClient.setNom(nom);
                newClient.setPrenom(prenom);
                newClient.setEmail(email);
                newClient.setTelephone(telephone);

                clientService.registerClient(newClient);

                System.out.println("account created");


            } else {

                System.out.println("exist");


            }
        }

    }

}
