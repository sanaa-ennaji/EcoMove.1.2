package main.java.ma.EcoMove.B1.dao;

import main.java.ma.EcoMove.B1.dao.Interface.IClient;
import main.java.ma.EcoMove.B1.entity.Client;
import main.java.ma.EcoMove.B1.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

public class ClientDAO implements IClient {

    private final Connection connection;

    public ClientDAO() {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }

    @Override
    public Optional<Client> findByEmail(String email) {
        String query = "SELECT * FROM clients WHERE email = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Client client = new Client(
                        UUID.fromString(rs.getString("id")),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("email"),
                        rs.getString("telephone"),
                        rs.getDate("dateInscription").toLocalDate(),
                        new ArrayList<>()

                );
                return Optional.of(client);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void save(Client client) {
        String query = "INSERT INTO clients (id, nom, prenom, email, telephone, dateInscription) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setObject(1, client.getId());
            stmt.setString(2, client.getNom());
            stmt.setString(3, client.getPrenom());
            stmt.setString(4, client.getEmail());
            stmt.setString(5, client.getTelephone());
            stmt.setDate(6, Date.valueOf(client.getDateInscription()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
