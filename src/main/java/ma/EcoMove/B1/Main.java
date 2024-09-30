package main.java.ma.EcoMove.B1;

import main.java.ma.EcoMove.B1.UI.ClientUI;
import main.java.ma.EcoMove.B1.UI.PrincipalMenu;
import main.java.ma.EcoMove.B1.dao.ClientDAO;
import main.java.ma.EcoMove.B1.service.ClientService;
import main.java.ma.EcoMove.B1.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        DatabaseConnection dbConnection = DatabaseConnection.getInstance();

        try (Connection connection = dbConnection.getConnection()) {
            if (connection != null) {
                ClientDAO clientDAO = new ClientDAO();
               ClientService clientService = new ClientService(clientDAO);

              //  ClientUI clientUI = new ClientUI(clientService);
              //  clientUI.login();
                PrincipalMenu principalMenu = new PrincipalMenu();
                principalMenu.run();

            } else {
                System.out.println("Failed to connect to the database.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to connect to the database. Exiting...");
        }

    }

}
