package main.java.ma.EcoMove.B1.UI;
import main.java.ma.EcoMove.B1.entity.Partenaire;
import main.java.ma.EcoMove.B1.enums.StatutPartenaire;
import main.java.ma.EcoMove.B1.enums.TypeTransport;
import main.java.ma.EcoMove.B1.service.IService.IPartenaireService;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;


public class PartenaireUI {
    private final IPartenaireService partenaireService ;
    private final Scanner scanner = new Scanner(System.in);
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public PartenaireUI(IPartenaireService partenaireService) {
        this.partenaireService = partenaireService;

    }



    public void run() {
        while (true) {
            System.out.println("1. Create Partenaire");
            System.out.println("2. Get Partenaire by ID");
            System.out.println("3. Get All Partenaires");
            System.out.println("4. Update Partenaire");
            System.out.println("5. Delete Partenaire");
            System.out.println("6. Exit");
            System.out.print("Select an option: ");

            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    createPartenaire();
                    break;
                case 2:
                    getPartenaireById();
                    break;
                case 3:
                    getAllPartenaires();
                    break;
                case 4:
                    updatePartenaire();
                    break;
                case 5:
                    deletePartenaire();
                    break;
                case 6:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    private void createPartenaire() {
        try {
            System.out.print("Enter nomCompagnie: ");
            String nomCompagnie = scanner.nextLine();
            System.out.print("Enter contactCommercial: ");
            String contactCommercial = scanner.nextLine();
            System.out.print("Enter typeTransport (bus, train, avion): ");
            TypeTransport typeTransport = TypeTransport.valueOf(scanner.nextLine().toUpperCase());
            System.out.print("Enter zoneGeographique: ");
            String zoneGeographique = scanner.nextLine();
            System.out.print("Enter conditionsSpeciales: ");
            String conditionsSpeciales = scanner.nextLine();
            System.out.print("Enter statutPartenaire (ACTIF, INACTIF): ");
            StatutPartenaire statutPartenaire = StatutPartenaire.valueOf(scanner.nextLine().toUpperCase());
            System.out.print("Enter dateCreation (yyyy-MM-dd): ");
            Date dateCreation = dateFormat.parse(scanner.nextLine());


            Partenaire partenaire = new Partenaire(
                    UUID.randomUUID(),
                    nomCompagnie ,
                    contactCommercial,
                    typeTransport,
                    zoneGeographique,
                    conditionsSpeciales,
                    statutPartenaire,
                    dateCreation ,
                    null


            );



            partenaireService.createPartenaire(partenaire);
            System.out.println("Partenaire created successfully.");
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }
    }

    private void getPartenaireById() {
        try {
            System.out.print("Enter Partenaire ID: ");
            UUID id = UUID.fromString(scanner.nextLine());
            Partenaire partenaire = partenaireService.getPartenaireById(id);
            if (partenaire != null) {
                System.out.println("------------------------------");
                System.out.println("ID: " + partenaire.getId());
                System.out.println("Nom Compagnie: " + partenaire.getNomCompagnie());
                System.out.println("Contact Commercial: " + partenaire.getContactCommercial());
                System.out.println("Type Transport (bus, train, avion): " + partenaire.getTypeTransport());
                System.out.println("Zone Géographique: " + partenaire.getZoneGeographique());
                System.out.println("Conditions Spéciales: " + partenaire.getConditionsSpeciales());
                System.out.println("Statut Partenaire: " + partenaire.getStatutPartenaire());
                System.out.println("Date Creation: " + partenaire.getDateCreation());
                System.out.println("------------------------------");
            } else {
                System.out.println("Partenaire not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private void getAllPartenaires() {
        try {

            List<Partenaire> partenaires = partenaireService.getAllPartenaires();
            for (Partenaire partenaire : partenaires) {
                System.out.println("------------------------------");
                System.out.println("ID: " + partenaire.getId());
                System.out.println("nomCompagnie: " +partenaire.getNomCompagnie());
                System.out.println("contactCommercial: " + partenaire.getContactCommercial());
                System.out.println("typeTransport (bus, train, avion): " + partenaire.getTypeTransport());
                System.out.println("zoneGeographique: " + partenaire.getZoneGeographique());
                System.out.println("conditionsSpeciales: " + partenaire.getConditionsSpeciales());
                System.out.println("statutPartenaire: " + partenaire.getStatutPartenaire());
                System.out.println("dateCreation: " + partenaire.getDateCreation());
                System.out.println("------------------------------");

            }
        } catch (SQLException e) {
            System.out.println("Partenaire not found.");
        }
    }


    private void updatePartenaire() {
        try {
            System.out.print("Enter Partenaire ID to update: ");
            UUID id = UUID.fromString(scanner.nextLine());
            Partenaire partenaire = partenaireService.getPartenaireById(id);
            if (partenaire != null) {
                System.out.print("Enter new nomCompagnie (leave empty to keep current): ");
                String nomCompagnie = scanner.nextLine();
                if (!nomCompagnie.isEmpty()) {
                    partenaire.setNomCompagnie(nomCompagnie);
                }
                System.out.print("Enter new contactCommercial (leave empty to keep current): ");
                String contactCommercial = scanner.nextLine();
                if (!contactCommercial.isEmpty()) {
                    partenaire.setContactCommercial(contactCommercial);
                }
                System.out.print("Enter new typeTransport (leave empty to keep current): ");
                String typeTransportInput = scanner.nextLine();
                if (!typeTransportInput.isEmpty()) {
                    partenaire.setTypeTransport(TypeTransport.valueOf(typeTransportInput.toUpperCase()));
                }
                System.out.print("Enter new zoneGeographique (leave empty to keep current): ");
                String zoneGeographique = scanner.nextLine();
                if (!zoneGeographique.isEmpty()) {
                    partenaire.setZoneGeographique(zoneGeographique);
                }
                System.out.print("Enter new conditionsSpeciales (leave empty to keep current): ");
                String conditionsSpeciales = scanner.nextLine();
                if (!conditionsSpeciales.isEmpty()) {
                    partenaire.setConditionsSpeciales(conditionsSpeciales);
                }
                System.out.print("Enter new statutPartenaire (leave empty to keep current): ");
                String statutPartenaireInput = scanner.nextLine();
                if (!statutPartenaireInput.isEmpty()) {
                    partenaire.setStatutPartenaire(StatutPartenaire.valueOf(statutPartenaireInput.toUpperCase()));
                }
                System.out.print("Enter new dateCreation (yyyy-MM-dd, leave empty to keep current): ");
                String dateCreationInput = scanner.nextLine();
                if (!dateCreationInput.isEmpty()) {
                    Date dateCreation = dateFormat.parse(dateCreationInput);
                    partenaire.setDateCreation(dateCreation);
                }

                partenaireService.updatePartenaire(partenaire);
                System.out.println("Partenaire updated successfully.");
            } else {
                System.out.println("Partenaire not found.");
            }
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }
    }

    private void deletePartenaire() {
        try {
            System.out.print("Enter Partenaire ID to delete: ");
            UUID id = UUID.fromString(scanner.nextLine());
            partenaireService.deletePartenaire(id);
            System.out.println("Partenaire deleted successfully.");
        } catch (SQLException e) {
            System.out.println("Partenaire not found.");
        }
    }
}
