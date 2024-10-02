package main.java.ma.EcoMove.B1.UI;
import main.java.ma.EcoMove.B1.dao.*;
import main.java.ma.EcoMove.B1.service.*;

import java.sql.SQLException;
import java.util.Scanner;

public class PrincipalMenu {

    private final Scanner scanner = new Scanner(System.in);
    private final PartenaireUI partenaireUI;
    private final ContratUI contratUI;
    private final BilletUI billetUI;
    private final PromotionUI promotionUI;

    public PrincipalMenu() {
        PartenaireDAO partenaireDAO = new PartenaireDAO();
        PartenaireService partenaireService = new PartenaireService(partenaireDAO);
        this.partenaireUI = new PartenaireUI(partenaireService);

        ContratDAO contratDAO = new ContratDAO();
        ContratService contratService = new ContratService(contratDAO);
        this.contratUI= new ContratUI(contratService , partenaireService);

        BilletDAO billetDAO = new BilletDAO();
        BilletService  billetService = new BilletService(billetDAO);
        ReservationDAO reservationDAO = new  ReservationDAO();
        ReservationService reservationService = new ReservationService(reservationDAO);
        this.billetUI = new BilletUI(billetService , contratService , reservationService);

        PromotionDAO promotionDAO = new PromotionDAO();
        PromotionService promotionService = new PromotionService(promotionDAO);
        this.promotionUI = new   PromotionUI(promotionService , contratService);



    }

    public void run() throws SQLException {
        while (true) {
            System.out.println("----- Principal Menu -----");
            System.out.println("1. Partenaire Management");
            System.out.println("2. Contrat Management");
            System.out.println("3. Billet Management");
            System.out.println("4. Promotion Management");
            System.out.println("5. Exit");
            System.out.print("Select an option: ");

            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    partenaireUI.run();
                    break;
                case 2:
                    contratUI.start();
                    break;
                case 3:
                    billetUI.showMenu();
                    break;
                case 4:
                    promotionUI.showMenu();
                    break;
                case 5:
                    System.out.println("Exiting...");
//                    closeConnection();
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }


}
